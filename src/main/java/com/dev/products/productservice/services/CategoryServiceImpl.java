package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.dtos.ProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Category;
import com.dev.products.productservice.models.Product;
import com.dev.products.productservice.repositories.CategoryRepository;
import com.dev.products.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("categoryServiceImpl")
public class CategoryServiceImpl<list> implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category getCategory(String uuid) {
        return null;
    }

    @Override
    public List<String> getproductTitles(List<String> UUID) {
        return null;
    }

    @Override
    public List<String> getallCategories() {
       List<String> categoriesName = new ArrayList<>();
       List<Category> categories= categoryRepository.findAll();
       categories.forEach(category -> {categoriesName.add(category.getName());});

        return categoriesName;
    }

    @Override
    public List<GenericProductDto> getallproductsinCategory(String categoryName) throws NotFoundException {
       List<GenericProductDto>productsinCategory = new ArrayList<>();
        List<Product>products = productRepository.findAllByCategory_Name(categoryName);
        products.forEach(product -> {
            productsinCategory.add(mapperforGenericProductDto(product));
        });
        return productsinCategory;
    }

    public GenericProductDto mapperforGenericProductDto(Product product){
        GenericProductDto genericProductDto= new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());


        return genericProductDto;
    }
}
