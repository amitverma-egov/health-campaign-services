package org.egov.transformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.egov.common.models.project.Task;
import org.egov.common.models.project.TaskResource;
import org.egov.transformer.config.TransformerProperties;
import org.egov.transformer.enums.Operation;
import org.egov.transformer.models.downstream.ProjectTaskIndexV1;
import org.egov.transformer.producer.Producer;
import org.egov.transformer.service.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public abstract class ProjectTaskTransformationService implements TransformationService<Task> {
    protected final ProjectTaskIndexV1Transformer transformer;

    protected final Producer producer;

    protected final TransformerProperties properties;

    @Autowired
    protected ProjectTaskTransformationService(ProjectTaskIndexV1Transformer transformer,
                                               Producer producer, TransformerProperties properties) {
        this.transformer = transformer;
        this.producer = producer;
        this.properties = properties;
    }

    @Override
    public void transform(List<Task> payloadList) {
        log.info("transforming for ids {}", payloadList.stream()
                .map(Task::getId).collect(Collectors.toList()));
        List<ProjectTaskIndexV1> transformedPayloadList = payloadList.stream()
                .map(transformer::transform)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("transformation successful");
        producer.push(getTopic(),
                transformedPayloadList);
    }

    public abstract String getTopic();

    @Override
    public Operation getOperation() {
        return Operation.TASK;
    }

    @Component
    static class ProjectTaskIndexV1Transformer implements
            Transformer<Task, ProjectTaskIndexV1> {
        private final ProjectService projectService;
        private final TransformerProperties properties;

        @Autowired
        ProjectTaskIndexV1Transformer(ProjectService projectService,  TransformerProperties properties) {
            this.projectService = projectService;
            this.properties = properties;
        }

        @Override
        public List<ProjectTaskIndexV1> transform(Task task) {
            Map<String, String> boundaryLabelToNameMap = null;
            if (task.getAddress().getLocality() != null && task.getAddress().getLocality().getCode() != null) {
                boundaryLabelToNameMap = projectService
                        .getBoundaryLabelToNameMap(task.getAddress().getLocality().getCode(), task.getTenantId());
            } else {
                boundaryLabelToNameMap = projectService
                        .getBoundaryLabelToNameMapByProjectId(task.getProjectId(), task.getTenantId());
            }
            String tenantId = task.getTenantId();
            JsonNode mdmsBoundaryData = projectService.fetchBoundaryData(tenantId,"");
            List<JsonNode> boundaryLevelVsLabel = StreamSupport
                    .stream(mdmsBoundaryData.get("boundaryHierarchy").spliterator(), false).collect(Collectors.toList());
            log.info("boundary labels {}", boundaryLabelToNameMap.toString());
            Map<String, String> finalBoundaryLabelToNameMap = boundaryLabelToNameMap;
            return task.getResources().stream().map(r ->
                    transformTaskToProjectTaskIndex(r,task,finalBoundaryLabelToNameMap,boundaryLevelVsLabel)
            ).collect(Collectors.toList());
        }

        private ProjectTaskIndexV1 transformTaskToProjectTaskIndex(TaskResource taskResource,Task task,Map<String, String> finalBoundaryLabelToNameMap,List<JsonNode> boundaryLevelVsLabel){
          ProjectTaskIndexV1 projectTaskIndexV1 =  ProjectTaskIndexV1.builder()
                    .id(taskResource.getId())
                    .taskId(task.getId())
                    .taskType("DELIVERY")
                    .projectId(task.getProjectId())
//                    .projectType("")
                    .tenantId(task.getTenantId())
                    .startDate(task.getActualStartDate())
                    .endDate(task.getActualEndDate())
                    .productVariant(taskResource.getProductVariantId())
                    .isDelivered(taskResource.getIsDelivered())
                    .quantity(taskResource.getQuantity())
                    .deliveredTo("HOUSEHOLD")
                    .deliveryComments(taskResource.getDeliveryComment())
                    .latitude(task.getAddress().getLatitude())
                    .longitude(task.getAddress().getLongitude())
                    .createdTime(task.getAuditDetails().getCreatedTime())
                    .createdBy(task.getAuditDetails().getCreatedBy())
                    .lastModifiedTime(task.getAuditDetails().getLastModifiedTime())
                    .lastModifiedBy(task.getAuditDetails().getLastModifiedBy())
                    .isDeleted(task.getIsDeleted())
                    .build();
            //todo verify this
            boundaryLevelVsLabel.forEach(node->{
                if(node.get("level").asInt()>1){
                    projectTaskIndexV1.getBoundaryHierarchy().put(node.get("indexLabel").asText(),finalBoundaryLabelToNameMap.get(node.get("indexLabel").asText())==null? null: finalBoundaryLabelToNameMap.get(node.get("indexLabel").asText()));
                }
            });
            return projectTaskIndexV1;
        }
    }
}
