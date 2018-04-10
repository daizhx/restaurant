package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.DiningTable;
import com.smtech.restaurant.server.dao.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiningTableController {

    @Autowired
    DiningTableRepository diningTableRepository;

    @RequestMapping(value = "/dining_table/add",method = RequestMethod.POST)
    @ResponseBody
    DiningTable addDiningTable(@RequestBody DiningTable t){
        DiningTable ret = diningTableRepository.save(t);
        return ret;
    }

}
