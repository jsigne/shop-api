package com.jsi.shop.product.feature.getAll;

import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
