package com.example.productcatelogservice.repo;

import com.example.productcatelogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
