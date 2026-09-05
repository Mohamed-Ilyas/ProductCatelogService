package com.example.productcatelogservice.models;


import jakarta.persistence.CascadeType;
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
    @ManyToOne(cascade = CascadeType.ALL) // 🌟 Key fix: ensures child references save smoothly
    private Category category;

    private Double price;
    private Boolean isSaleEligible;
}
