package com.example.productcatelogservice.TableInheritenceImplementation.Default;

import jakarta.persistence.Entity;

@Entity(name="instructor")
public class Instructor extends User {
    private String company;
}
