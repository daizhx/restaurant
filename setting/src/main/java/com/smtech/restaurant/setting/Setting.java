package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.Module;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgBase;

/**
 * 系统设置模块
 * 该类使用单例模式，在程序中只需存在一个实例
 *
 * TODO 1,实现系统设置主界面及设置菜单导航功能
 *
 */
public class Setting implements Module{

    private static Setting INSTANCE = new Setting();

    private Setting(){
    }

    public static Setting getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void start() {
        //TODO
        DlgManager.getInstance().getDlg(XDialog.class).display();
    }

    @Override
    public void exit() {
        //TODO
    }



    public static void main(String[] args) {
        //测试运行
        Setting setting = Setting.getINSTANCE();
        setting.start();
    }
}
