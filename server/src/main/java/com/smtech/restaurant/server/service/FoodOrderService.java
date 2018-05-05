package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.FoodOrder;

public interface FoodOrderService {

    FoodOrder generateFoodOrder();

    FoodOrder generateFoodOrder(int tableId);
}
