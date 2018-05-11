package com.smtech.restaurant.server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.smtech.restaurant.entities"})
public class RestaurantServer {

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        SpringApplication.run(RestaurantServer.class, args);
    }
}