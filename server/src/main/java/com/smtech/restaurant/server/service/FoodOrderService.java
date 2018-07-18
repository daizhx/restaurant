package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.FoodOrder;

public interface FoodOrderService {

    // 生成消费单记录
    FoodOrder generateFoodOrder(FoodOrder fo);

    // 获取当前桌台ID的消费单
    FoodOrder getFoodOrderByTableId(int tableId);

    // 获取消费单 By ID
    FoodOrder getFoodOrderById(int id);
}
