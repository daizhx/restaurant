package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.FoodOrder;
import com.smtech.restaurant.server.RestaurantServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantServer.class)
public class FoodOrderServiceTest {

    @Autowired
    FoodOrderService foodOrderService;

    @Test
    public void generateFoodOrder() {
        FoodOrder fo = foodOrderService.generateFoodOrder();
        int id = fo.getId();
        System.out.println("----------->"+id);
    }

    @Test
    public void generateFoodOrder1() {

    }
}