package com.smtech.swing.common.dlgs;

import com.alibaba.fastjson.JSONObject;
import com.smtech.restaurant.common.http.HttpClient;
import com.smtech.restaurant.entities.DiningTable;
import com.smtech.restaurant.util.StringUtil;
import com.smtech.swing.common.layout.GridLayoutEx;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// 桌台界面，公共对话框
public class DlgDiningTable extends DlgBase {
    // 业务server的IP的地址
    private String ip;

    private JPanel contentPanel;

    private ArrayList<DiningTable> talbes;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public DlgDiningTable(Window owner) {
        super(owner);
    }

    @Override
    protected void onCrtContntView(JPanel content) {
        contentPanel = content;
        content.setLayout(new GridLayoutEx());
    }

    public void reflash(){
        HttpClient httpClient = HttpClient.getInstance();
        httpClient.setLocalServerIP(ip);

        httpClient.getLocal("/dining_table/all", new HttpClient.HttpRequestResult() {
            @Override
            public void onSuccess(JSONObject data) {
                System.out.println("onsuccess:---->"+data.toString());
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


}
