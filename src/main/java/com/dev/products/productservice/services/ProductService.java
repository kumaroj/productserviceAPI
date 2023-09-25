package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Product;

import java.util.List;

public interface ProductService {

   public GenericProductDto createProduct(GenericProductDto genericProductDto);

   public GenericProductDto updateProduct(GenericProductDto genericProductDto , String id);

   public GenericProductDto getproductById(String id) throws NotFoundException;

   public List<GenericProductDto> getAllProducts();

   public GenericProductDto deleteproductById(String id) throws NotFoundException;



}
