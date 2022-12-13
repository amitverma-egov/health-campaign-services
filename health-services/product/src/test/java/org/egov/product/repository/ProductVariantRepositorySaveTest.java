package org.egov.product.repository;

import org.egov.common.producer.Producer;
import org.egov.product.helper.ProductVariantTestBuilder;
import org.egov.product.web.models.ProductVariant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductVariantRepositorySaveTest {

    @InjectMocks
    private ProductVariantRepository productVariantRepository;

    @Mock
    private Producer producer;

//    @Mock
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Mock
//    private HashOperations hashOperations;

    private List<ProductVariant> productVariants;

    private static final String TOPIC = "save-product-variant-topic";

    @BeforeEach
    void setUp() {
        productVariants = Collections.singletonList(ProductVariantTestBuilder
                .builder().withId().build());
        //when(redisTemplate.opsForHash()).thenReturn(hashOperations);
    }

    @Test
    @DisplayName("should save and return saved objects back")
    void shouldSaveAndReturnSavedObjectsBack() {
        List<ProductVariant> result = productVariantRepository
                .save(productVariants, TOPIC);

        assertEquals(result, productVariants);
        verify(producer, times(1)).push(any(String.class), any(Object.class));
    }

    @Test
    @DisplayName("should save and add objects in the cache")
    @Disabled
    void shouldSaveAndAddObjectsInTheCache() {
//        productVariantRepository.save(productVariants, TOPIC);
//
//        InOrder inOrder = inOrder(producer, hashOperations);
//
//        inOrder.verify(producer, times(1)).push(any(String.class), any(Object.class));
//        inOrder.verify(hashOperations, times(1))
//                .putAll(any(String.class), any(Map.class));
    }
}