package com.smtech.restaurant.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.smtech.restaurant.common.HttpRes;
import com.smtech.restaurant.entities.FoodOrder;
import com.smtech.restaurant.server.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @RequestMapping("/createFoodOrder")
    @ResponseBody
    public String createFoodOrder(){

        FoodOrder fo = foodOrderService.generateFoodOrder();
        JSONObject result = (JSONObject) JSONObject.toJSON(fo);
        return HttpRes.getSuccesResponse(result);
    }
}
