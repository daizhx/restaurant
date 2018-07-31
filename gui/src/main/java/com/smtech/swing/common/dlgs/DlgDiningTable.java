package com.smtech.swing.common.dlgs;

import com.alibaba.fastjson.JSON;
import com.smtech.restaurant.common.http.HttpClient;
import com.smtech.restaurant.entities.DiningTable;
import com.smtech.swing.common.layout.BorderLayoutEx;
import com.smtech.swing.common.layout.GridLayoutEx;
import com.smtech.swing.common.view.TextView;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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
        content.setLayout(new GridLayoutEx(10,10,4,4));
    }

    public void reflash(){
        HttpClient httpClient = HttpClient.getInstance();
        httpClient.setLocalServerIP(ip);

        httpClient.getLocal("/dining_table/all", new HttpClient.HttpRequestResult() {
            @Override
            public void onSuccess(String data) {
                List<DiningTable> diningTableList = JSON.parseArray(data,DiningTable.class);
                if(diningTableList.size() == 0){
                    //未设置桌台编号
                    TextView tv = new TextView("您还未设置桌台编号!");
                    contentPanel.setLayout(new BorderLayoutEx());
                    contentPanel.add(tv,BorderLayout.CENTER);
                }
                for(DiningTable t : diningTableList){
                    contentPanel.add(new TableView(t, new ViewGroup.OnClickListener() {
                        @Override
                        public void onClick(ViewGroup v) {
                            TableView v2 = (TableView) v;
                            DiningTable diningTable = v2.getData();
                            System.out.println("click:---->"+diningTable.getName());
                        }
                    }));
                }
                contentPanel.updateUI();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    // 桌台视图
    private class TableView extends ViewGroup{

        private TextView tvName;

        private DiningTable data;

        public TableView(DiningTable data,OnClickListener clickListener) {
            this.data = data;
            setBackground(Color.GREEN);
            setLayout(new BorderLayoutEx());
            tvName = new TextView(data.getName());
            add(tvName,BorderLayout.CENTER);
            setOnClickListener(clickListener);
        }

        public DiningTable getData(){
            return data;
        }
    }


}
