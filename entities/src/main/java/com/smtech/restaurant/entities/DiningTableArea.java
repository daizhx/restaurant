package com.smtech.restaurant.entities;

import javax.persistence.Entity;

//桌台区域，桌台类型
@Entity
public class DiningTableArea extends BaseColumn{

    @ColumnInfo(dspName = "名称")
    private String name;

    //编号
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "DiningTableArea{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
