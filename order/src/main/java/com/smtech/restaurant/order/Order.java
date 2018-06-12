package com.smtech.restaurant.order;

//点菜模块
public class Order {
    // server的地址
    private String serverIp;

    // 模块启动方法
    public void startUp(){
        //TODO inquire serverIp

        if(null != serverIp){
            // 启动点单主界面

        }
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
