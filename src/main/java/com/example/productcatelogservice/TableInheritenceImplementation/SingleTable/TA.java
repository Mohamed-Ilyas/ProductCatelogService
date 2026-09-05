package com.example.productcatelogservice.TableInheritenceImplementation.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value = "TA")
public class TA extends User {
    private int helpRequestsCount;
}
