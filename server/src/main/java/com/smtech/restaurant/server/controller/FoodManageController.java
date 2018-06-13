package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.common.http.HttpRes;
import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.entities.FoodType;
import com.smtech.restaurant.server.service.FoodManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FoodManageController {

    @Autowired
    FoodManageService foodManageService;

    @RequestMapping("/foodType/all")
    @ResponseBody
    public String getAllFootType(){
        List<FoodType> foodTypeList = foodManageService.getAllFoodType();
        return HttpRes.getSuccesResponse(foodTypeList);
    }


    @RequestMapping("/food/all")
    @ResponseBody
    public String getAllFoot(){
        List<Food> foodTypeList = foodManageService.getAllFood();
        return HttpRes.getSuccesResponse(foodTypeList);
    }
}
