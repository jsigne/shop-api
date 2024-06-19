package com.jsi.shop.product.feature.getAll;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GetAllProductEndpoint.class)
class GetAllProductEndpointTest {
    private final String GET_ALL_PRODUCT_URL = "/products";

    @MockBean
    private GetAllProductService getAllProductService;
    @MockBean
    private ModelMapper mapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenAddUsersRequest_WhenBodyIsValid_ThenReturn204NoContent() throws Exception {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setName("Product");
        List<ProductResponse> expectProductResponses = List.of(productResponse);

        Product product = new Product();
        product.setName("Product");
        when(getAllProductService.getAll()).thenReturn(List.of(product));

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        when(mapper.map(productArgumentCaptor.capture(), eq(ProductResponse.class))).thenReturn(productResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_PRODUCT_URL))
                .andExpect(status().isOk())
                .andReturn();

        TypeReference<List<ProductResponse>> typeRef = new TypeReference<>() {};
        String contentAsString = result.getResponse().getContentAsString();
        List<ProductResponse> actual = objectMapper.readValue(contentAsString,typeRef);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectProductResponses);
    }



}