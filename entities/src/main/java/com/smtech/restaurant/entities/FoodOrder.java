package com.smtech.restaurant.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // 生成时间
    private Date createTime;

    // 结账时间
    private Date checkTime;

    //应收
    private float sum;

    //实收
    private float income;

    // 付款情况
    @OneToMany
    private Payment payment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
