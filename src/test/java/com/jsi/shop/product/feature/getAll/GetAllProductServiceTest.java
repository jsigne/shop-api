package com.jsi.shop.product.feature.getAll;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllProductServiceTest {

    @InjectMocks
    GetAllProductService getAllProductService;
    @Mock
    ProductRepository productRepository;

    @Test
    void getAll() {
        List<Product> expectedProducts = List.of(new Product());
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actual = getAllProductService.getAll();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedProducts);
    }
}