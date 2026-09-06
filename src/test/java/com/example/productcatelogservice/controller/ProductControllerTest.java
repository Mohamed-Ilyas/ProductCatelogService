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

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void TestReplaceProduct_SuccessScenario() {
        //Arrange

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Laptop");
        //when(productService.replaceProduct(1L, product)).thenReturn(product);
        when(productService.replaceProduct(eq(1L), any(Product.class))).thenReturn(product);
        //Act

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.replaceProduct(1L, productDto);

        //Assert

        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(HttpStatus.OK, productDtoResponseEntity.getStatusCode());
        assertEquals(1L, productDtoResponseEntity.getBody().getId());
        assertEquals("Laptop", productDtoResponseEntity.getBody().getName());

    }

    @Test
    public void TestCreateProduct_SuccessScenario() {
        //Arrange

        ProductDto inputDto = new ProductDto();
        inputDto.setName("Gaming Mouse");
        inputDto.setPrice(49.99);
        inputDto.setDescription("High precision wireless gaming mouse");

        Product mockSavedProduct = new Product();
        mockSavedProduct.setId(101L);
        mockSavedProduct.setName("Gaming Mouse");
        mockSavedProduct.setPrice(49.99);
        mockSavedProduct.setDescription("High precision wireless gaming mouse");

        when(productService.createProduct(any(Product.class))).thenReturn(mockSavedProduct);

        //Act

        ResponseEntity<ProductDto> responseEntity = productController.createProduct(inputDto);

        //Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ProductDto outputDto = responseEntity.getBody();
        assertNotNull(outputDto);
        assertEquals(101L, outputDto.getId());
        assertEquals("Gaming Mouse", outputDto.getName());
        assertEquals(49.99, outputDto.getPrice());

    }

    @Test
    public void TestGetAllProducts_SuccessScenario() {
        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Phone");

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(product1);
        mockProductList.add(product2);

        when(productService.getAllProducts()).thenReturn(mockProductList);

        //Act
        ResponseEntity<List<ProductDto>> responseEntity = productController.getAllProducts();

        //Assert
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<ProductDto> outputList = responseEntity.getBody();
        assertEquals(2, outputList.size());

        assertEquals(1L, outputList.get(0).getId());
        assertEquals("Laptop", outputList.get(0).getName());

        assertEquals(2L, outputList.get(1).getId());
        assertEquals("Phone", outputList.get(1).getName());

    }

    @Test
    public void TestDeleteProduct_SuccessScenario() {
        //Arrange
        Long productId = 1L;

        doNothing().when(productService).deleProduct(productId);

        //Act
        ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);

        //Assert
        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

    }




}