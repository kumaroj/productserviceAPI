package com.dev.products.productservice.dtos;

import com.dev.products.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GenericProductDto {

    private UUID id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
