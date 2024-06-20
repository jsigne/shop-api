package com.jsi.shop.product.feature.delete;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(DeleteProductEndpoint.class)
class DeleteProductEndpointTest {

    private final String DELETE_PRODUCT_URL = "/products/{id}";

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void delete() throws Exception {
        Long productId = 1L;
        Product productToDelete = prepareProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToDelete));

        mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_PRODUCT_URL, productId))
                .andReturn();

        verify(productRepository).delete(productToDelete);
    }

    private Product prepareProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setCode("P1");
        product.setName("Basic product");
        product.setDescription("A basic product");
        product.setCategory("Category");
        product.setPrice(10F);
        product.setQuantity(10);
        product.setInventoryStatus("Medium");
        return product;
    }

}