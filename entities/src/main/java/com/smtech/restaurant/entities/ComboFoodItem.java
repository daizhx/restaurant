package com.smtech.restaurant.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ComboFoodItem {

    @EmbeddedId
    private ComboFoodItemId id;

    private int amount;

    private int maxAmount;

    private float price;


    public ComboFoodItemId getId() {
        return id;
    }

    public void setId(ComboFoodItemId id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
