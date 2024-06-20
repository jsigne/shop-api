package com.jsi.shop.product.feature.partialUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartialUpdateProductCommand {
        
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
