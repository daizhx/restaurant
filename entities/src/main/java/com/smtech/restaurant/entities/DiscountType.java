package com.smtech.restaurant.entities;


public class DiscountType extends BaseColumn {

    private String name;

    private float discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
