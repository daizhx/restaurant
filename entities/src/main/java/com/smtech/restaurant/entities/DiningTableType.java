package com.smtech.restaurant.entities;

import javax.persistence.Entity;

@Entity
public class DiningTableType extends BaseColumn {

    private String name;

    //服务费
    private float serviceCharge;
    //最低消费
    private float minimumConsumption;
    //就餐人数
    private int personCapacity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
