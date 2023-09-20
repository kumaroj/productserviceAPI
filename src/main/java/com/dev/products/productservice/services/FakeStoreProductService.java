package com.dev.products.productservice.services;

import com.dev.products.productservice.thirdpartyclientsfakestore.FakeStoreProductDto;
import com.dev.products.productservice.dtos.GenericProductDto;
import com.dev.products.productservice.exceptions.NotFoundException;
import com.dev.products.productservice.thirdpartyclientsfakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
/*


    private RestTemplateBuilder restTemplateBuilder;
    //private RestTemplate restTemplate;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestBaseUrl = "https://fakestoreapi.com/products";
*/
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;

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
       FakeStoreProductDto fakeStoreProductDto= fakeStoreProductServiceClient.createProduct(genericProductDto);
        return changeFakeStoreProductDtoToGenericDto(fakeStoreProductDto);
    }



    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto , long id) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.updateProduct(genericProductDto  , id);
       genericProductDto.setPrice(fakeStoreProductDto.getPrice());
       genericProductDto.setCategory(fakeStoreProductDto.getCategory());
       genericProductDto.setDescription(fakeStoreProductDto.getDescription());
       return genericProductDto;
    }


    @Override
    public GenericProductDto getproductById(long id) throws NotFoundException {

     FakeStoreProductDto fakeStoreProductDto=fakeStoreProductServiceClient.getproductById(id);
     if (fakeStoreProductDto == null)
         throw new NotFoundException("No product is there with id :" +id);
      return  changeFakeStoreProductDtoToGenericDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> productLists =  new ArrayList<>();
        fakeStoreProductServiceClient.getAllProducts().forEach(fakeStoreProductDto -> {
            GenericProductDto genericProductDto=changeFakeStoreProductDtoToGenericDto(fakeStoreProductDto);
         productLists.add(genericProductDto);
     });

        return productLists;
    }

    @Override
    public GenericProductDto deleteproductById(long id) {
       return changeFakeStoreProductDtoToGenericDto(fakeStoreProductServiceClient.deleteproductById(id));

    }
}
