package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;

public interface IProductService {
    public Product getProductById(Long id);
    public Product replaceProduct(Long id, Product product);
}
