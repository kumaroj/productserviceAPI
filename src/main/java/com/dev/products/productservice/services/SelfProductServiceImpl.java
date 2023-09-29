package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Category;
import com.dev.products.productservice.models.Price;
import com.dev.products.productservice.models.Product;
import com.dev.products.productservice.repositories.CategoryRepository;
import com.dev.products.productservice.repositories.PriceRepository;
import com.dev.products.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository,
                                  PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) {
        Category category = new Category();
        category.setName(productDto.getCategory());
        Category savedCategory = categoryRepository.save(category);

        Price price =new Price();
        price.setPrice(productDto.getPrice());
        Price productprice = priceRepository.save(price);

        Product  product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(savedCategory);
        product.setPrice(productprice);
        Product savedproduct = productRepository.save(product);
        return mapperforgenericproductDto(product);

    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, String id) throws NotFoundException {
        Product product = productRepository.findById(UUID.fromString(id)).get();
       product.getPrice().setPrice(genericProductDto.getPrice());
       product.setDescription(genericProductDto.getDescription());
       product.setTitle(genericProductDto.getTitle());
       Product productUpdated = productRepository.save(product);
      return mapperforgenericproductDto(productUpdated);
    }


    @Override
    public GenericProductDto getproductById(String id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        return mapperforgenericproductDto(product.get());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
       List<GenericProductDto>productList = new ArrayList<>();
       productRepository.findAll().forEach(product -> {
           productList.add(mapperforgenericproductDto(product));
       });
        return productList;
    }

    @Override
    @Transactional
    public GenericProductDto deleteproductById(String id) throws NotFoundException {
        GenericProductDto genericProductDto = null;
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if (product == null)
            throw new NotFoundException("No product is there with id :" +id);

        genericProductDto =  mapperforgenericproductDto(product.get());
        productRepository.deleteById(UUID.fromString(id));

    return genericProductDto;
    }


    public GenericProductDto mapperforgenericproductDto(Product product){

        GenericProductDto genericProductDto= new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setId(product.getId());

        return genericProductDto;
    }
}
