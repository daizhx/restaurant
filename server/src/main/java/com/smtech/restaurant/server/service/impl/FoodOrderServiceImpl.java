package com.smtech.restaurant.server.service.impl;

import com.smtech.restaurant.entities.FoodOrder;
import com.smtech.restaurant.server.dao.FoodOrderRepository;
import com.smtech.restaurant.server.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Override
    public FoodOrder generateFoodOrder(FoodOrder fo) {
        return foodOrderRepository.save(fo);
    }

    @Override
    public FoodOrder getFoodOrderByTableId(int tableId) {
        return null;
    }

    @Override
    public FoodOrder getFoodOrderById(int id) {
        return null;
    }

}
