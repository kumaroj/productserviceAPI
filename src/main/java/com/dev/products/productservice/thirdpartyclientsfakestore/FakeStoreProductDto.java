package com.dev.products.productservice.thirdpartyclientsfakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
