package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.DiningTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiningTableController {


    @RequestMapping("/dining_table/add")
    @ResponseBody
    void addDiningTable(DiningTable t){
        System.out.println("------->");
    }
}
