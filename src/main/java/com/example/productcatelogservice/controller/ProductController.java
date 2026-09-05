package com.example.productcatelogservice.controller;

import com.example.productcatelogservice.dtos.CategoryDto;
import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.repo.ProductRepo;
import com.example.productcatelogservice.services.IProductService;
import com.example.productcatelogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        if(productId <= 0L)
            throw new IllegalArgumentException("Please pass id > 0");
        Product product = productService.getProductById(productId);
        if(product == null) {
            throw new RuntimeException("Product not available");
        }
        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("id") Long productId, @RequestBody ProductDto request) {
        Product input = from(request);
        input.setId(productId);
        Product output = productService.replaceProduct(productId, input);
        ProductDto productDto = from(output);
        return new ResponseEntity<>(productDto, HttpStatus.OK);

    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product productOutput = productService.createProduct(product);
        ProductDto productDtoOutput = from(productOutput);
        return new ResponseEntity<>(productDtoOutput, HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(from(product));
        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        if(productId <= 0L)
            throw new IllegalArgumentException("Pass valid id");
        productService.deleProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId((product.getId()));
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}