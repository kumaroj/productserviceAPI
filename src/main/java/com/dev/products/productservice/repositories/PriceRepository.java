package com.dev.products.productservice.repositories;

import com.dev.products.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
