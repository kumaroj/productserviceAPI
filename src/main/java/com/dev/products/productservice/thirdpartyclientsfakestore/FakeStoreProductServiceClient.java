package com.dev.products.productservice.thirdpartyclientsfakestore;

import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductServiceClient")
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.hosturl}")
    private String fakestorehostUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakestoreproductsapiPath;
    private String specificProductRequestUrl = fakestorehostUrl + fakestoreproductsapiPath +"/{id}";
    private String productRequestBaseUrl = fakestorehostUrl + fakestoreproductsapiPath;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder ){
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,
                genericProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }




    public FakeStoreProductDto updateProduct(GenericProductDto genericProductDto , long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity  = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    /*    genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;*/
    }



    public FakeStoreProductDto getproductById(long id) throws NotFoundException {

        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductRequestUrl ,
                FakeStoreProductDto.class , id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto == null)
            throw new NotFoundException("No product is there with id :" +id);
        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> productLists =  new ArrayList<>();

        Arrays.stream(response.getBody()).forEach(FakeStoreProductDto->{

            productLists.add(FakeStoreProductDto);
        });

        return productLists;
    }


    public FakeStoreProductDto deleteproductById(long id) {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductRequestUrl,
                HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();

    }
}
