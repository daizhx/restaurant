package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.Module;

/**
 * 系统设置模块
 * 该类使用单例模式，在整个系统中只需存在一个实例
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
    }

    @Override
    public void exit() {

    }



    public static void main(String[] args) {
        //测试运行
        Setting setting = Setting.getINSTANCE();
        setting.start();
    }
}
