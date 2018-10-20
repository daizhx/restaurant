package com.smtech.restaurant.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// 消费单
@Entity
public class FoodOrderBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // 创建时间
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;

    // 结账时间
    private Date checkTime;

    //应收
    @Column(nullable = true)
    private float chargeSum;

    //实收
    @Column(nullable = true)
    private float income;

    //服务费
    @Column(nullable = true)
    private float serviceCharge;

    //外卖送餐地址
    private String deliveryAddr;

    // 地税
    @Column(nullable = true)
    private float tax;

    //国税
    @Column(nullable = true)
    private float stateTax;

    // 付款情况
    @OneToMany
    private List<Payment> paymentList;

    //来源
    private String createSource;

    // 消费详情
    @OneToMany
    private List<FoodOrder> foodOrderList;

    // 关联的桌台，外卖，快餐等账单为null
    @OneToMany
    @Column(nullable = true)
    private List<DiningTable> diningTables;


    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }

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

    public float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getStateTax() {
        return stateTax;
    }

    public void setStateTax(float stateTax) {
        this.stateTax = stateTax;
    }

    public List<FoodOrder> getFoodOrderList() {
        return foodOrderList;
    }

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }


    public float getChargeSum() {
        return chargeSum;
    }

    public void setChargeSum(float chargeSum) {
        this.chargeSum = chargeSum;
    }
}
