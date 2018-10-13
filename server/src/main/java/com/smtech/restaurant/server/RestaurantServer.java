package com.smtech.restaurant.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.enilu.guns"},basePackageClasses = {SpringContextHolder.class})
@EnableJpaRepositories(basePackages = {"com.smtech.restaurant.server.dao","cn.enilu.guns.dao"})
@EntityScan({"com.smtech.restaurant.entities","cn.enilu.guns.bean.entity"})
public class RestaurantServer {

    public static void main(String[] args){
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        //启动
        SpringApplication.run(RestaurantServer.class, args);
        //启动线程
        new ReceiveUDP().start();
    }
}