package com.jsi.shop.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String inventoryStatus;
    private String category;
    private String image;
    private Integer rating;
}
