package com.example.productcatelogservice.TableInheritenceImplementation.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tbc_ta")
public class TA extends User{
    private int helpRequestsCount;
}
