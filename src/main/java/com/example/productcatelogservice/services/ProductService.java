package com.example.productcatelogservice.services;

import com.example.productcatelogservice.dtos.FakeStoreProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import io.micrometer.common.lang.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                id
        );
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product inputProduct) {
        FakeStoreProductDto input = from(inputProduct);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = putForEntity(
                "https://fakestoreapi.com/products/{id}",
                input,
                FakeStoreProductDto.class,
                id
        );
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.valueOf(200))) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
