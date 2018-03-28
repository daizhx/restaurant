package com.smtech.restaurant.server;

import com.smtech.restaurant.entities.Table;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class ServiceController {


    @RequestMapping("/")
    @ResponseBody
    String home() {
        Table t = new Table();
        t.setZuoTaiName("tai1");
        return t.getZuoTaiName();
    }




    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
        SpringApplication.run(ServiceController.class, args);
    }
}