package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.server.RestaurantServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantServer.class)
public class TableRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    public void save(){
        Food f = new Food();
        f.setName("小炒肉");
        foodRepository.save(f);

    }
}