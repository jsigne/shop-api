package com.jsi.shop.product.feature.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductCommand{
        String code;
        String name;
        String description;
        Float price;
        Integer quantity;
        String inventoryStatus;
        String category;
        String image;
        Integer rating;
}
