package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.models.Product;

import java.util.List;

public interface ProductService {

   public GenericProductDto createProduct(GenericProductDto genericProductDto);

   public GenericProductDto updateProduct(GenericProductDto genericProductDto , long id);

   public GenericProductDto getproductById(long id);

   public List<GenericProductDto> getAllProducts();

   public GenericProductDto deleteproductById(long id);

}
