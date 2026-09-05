package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public Product replaceProduct(Long id, Product product);
    public Product createProduct(Product product);
    public List<Product> getAllProducts();
    public void deleProduct(Long id);
}
