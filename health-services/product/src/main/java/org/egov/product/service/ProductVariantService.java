package org.egov.product.service;

import lombok.extern.slf4j.Slf4j;
import org.egov.common.service.IdGenService;
import org.egov.product.web.models.ProductVariant;
import org.egov.product.web.models.ProductVariantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ProductVariantService {

    private final IdGenService idGenService;

    @Autowired
    public ProductVariantService(IdGenService idGenService) {
        this.idGenService = idGenService;
    }

    public List<ProductVariant> create(ProductVariantRequest request) throws Exception {
        Optional<ProductVariant> anyProductVariant = request.getProductVariant()
                .stream().findAny();
        String tenantId = null;
        if (anyProductVariant.isPresent()) {
            tenantId = anyProductVariant.get().getTenantId();
        }
        log.info("Generating IDs using IdGenService");
        List<String> idList = idGenService.getIdList(request.getRequestInfo(), tenantId,
                "product.variant.id", "", request.getProductVariant().size());
        log.info("IDs generated");
        IntStream.range(0, request.getProductVariant().size())
                .forEach(i -> request.getProductVariant().get(i).setId(idList.get(i)));
        return request.getProductVariant();
    }
}
