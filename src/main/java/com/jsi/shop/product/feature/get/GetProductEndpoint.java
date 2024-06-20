package com.jsi.shop.product.feature.get;

import com.jsi.shop.exception.NotFoundException;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Products")
public class GetProductEndpoint {

    private final ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public Product get(@PathVariable Long id){
        return productRepository.findById(id)
                .map(product -> {
                    log.info("Get product by id {}", id);
                    return product;
                })
                .orElseThrow(() -> new NotFoundException("Product not found for id " + id));
    }
}
