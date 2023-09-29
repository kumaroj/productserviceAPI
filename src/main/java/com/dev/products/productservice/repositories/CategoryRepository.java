package com.dev.products.productservice.repositories;

import com.dev.products.productservice.models.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface CategoryRepository extends JpaRepository<Category , UUID> {


    @Query(value = CustomQueries.FIND_CATEGORY_BY_NAME , nativeQuery = true)
    List<Category> findByName(String categoryName);
}
