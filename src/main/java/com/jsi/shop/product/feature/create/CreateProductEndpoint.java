package com.jsi.shop.product.feature.create;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Products")
@RequiredArgsConstructor
public class CreateProductEndpoint {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @PostMapping("/products")
    public Product create(@RequestBody @Valid CreateProductCommand createProductCommand){
        Product product = modelMapper.map(createProductCommand, Product.class);
        Product savedProduct = productRepository.save(product);
        log.info("Product created with id {}", savedProduct.getId());
        return savedProduct;
    }
}
