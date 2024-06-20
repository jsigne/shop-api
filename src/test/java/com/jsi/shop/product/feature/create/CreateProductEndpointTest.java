package com.jsi.shop.product.feature.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CreateProductEndpoint.class)
class CreateProductEndpointTest {
    private final String CREATE_PRODUCT_URL = "/products";

    @MockBean
    ProductRepository productRepository;
    @MockBean
    private ModelMapper mapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {

        CreateProductCommand productCommand = prepareCreateProductCommand();

        Product expectedProduct = prepareProduct();

        ArgumentCaptor<CreateProductCommand> commandArgumentCaptor = ArgumentCaptor.forClass(CreateProductCommand.class);
        when(mapper.map(commandArgumentCaptor.capture(), eq(Product.class))).thenReturn(expectedProduct);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        when(productRepository.save(productArgumentCaptor.capture())).thenReturn(expectedProduct);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_PRODUCT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productCommand)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(commandArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(productCommand);
        verify(productRepository).save(expectedProduct);
    }

    private static CreateProductCommand prepareCreateProductCommand() {
        CreateProductCommand productCommand = new CreateProductCommand();
        productCommand.setCode("P1");
        productCommand.setName("Basic product");
        productCommand.setDescription("A basic product");
        productCommand.setCategory("Category");
        productCommand.setPrice(10F);
        productCommand.setQuantity(10);
        productCommand.setInventoryStatus("Medium");
        return productCommand;
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
