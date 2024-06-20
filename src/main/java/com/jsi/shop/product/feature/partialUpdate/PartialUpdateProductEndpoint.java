package com.jsi.shop.product.feature.partialUpdate;

import com.jsi.shop.exception.NotFoundException;
import com.jsi.shop.product.Product;
import com.jsi.shop.product.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Products")
public class PartialUpdateProductEndpoint {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    @PatchMapping("/products/{id}")
    public Product partialUpdate(@PathVariable Long id, @RequestBody PartialUpdateProductCommand partialUpdateProductCommand){
        Product partialProduct = modelMapper.map(partialUpdateProductCommand, Product.class);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id " + id));

        if (StringUtils.isNotBlank(partialProduct.getCode())){
            product.setCode(partialProduct.getCode());
        }
        if (StringUtils.isNotBlank(partialProduct.getName())){
            product.setName(partialProduct.getName());
        }
        if (StringUtils.isNotBlank(partialProduct.getDescription())){
            product.setDescription(partialProduct.getDescription());
        }
        if (partialProduct.getPrice() != null){
            product.setPrice(partialProduct.getPrice());
        }
        if (partialProduct.getQuantity() != null){
            product.setQuantity(partialProduct.getQuantity());
        }
        if (StringUtils.isNotBlank(partialProduct.getCategory())){
            product.setCategory(partialProduct.getCategory());
        }
        if (StringUtils.isNotBlank(partialProduct.getInventoryStatus())){
            product.setInventoryStatus(partialProduct.getInventoryStatus());
        }
        if (StringUtils.isNotBlank(partialProduct.getImage())){
            product.setImage(partialProduct.getImage());
        }
        if (partialProduct.getRating()!= null){
            product.setRating(partialProduct.getRating());
        }

        Product savedProduct = productRepository.save(product);
        log.info("Product with id {} updated", savedProduct.getId());

        return savedProduct;
    }

}
