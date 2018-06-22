package com.smtech.restaurant.order;

import com.smtech.swing.common.DlgManager;

//点菜模块
public class Order {

    // 模块启动方法
    public void startUp(){
        //TODO inquire serverIp

    }

    public static void main(String[] args) {
        DlgStart dlg = (DlgStart) DlgManager.getInstance().getDlg(DlgStart.class);
        dlg.setSize(500,500);
        dlg.setVisible(true);
    }
}
