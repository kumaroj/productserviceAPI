package com.dev.products.productservice;

import com.dev.products.productservice.models.Category;
import com.dev.products.productservice.models.Price;
import com.dev.products.productservice.models.Product;
import com.dev.products.productservice.repositories.CategoryRepository;
import com.dev.products.productservice.repositories.PriceRepository;
import com.dev.products.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final PriceRepository priceRepository;


    public ProductServiceApplication(ProductRepository productRepository , CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

   public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

public  void run(String ... args){

        Category category = new Category();
        category.setName("Apple Istore");
        Category savedcategory =  categoryRepository.save(category);

        Price price = new Price();
        price.setPrice(100000);
        Price productprice = priceRepository.save(price);

        Product   product = new Product();
        product.setTitle("Iphone 15 pro");
        product.setDescription("Best Iphone ever");
        product.setPrice(productprice);
        product.setCategory(savedcategory);
        productRepository.save(product);


  /*  category1.getProducts().forEach(product1 -> {
        System.out.println(product1.getTitle());
    });*/
}

}
