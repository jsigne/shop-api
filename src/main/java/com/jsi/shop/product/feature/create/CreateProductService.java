package com.jsi.shop.product.feature.create;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }
}
