package com.smtech.restaurant.common.http;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

//http客户端
public class HttpClient {

    private static final HttpClient instance = new HttpClient();
    private OkHttpClient client;

    private HttpClient(){
        client = new OkHttpClient();
    }

    public static final HttpClient getInstance(){
        return instance;
    }

    public String get(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //在swing环境中使用的异步接口
    public void get(String url, HttpRequestResult result){
        new SwingWorker<String,Void>(){

            @Override
            protected String doInBackground(){
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void done() {
                try {
                    String ret = get();
                    JSONObject data = JSONObject.parseObject(ret);
                    result.onSuccess(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }


    public interface HttpRequestResult{
        void onSuccess(JSONObject data);
        void onFail(String msg);
    }
}
