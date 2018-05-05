package com.smtech.restaurant.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Column(nullable = true)
    private float sum;

    //实收
    @Column(nullable = true)
    private float income;

    // 付款情况
    @OneToMany
    private List<Payment> paymentList;

    //来源
    private String createSource;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }


    public String getCreateSource() {
        return createSource;
    }

    public void setCreateSource(String createSource) {
        this.createSource = createSource;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }


    @Override
    public String toString() {
        return "FoodOrder{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", checkTime=" + checkTime +
                ", sum=" + sum +
                ", income=" + income +
                ", paymentList=" + paymentList +
                ", createSource='" + createSource + '\'' +
                '}';
    }
}
