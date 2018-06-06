package com.smtech.restaurant.common;

import com.alibaba.fastjson.JSONObject;

public class HttpRes {

    private HttpRes(){

    }

    public static String getSuccesResponse(Object result){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("detail","");
        jsonObject.put("data",result);
        return jsonObject.toJSONString();
    }
}
