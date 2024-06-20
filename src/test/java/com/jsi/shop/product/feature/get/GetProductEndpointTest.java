package com.jsi.shop.product.feature.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetProductEndpoint.class)
class GetProductEndpointTest {

    private final String GET_PRODUCT_URL = "/products/{id}";

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void get() throws Exception {
        Long productId = 1L;

        Product expectedProduct = prepareProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(GET_PRODUCT_URL, productId))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Product actual = objectMapper.readValue(response, Product.class);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedProduct);
    }

    @Test
    void get_shouldThrowNotFoundException() throws Exception {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(GET_PRODUCT_URL, productId))
                .andExpect(status().isNotFound())
                .andReturn();
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