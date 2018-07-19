package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.FoodOrderBill;

public interface FoodOrderBillService {

    // 生成消费单记录
    FoodOrderBill generateFoodOrder();
    FoodOrderBill generateFoodOrder(FoodOrderBill fo);

    // 获取当前桌台ID的消费单
    FoodOrderBill getFoodOrderByTableId(int tableId);

    // 获取消费单 By ID
    FoodOrderBill getFoodOrderById(int id);
}
