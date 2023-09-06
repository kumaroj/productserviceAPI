package com.dev.products.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {

    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
