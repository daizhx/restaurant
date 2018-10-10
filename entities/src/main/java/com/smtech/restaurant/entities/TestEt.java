package com.smtech.restaurant.entities;

import javax.persistence.Entity;

@Entity
public class TestEt extends BaseColumn {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
