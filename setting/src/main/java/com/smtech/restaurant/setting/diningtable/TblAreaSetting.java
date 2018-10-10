package com.smtech.restaurant.setting.diningtable;

import com.smtech.restaurant.setting.DlgBeanManage;
import com.smtech.swing.common.DlgManager;

/**
 *设置桌台区域
 * 1，提供交互窗口
 * 2，交互功能增删查改
 */
public class TblAreaSetting {


    public static void main(String[] args) {
        DlgBeanManage dlg = (DlgBeanManage) DlgManager.getInstance().getDlg(DlgBeanManage.class);
        dlg.setPresenter(new TblAreaPresenter());
        dlg.reflash();
        dlg.display();
    }
}
