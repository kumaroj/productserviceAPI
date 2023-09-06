package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.FakeStoreProductDto;
import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequesturl = "https://fakestoreapi.com/products/{id}";
    private String postProductRequesturl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(postProductRequesturl,
                genericProductDto, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto , long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpEntity<GenericProductDto> request = new HttpEntity<>(genericProductDto);
        ResponseEntity<GenericProductDto> response = restTemplate.exchange(getProductRequesturl , HttpMethod.PUT ,
                request, GenericProductDto.class, id);
        return response.getBody();
    }


    @Override
    public GenericProductDto getproductById(long id) {

     RestTemplate restTemplate= restTemplateBuilder.build();
     ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductRequesturl ,
             FakeStoreProductDto.class , id);
     FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
     GenericProductDto genericProductDto = new GenericProductDto();
     genericProductDto.setId(fakeStoreProductDto.getId());
     genericProductDto.setPrice(fakeStoreProductDto.getPrice());
     genericProductDto.setTitle(fakeStoreProductDto.getTitle());
     genericProductDto.setDescription(fakeStoreProductDto.getDescription());
     genericProductDto.setImage(fakeStoreProductDto.getImage());
     genericProductDto.setCategory(fakeStoreProductDto.getCategory());
     return genericProductDto;
    }
}
