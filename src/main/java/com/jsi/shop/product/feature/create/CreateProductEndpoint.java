package com.jsi.shop.product.feature.create;

import com.jsi.shop.product.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateProductEndpoint {

    private final CreateProductService createProductService;

    private final ModelMapper modelMapper;

    @PostMapping("/products")
    public Product create(@RequestBody CreateProductCommand createProductCommand){
        Product product = modelMapper.map(createProductCommand, Product.class);
        return createProductService.create(product);
    }
}
