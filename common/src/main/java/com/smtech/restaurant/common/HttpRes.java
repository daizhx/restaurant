package com.smtech.restaurant.common;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;

public class HttpRes {

    private HttpRes(){

    }

    public static String getSuccesResponse(JSONObject result){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("detail","");
        jsonObject.put("result",result);
        return jsonObject.toJSONString();
    }

    public void test(){
        OkHttpClient client = new OkHttpClient();
    }

}
