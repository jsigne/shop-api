package com.jsi.shop.product.feature.getAll;

import com.jsi.shop.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetAllProductEndpoint {

    private final GetAllProductService getAllProductService;

    private final ModelMapper mapper;

    @GetMapping("/products")
    public List<ProductResponse> getAll(){
        return getAllProductService.getAll().stream()
                .map(e -> mapper.map(e, ProductResponse.class))
                .toList();
    }
}
