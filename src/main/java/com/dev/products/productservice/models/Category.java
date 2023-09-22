package com.dev.products.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    // this is the same relation being mapped by category attribute
    // in the other (Product) class
}
