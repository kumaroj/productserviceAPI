package com.dev.products.productservice.repositories;

import com.dev.products.productservice.models.Category;
import com.dev.products.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface  ProductRepository extends JpaRepository<Product , UUID> {


}
