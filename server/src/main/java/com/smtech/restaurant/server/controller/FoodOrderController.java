package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.FoodOrder;
import com.smtech.restaurant.server.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @RequestMapping("/foodOrder/new")
    @ResponseBody
    public FoodOrder createFoodOrder(@RequestParam(value = "tableId", required = false) int tableId){

        if(StringUtils.isEmpty(tableId)) {
            FoodOrder fo = foodOrderService.generateFoodOrder();
            return fo;
        }else{
            FoodOrder fo = foodOrderService.generateFoodOrder(tableId);
            return fo;
        }
//        JSONObject result = (JSONObject) JSONObject.toJSON(fo);
//        return HttpRes.getSuccesResponse(result);
    }


}
