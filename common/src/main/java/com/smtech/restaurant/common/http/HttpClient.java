package com.smtech.restaurant.common.http;

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
    //local server ip
    private String localServerIP = "127.0.0.1";
    private final static int localServerPort = 9000;

    private HttpClient(){
        client = new OkHttpClient();
    }

    public static final HttpClient getInstance(){
        return instance;
    }

    public void setLocalServerIP(String localServerIP) {
        this.localServerIP = localServerIP;
    }

    //合成本地server的url
    public String genLocalUrl(String api){
        return "http://" + localServerIP + ":" + localServerPort +"/" + api;
    }

    //调用本地服务端接口方法
    public String getLocal(String api){
        return get(genLocalUrl(api));
    }

    public void getLocal(String api, HttpRequestResult result){
        get(genLocalUrl(api),result);
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
    public void get(String url, HttpRequestResult result){ ;
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
                    result.onSuccess(ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }


    public interface HttpRequestResult{
        void onSuccess(String res);
        void onFail(String msg);
    }
}
