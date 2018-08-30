package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.DiningTableArea;
import com.smtech.restaurant.server.service.DinningTblAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DiningTblAreaController {

    @Autowired
    DinningTblAreaService service;

    @RequestMapping(value = "/dining_table_area/add",method = RequestMethod.POST)
    @ResponseBody
    DiningTableArea addDiningTableArea(@RequestBody DiningTableArea t){
        DiningTableArea ret = service.add(t);
        return ret;
    }

    @RequestMapping(value = "/dining_table_area/all",method = RequestMethod.GET)
    @ResponseBody
    public List<DiningTableArea> getAllDiningTable(){
        return service.getAll();
    }
}
