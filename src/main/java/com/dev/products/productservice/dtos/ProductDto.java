package com.dev.products.productservice.dtos;

import com.dev.products.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    private UUID uuid;
    private String title;
    private String description;
    private String image;
    private Price price;

}
