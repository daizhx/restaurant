package com.smtech.restaurant.server;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.server.dao.FoodRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EntityScan({"com.smtech.restaurant.entities"})
public class ServiceController {

    FoodRepository foodRepository;

    @RequestMapping("/")
    @ResponseBody
    String home() {
//        Food f = new Food();
//        f.setName("kele");
//        foodRepository.save(f);
        return "hello";
    }




    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        SpringApplication.run(ServiceController.class, args);
    }
}