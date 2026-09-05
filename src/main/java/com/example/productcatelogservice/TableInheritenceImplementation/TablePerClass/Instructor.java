package com.example.productcatelogservice.TableInheritenceImplementation.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tbc_instructor")
public class Instructor extends User {
    private String company;
}
