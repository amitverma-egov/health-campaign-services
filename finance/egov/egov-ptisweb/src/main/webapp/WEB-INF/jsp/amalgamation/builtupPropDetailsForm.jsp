<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~      accountability and the service delivery of the government  organizations.
  ~
  ~       Copyright (C) 2016  eGovernments Foundation
  ~
  ~       The updated version of eGov suite of products as by eGovernments Foundation
  ~       is available at http://www.egovernments.org
  ~
  ~       This program is free software: you can redistribute it and/or modify
  ~       it under the terms of the GNU General Public License as published by
  ~       the Free Software Foundation, either version 3 of the License, or
  ~       any later version.
  ~
  ~       This program is distributed in the hope that it will be useful,
  ~       but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~       GNU General Public License for more details.
  ~
  ~       You should have received a copy of the GNU General Public License
  ~       along with this program. If not, see http://www.gnu.org/licenses/ or
  ~       http://www.gnu.org/licenses/gpl.html .
  ~
  ~       In addition to the terms of the GPL license to be adhered to in using this
  ~       program, the following additional terms are to be complied with:
  ~
  ~           1) All versions of this program, verbatim or modified must carry this
  ~              Legal Notice.
  ~
  ~           2) Any misrepresentation of the origin of the material is prohibited. It
  ~              is required that all modified versions of this material be marked in
  ~              reasonable ways as different from the original version.
  ~
  ~           3) This license does not grant any rights to any user of the program
  ~              with regards to rights under trademark law for use of the trade names
  ~              or trademarks of eGovernments Foundation.
  ~
  ~     In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>

<div class="panel-heading" style="text-align: left">
	<div class="panel-title">
		<s:text name="amenities" />
	</div>
</div>

<div class="panel-body">
	<div class="form-group">
		<%@ include file="../common/amenitiesForm.jsp"%>
	</div>
</div>

<div class="panel-heading" style="text-align: left">
	<div class="panel-title">
		<s:text name="title.constructiontypes" />
	</div>
</div>

<div class="panel-body">
	<div class="form-group">
		<label class="col-sm-3 control-label text-right"> <s:text
				name="floortype" /> <span class="mandatory" /> :
		</label>
		<div class="col-sm-3 add-margin">
			<s:select headerKey="-1" title="Floor type of the property"
				headerValue="%{getText('default.select')}" name="floorTypeId"
				id="floorTypeId" listKey="id" listValue="name"
				list="dropdownData.floorType" value="%{floorTypeId}"
				cssClass="form-control" />
		</div>
		<label class="col-sm-2 control-label text-right"> <s:text
				name="rooftype" /> <span class="mandatory" /> :
		</label>
		<div class="col-sm-3 add-margin">
			<s:select headerKey="-1" title="Roof type of the property"
				headerValue="%{getText('default.select')}" name="roofTypeId"
				id="roofTypeId" listKey="id" listValue="name"
				list="dropdownData.roofType" value="%{roofTypeId}"
				cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label text-right"> <s:text
				name="walltype" /> :
		</label>
		<div class="col-sm-3 add-margin">
			<s:select headerKey="-1" title="Wall type"
				headerValue="%{getText('default.select')}" name="wallTypeId"
				id="wallTypeId" listKey="id" listValue="name"
				list="dropdownData.wallType" value="%{wallTypeId}"
				cssClass="form-control" />
		</div>
		<label class="col-sm-2 control-label text-right"> <s:text
				name="woodtype" /> :
		</label>
		<div class="col-sm-3 add-margin">
			<s:select headerKey="-1" title="Wood type"
				headerValue="%{getText('default.select')}" name="woodTypeId"
				id="woodTypeId" listKey="id" listValue="name"
				list="dropdownData.woodType" value="%{woodTypeId}"
				cssClass="form-control" />
		</div>
	</div>

</div>

<div class="panel-heading" style="text-align: left">
	<div class="panel-title">
		<s:text name="FloorDetailsHeader" />
	</div>
</div>

<div class="form group">
	<table class="table table-bordered" id="floorDetailsTbl">
		<thead>
			<tr>
				<th class="text-center"><s:text name="FloorNo" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="ConstructionType" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="Usage" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="firmName" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="Occupancy" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="Occupantname" /></th>
				<th class="text-center"><s:text name="constrdate" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="effectiveDate" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="unstructuredLand" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="plinthLength" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="plinthBreadth" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="PlinthArea" /><span
					class="mandatory" /></th>
				<th class="text-center"><s:text name="building.permNo" /></th>
				<th class="text-center"><s:text name="buildingpermdate" /></th>
				<th class="text-center"><s:text name="buildingpermplintharea" /></th>
				<th class="text-center" id="addDelFloors"><s:text
						name="Add/Delete" /></th>
			</tr>
		</thead>
		<tbody>
			<s:if test="!propertyDetail.floorDetailsProxy.isEmpty()">
				<s:iterator value="(propertyDetail.floorDetailsProxy.size).{#this}"
					status="floorsstatus">
					<tr id="floorDetailsRow">
						<s:hidden
							name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].floorUid"
							id="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].floorUid"
							value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].floorUid}"></s:hidden>
						<td align="center"><s:select
								headerKey="" headerValue="%{getText('default.select')}"
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].floorNo"
								listKey="key" id="floorNo" listValue="value" list="floorNoMap"
								cssClass="form-control"
								data-errormsg="Floor Number is mandatory!" data-optional="0"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].floorNo}"
								cssStyle="width:100%" title="Floor number of the property" /></td>
						<td align="center"><s:if
								test="%{#floorsstatus.index == 0}">
								<s:select headerKey=""
									headerValue="%{getText('default.select')}"
									name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].structureClassification.id"
									listKey="id" id="floorConstType"
									value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].structureClassification.id}"
									listValue="typeName" list="dropdownData.StructureList"
									data-errormsg="Classification of building is required!"
									data-optional="0" cssClass="form-control"
									title="Classification of the Building" />
							</s:if> <s:else>
								<s:select headerKey=""
									headerValue="%{getText('default.select')}"
									name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].structureClassification.id"
									listKey="id" id="floorConstType%{#floorsstatus.index-1}"
									value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].structureClassification.id}"
									listValue="typeName" list="dropdownData.StructureList"
									data-errormsg="Classification of building is required!"
									data-optional="0" cssClass="form-control"
									title="Classification of the Building" />
							</s:else></td>
						<td align="center"><s:select
								headerKey="" headerValue="%{getText('default.select')}"
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].propertyUsage.id"
								listKey="id" id="floorUsage" data-idx="%{#floorsstatus.index}"
								listValue="usageName" list="dropdownData.UsageList"
								cssClass="form-control floorusage" data-optional="0"
								data-errormsg="Nature of usage is required!"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].propertyUsage.id}"
								title="Nature of usage of the property" /></td>

						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].firmName"
								id="firmName" size="25" maxlength="32"
								cssClass="form-control firmname"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].firmName}"
								title="Name of the Firm" /></td>
						<td align="center"><s:select
								headerKey="" headerValue="%{getText('default.select')}"
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].propertyOccupation.id"
								listKey="id" id="floorOccupation" listValue="occupation"
								list="dropdownData.OccupancyList" cssClass="form-control"
								data-optional="0" data-errormsg="Occupancy is required!"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].propertyOccupation.id}"
								title="Property occupied by" /></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].occupantName"
								id="occupantname" size="25" maxlength="32"
								cssClass="form-control"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].occupantName}"
								title="Name of the occupied by person" /></td>

						<td><s:date
								name="propertyDetail.floorDetailsProxy[#floorsstatus.index].constructionDate"
								var="constrDate" format="dd/MM/yyyy" /> <s:textfield
								autocomplete="off"
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].constructionDate"
								value="%{constrDate}" data-optional="0"
								data-errormsg="Construction date is required!"
								id="propertyDetail.floorDetailsProxy[%#floorsstatus.index].constructionDate"
								size="10" maxlength="10" cssClass="form-control datepicker"
								title="Construction Date"></s:textfield></td>
						<td><s:date
								name="propertyDetail.floorDetailsProxy[#floorsstatus.index].occupancyDate"
								var="occDate" format="dd/MM/yyyy" /> <s:textfield
								autocomplete="off"
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].occupancyDate"
								value="%{occDate}" data-optional="0"
								data-errormsg="Occupancy date is required!"
								id="propertyDetail.floorDetailsProxy[%#floorsstatus.index].occupancyDate"
								size="10" maxlength="10" cssClass="form-control datepicker occupancydate"
								title="Tax effective from entered installment"></s:textfield></td>
						<td align="center"><s:select
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].unstructuredLand"
								id="unstructuredLand" list="#{'false':'No','true':'Yes' }"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].unstructuredLand}"
								data-idx="%{#floorsstatus.index}"
								cssClass="form-control unstructuredland" data-optional="0"
								title="Unstructured Land">
							</s:select></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].builtUpArea.length"
								data-idx="%{#floorsstatus.index}" maxlength="10" size="10"
								id="builtUpArealength"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].builtUpArea.length}"
								data-pattern="decimalvalue"
								cssClass="form-control patternvalidation builtuplength" data-optional="1"
								data-errormsg="Length is mandatory!" title="Length" /></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].builtUpArea.breadth"
								data-idx="%{#floorsstatus.index}" maxlength="10" size="10"
								id="builtUpAreabreadth"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].builtUpArea.breadth}"
								data-pattern="decimalvalue"
								cssClass="form-control patternvalidation builtupbreadth" data-optional="1"
								data-errormsg="Breadth is mandatory!" title="Breadth" /></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].builtUpArea.area"
								maxlength="10" size="10" id="builtUpArea" data-optional="0"
								data-errormsg="Plinth area is mandatory!"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].builtUpArea.area}"
								data-pattern="decimalvalue" cssClass="form-control patternvalidation builtuparea"
								title="Length X Width" /></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPermissionNo"
								maxlength="16" size="16"
								id="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPermissionNo"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].buildingPermissionNo}"
								onblur="checkZero(this);" onchange="trim(this,this.value);"
								cssClass="form-control buildingpermissionno"
								title="Building Permission Number" /></td>
						<td><s:date
								name="propertyDetail.floorDetailsProxy[#floorsstatus.index].buildingPermissionDate"
								var="blngPlinthArea" format="dd/MM/yyyy" /> <s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPermissionDate"
								maxlength="16" size="16"
								id="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPermissionDate"
								value="%{blngPlinthArea}"
								cssClass="form-control datepicker buildingpermissiondate"
								title="Building Permission Date" /></td>
						<td><s:textfield
								name="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPlanPlinthArea.area"
								maxlength="10" size="10"
								id="propertyDetail.floorDetailsProxy[%{#floorsstatus.index}].buildingPlanPlinthArea.area"
								value="%{propertyDetail.floorDetailsProxy[#floorsstatus.index].buildingPlanPlinthArea.area}"
								cssClass="form-control buildingplanplintharea patternvalidation"
								data-pattern="decimalvalue" title="Plinth area in building plan" /></td>
						<td class="text-center"><a href="javascript:void(0);"
							class="btn-sm btn-default" onclick="addFloors();"><span
								style="cursor: pointer;"><i class="fa fa-plus"></i></span></a> 
							<span id="deleteFloor" name="deleteFloorBtn"
							class="btn-sm btn-default" alt="removeFloorBtn"> <i
								class="fa fa-trash"></i>							
						</td>
					</tr>
				</s:iterator>
			</s:if>
		</tbody>
	</table>
</div>