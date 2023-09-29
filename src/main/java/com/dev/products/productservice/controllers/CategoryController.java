package com.dev.products.productservice.controllers;

import com.dev.products.productservice.dtos.CategoryDto;
import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.dtos.ProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Category;
import com.dev.products.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories/")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping()
    public List<String> getAllCategories(){
       return categoryService.getallCategories();
    }

    @GetMapping("/{categoryName}")
    public List<GenericProductDto> getallproductsinCategory(@PathVariable("categoryName") String categoryName)
            throws NotFoundException {
       return categoryService.getallproductsinCategory(categoryName);
    }
}
