package com.example.productcatelogservice.TableInheritenceImplementation.Default;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name="user")
public class User {
    @Id
    private UUID id;
    private String name;
}
