package com.smtech.restaurant.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.smtech.restaurant.entities"})
public class RestaurantServer {

    public static void main(String[] args){
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        //启动
        SpringApplication.run(RestaurantServer.class, args);
        //启动线程
        new ReceiveUDP().start();
    }
}