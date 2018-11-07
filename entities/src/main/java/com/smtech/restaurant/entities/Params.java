package com.smtech.restaurant.entities;

import javax.persistence.Entity;

@Entity
public class Params extends BaseColumn{
    //参数key
    private String key;

    private String strVal;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }
}
