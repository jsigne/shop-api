package com.jsi.shop.product.feature.delete;

import com.jsi.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteProductEndpoint {

    private final ProductRepository productRepository;

    @DeleteMapping("/products/{id}")
    public void get(@PathVariable Long id){
        productRepository.findById(id)
                .ifPresent(productRepository::delete);
    }
}
