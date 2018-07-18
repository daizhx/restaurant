package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.FoodOrder;
import com.smtech.restaurant.server.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping(path = "/foodOrder/new")
    @ResponseBody
    public FoodOrder createFoodOrder(@RequestBody() FoodOrder fo){

        return foodOrderService.generateFoodOrder(fo);
//        JSONObject result = (JSONObject) JSONObject.toJSON(fo);
//        return HttpRes.getSuccesResponse(result);
    }


}
