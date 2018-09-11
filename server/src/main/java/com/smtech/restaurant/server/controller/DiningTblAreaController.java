package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.DiningTableArea;
import com.smtech.restaurant.server.service.DinningTblAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/dining_table_area/update",method = RequestMethod.POST)
    @ResponseBody
    boolean updateDiningTableArea(@RequestBody DiningTableArea t){
        boolean ret = service.update(t);
        return ret;
    }

    @RequestMapping(value = "/dining_table_area/all",method = RequestMethod.GET)
    @ResponseBody
    public List<DiningTableArea> getAllDiningTable(){
        return service.getAll();
    }

    @RequestMapping(value = "/dining_table_area/delete",method = RequestMethod.POST)
    @ResponseBody
    boolean deleteDiningTableArea(@RequestBody DiningTableArea t){
        System.out.println("del-------->"+t.toString()+",id="+t.getId());
        boolean ret = service.del(t);
        return ret;
    }


    @RequestMapping(value = "/dining_table_area/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    boolean deleteDiningTableArea(@PathVariable int id){
        boolean ret = service.del(id);
        return ret;
    }
}
