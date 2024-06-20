package com.jsi.shop.product.feature.create;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Products")
public class CreateProductEndpoint {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @PostMapping("/products")
    public Product create(@RequestBody @Valid CreateProductCommand createProductCommand){
        Product product = modelMapper.map(createProductCommand, Product.class);
        return productRepository.save(product);
    }
}
