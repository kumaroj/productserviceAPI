package com.dev.products.productservice.thirdpartyclientsfakestore;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductDto {

    private UUID id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
