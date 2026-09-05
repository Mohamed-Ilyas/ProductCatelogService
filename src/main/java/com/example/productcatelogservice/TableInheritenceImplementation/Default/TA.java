package com.example.productcatelogservice.TableInheritenceImplementation.Default;

import jakarta.persistence.Entity;

@Entity(name="ta")
public class TA extends User {
    private int helpRequestsCount;
}
