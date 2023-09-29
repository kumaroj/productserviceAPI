package com.dev.products.productservice.repositories;

import com.dev.products.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface  ProductRepository extends JpaRepository<Product , UUID> {

   @Modifying
    @Query(value = "DELETE FROM Product p where p.id = ?1")
    int deleteProductsById(UUID id);


    List< Product> findByCategory_Id(UUID Id);


    List<Product> findAllByCategory_Name(String categoryName);
}
