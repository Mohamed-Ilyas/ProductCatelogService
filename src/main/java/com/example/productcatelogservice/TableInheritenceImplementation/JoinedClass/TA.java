package com.example.productcatelogservice.TableInheritenceImplementation.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private int helpRequestsCount;
}
