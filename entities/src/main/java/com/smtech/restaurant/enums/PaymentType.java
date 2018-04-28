package com.smtech.restaurant.enums;

// 支付类型
public enum PaymentType {
    ZFB(2),WX(1),XJ(0);

    int id;

    PaymentType(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }
}
