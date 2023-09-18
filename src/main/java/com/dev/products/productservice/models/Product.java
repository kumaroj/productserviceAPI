package com.dev.products.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel{

    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private int price;
}
