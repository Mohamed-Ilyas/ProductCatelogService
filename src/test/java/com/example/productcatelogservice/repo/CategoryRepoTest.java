package com.example.productcatelogservice.repo;

import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes() {
        Optional<Category> optionalCategory = categoryRepo.findById(10L);
        Category category = optionalCategory.get();
        for(Product product: category.getProductList()) {
            System.out.println(product.getName());
        }
    }
}