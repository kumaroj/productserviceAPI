package com.dev.products.productservice.controllers;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products/")
public class ProductControllers {

    //field injection
    //@Autowired
    private ProductService productService;

    //constructor injection
    public ProductControllers(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    //setter injection
   // @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id){
       return productService.getproductById(id);

    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") long id){
        return productService.deleteproductById(id);
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
       return productService.createProduct(genericProductDto);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProduct(@RequestBody GenericProductDto genericProductDto,@PathVariable("id") long id ){
        return productService.updateProduct(genericProductDto , id);
    }
}
