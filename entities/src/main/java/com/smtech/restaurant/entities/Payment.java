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
    private FoodOrderBill order;

    private PaymentType type;

    private float amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodOrderBill getOrder() {
        return order;
    }

    public void setOrder(FoodOrderBill order) {
        this.order = order;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
