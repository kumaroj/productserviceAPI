package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, long id) {
        return null;
    }


    @Override
    public GenericProductDto getproductById(long id) {
        return null;
    }
}
