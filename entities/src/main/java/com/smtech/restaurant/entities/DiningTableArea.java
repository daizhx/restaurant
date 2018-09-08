package com.smtech.restaurant.entities;

import javax.persistence.Entity;

//桌台区域，桌台类型
@Entity
public class DiningTableArea extends BaseColumn{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
