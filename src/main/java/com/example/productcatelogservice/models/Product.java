package com.example.productcatelogservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Category category;
    private Double price;
    private Boolean isSaleEligible;
}
