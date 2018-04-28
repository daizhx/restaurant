package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.PaymentType;

import javax.persistence.*;

//付款记录
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private FoodOrder order;

    private PaymentType type;

    private float amount;
}
