package com.smtech.restaurant.server.controller;

import com.smtech.restaurant.entities.FoodOrderBill;
import com.smtech.restaurant.server.service.FoodOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FoodOrderController {

    @Autowired
    private FoodOrderBillService foodOrderBillService;

    @PostMapping(path = "/foodOrderBill/new")
    @ResponseBody
    public FoodOrderBill createFoodOrderBill(@RequestBody(required = false) FoodOrderBill fo){
        return foodOrderBillService.generateFoodOrder(fo);
//        JSONObject result = (JSONObject) JSONObject.toJSON(fo);
//        return HttpRes.getSuccesResponse(result);
    }


}
