package com.jsi.shop.product.feature.partialUpdate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartialUpdateProductEndpoint.class)
class PartialUpdateProductEndpointTest {
    private String PARTIAL_UPDATE_URL = "/products/{id}";
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void partialUpdate_shouldReturnOk_whenPartialProductCommand() throws Exception {
        Long productId = 1L;

        PartialUpdateProductCommand partialUpdateProductCommand = new PartialUpdateProductCommand();
        partialUpdateProductCommand.setQuantity(2);
        partialUpdateProductCommand.setInventoryStatus("Low");

        Product partialProduct = new Product();
        partialProduct.setQuantity(2);
        partialProduct.setInventoryStatus("Low");

        ArgumentCaptor<PartialUpdateProductCommand> partialUpdateCommandArgumentCaptor = ArgumentCaptor.forClass(PartialUpdateProductCommand.class);
        when(mapper.map(partialUpdateCommandArgumentCaptor.capture(), eq(Product.class))).thenReturn(partialProduct);

        Product product = prepareProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product expectedProduct = prepareProduct();
        expectedProduct.setQuantity(2);
        expectedProduct.setInventoryStatus("Low");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.patch(PARTIAL_UPDATE_URL, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partialUpdateProductCommand)))
                .andExpect(status().isOk())
                .andReturn();

        verify(productRepository).save(product);
        assertThat(product).usingRecursiveComparison().isEqualTo(expectedProduct);
        assertThat(partialUpdateCommandArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(partialUpdateProductCommand);
    }
    @Test
    void partialUpdate_shouldReturnOk_whenCompleteProductCommand() throws Exception {
        Long productId = 1L;

        PartialUpdateProductCommand partialUpdateProductCommand = new PartialUpdateProductCommand();
        partialUpdateProductCommand.setCode("P2");
        partialUpdateProductCommand.setName("Simple product");
        partialUpdateProductCommand.setDescription("A simple product");
        partialUpdateProductCommand.setCategory("Category2");
        partialUpdateProductCommand.setPrice(15F);
        partialUpdateProductCommand.setQuantity(50);
        partialUpdateProductCommand.setInventoryStatus("Hight");
        partialUpdateProductCommand.setImage("product.png");
        partialUpdateProductCommand.setRating(4);

        Product partialProduct = new Product();
        partialProduct.setCode("P2");
        partialProduct.setName("Simple product");
        partialProduct.setDescription("A simple product");
        partialProduct.setCategory("Category2");
        partialProduct.setPrice(15F);
        partialProduct.setQuantity(50);
        partialProduct.setInventoryStatus("Hight");
        partialProduct.setImage("product.png");
        partialProduct.setRating(4);

        ArgumentCaptor<PartialUpdateProductCommand> partialUpdateCommandArgumentCaptor = ArgumentCaptor.forClass(PartialUpdateProductCommand.class);
        when(mapper.map(partialUpdateCommandArgumentCaptor.capture(), eq(Product.class))).thenReturn(partialProduct);

        Product product = prepareProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setCode("P2");
        expectedProduct.setName("Simple product");
        expectedProduct.setDescription("A simple product");
        expectedProduct.setCategory("Category2");
        expectedProduct.setPrice(15F);
        expectedProduct.setQuantity(50);
        expectedProduct.setInventoryStatus("Hight");
        expectedProduct.setImage("product.png");
        expectedProduct.setRating(4);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.patch(PARTIAL_UPDATE_URL, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partialUpdateProductCommand)))
                .andExpect(status().isOk())
                .andReturn();

        verify(productRepository).save(product);
        assertThat(product).usingRecursiveComparison().isEqualTo(expectedProduct);
        assertThat(partialUpdateCommandArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(partialUpdateProductCommand);
    }

    @NotNull
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