package com.dev.products.productservice.services;

import com.dev.products.productservice.dtos.FakeStoreProductDto;
import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    //private RestTemplate restTemplate;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestBaseUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;

    }

    private GenericProductDto changeFakeStoreProductDtoToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,
                genericProductDto, GenericProductDto.class);
        return response.getBody();
    }



    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto , long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity  = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, id);
       FakeStoreProductDto fakeStoreProductDto= responseEntity.getBody();
       genericProductDto.setPrice(fakeStoreProductDto.getPrice());
       genericProductDto.setCategory(fakeStoreProductDto.getCategory());
       genericProductDto.setDescription(fakeStoreProductDto.getDescription());
       return genericProductDto;
    }


    @Override
    public GenericProductDto getproductById(long id) throws NotFoundException {

     RestTemplate restTemplate= restTemplateBuilder.build();
     ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductRequestUrl ,
             FakeStoreProductDto.class , id);
     FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
     if (fakeStoreProductDto == null)
         throw new NotFoundException("No product is there with id :" +id);
      return  changeFakeStoreProductDtoToGenericDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> productLists =  new ArrayList<>();

        Arrays.stream(response.getBody()).forEach(FakeStoreProductDto->{
            GenericProductDto genericProductDto  = changeFakeStoreProductDtoToGenericDto(FakeStoreProductDto);
            productLists.add(genericProductDto);
        });

        return productLists;
    }

    @Override
    public GenericProductDto deleteproductById(long id) {
      RestTemplate restTemplate =  restTemplateBuilder.build();
      RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
      ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
      ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductRequestUrl,
               HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
       return changeFakeStoreProductDtoToGenericDto(fakeStoreProductDto);

    }
}
