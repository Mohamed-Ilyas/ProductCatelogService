package com.example.productcatelogservice.controller;

import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import com.example.productcatelogservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;
    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_SuccessScenario() {
        //Arrange

        Product product = new Product();
        product.setId(1L);
        product.setName("NUTS");
        when(productService.getProductById(1L)).thenReturn(product);

        //Act

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(1L);


        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(HttpStatus.OK, productDtoResponseEntity.getStatusCode());
        assertEquals("NUTS", productDtoResponseEntity.getBody().getName());
        assertEquals(1L, productDtoResponseEntity.getBody().getId());
        verify(productService, times(1))
                .getProductById(1L);

    }

    @Test
    public void TestGetProductById_ErrorScenario() {
        //Arrange
        Long productId = -5L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> productController.getProductById(productId));

        assertEquals("Please pass id > 0", exception.getMessage());

    }

}