package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.FoodOrderBill;
import com.smtech.restaurant.server.RestaurantServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantServer.class)
public class FoodOrderBillServiceTest {

    @Autowired
    FoodOrderBillService foodOrderBillService;

    @Test
    public void generateFoodOrder() {
        FoodOrderBill fo = foodOrderBillService.generateFoodOrder();
        int id = fo.getId();
        System.out.println("----------->"+id);
    }

    @Test
    public void generateFoodOrder1() {

    }
}