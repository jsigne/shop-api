package com.jsi.shop.product.feature.create;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @InjectMocks
    CreateProductService createProductService;

    @Mock
    ProductRepository productRepository;

    @Test
    void create() {
        Product product = new Product();
        createProductService.create(product);
        verify(productRepository).save(product);
    }
}