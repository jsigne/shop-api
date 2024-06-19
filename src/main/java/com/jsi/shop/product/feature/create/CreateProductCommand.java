package com.jsi.shop.product.feature.create;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductCommand{
        @NotBlank
        String code;
        @NotBlank
        String name;
        @NotBlank
        String description;
        @Nonnull
        Float price;
        @Nonnull
        Integer quantity;
        @NotBlank
        String inventoryStatus;
        @NotBlank
        String category;
        String image;
        Integer rating;
}
