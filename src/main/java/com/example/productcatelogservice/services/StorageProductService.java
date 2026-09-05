package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent())
            return productOptional.get();
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty())
            throw new RuntimeException("Product with "+id+" is not present");
        product.setId(id);
        product.setLastUpdatedAt(new Date());
        return productRepo.save(product);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if(optionalProduct.isPresent())
            throw new RuntimeException("Product with "+product.getId()+" is already present");
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void deleProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty())
            throw new RuntimeException("Product with"+id+" does not exist");
        productRepo.deleteById(id);
    }
}
