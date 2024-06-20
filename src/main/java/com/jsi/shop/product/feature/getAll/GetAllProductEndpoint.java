package com.jsi.shop.product.feature.getAll;

import com.jsi.shop.product.ProductRepository;
import com.jsi.shop.product.ProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Products")
public class GetAllProductEndpoint {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @GetMapping("/products")
    public List<ProductResponse> getAll(){
        return productRepository.findAll().stream()
                .map(e -> mapper.map(e, ProductResponse.class))
                .toList();
    }
}
