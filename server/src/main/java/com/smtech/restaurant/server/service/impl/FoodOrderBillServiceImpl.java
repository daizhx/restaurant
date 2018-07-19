package com.smtech.restaurant.server.service.impl;

import com.smtech.restaurant.entities.FoodOrderBill;
import com.smtech.restaurant.server.dao.FoodOrderRepository;
import com.smtech.restaurant.server.service.FoodOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderBillServiceImpl implements FoodOrderBillService {

    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Override
    public FoodOrderBill generateFoodOrder() {
        return generateFoodOrder(null);
    }

    @Override
    public FoodOrderBill generateFoodOrder(FoodOrderBill fo) {
        if(fo == null){
            fo = new FoodOrderBill();
        }
        return foodOrderRepository.save(fo);
    }

    @Override
    public FoodOrderBill getFoodOrderByTableId(int tableId) {
        return null;
    }

    @Override
    public FoodOrderBill getFoodOrderById(int id) {
        return null;
    }

}
