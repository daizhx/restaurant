package com.smtech.restaurant.entities;

import com.sun.istack.internal.NotNull;

public class DiscountType extends BaseColumn {

    @NotNull
    private String name;

    @NotNull
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
