package com.jsi.shop.product.feature.get;

import com.jsi.shop.exception.NotFoundException;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetProductEndpoint {

    private final ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public Product get(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id " + id));
    }
}
