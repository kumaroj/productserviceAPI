package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.dtos.ProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Category;

import java.util.List;

public interface CategoryService {

  Category getCategory(String uuid);
  List<String>  getproductTitles(List<String> UUID);

  List<String>getallCategories();

  List<GenericProductDto> getallproductsinCategory(String name) throws NotFoundException;
}
