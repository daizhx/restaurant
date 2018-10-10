package com.smtech.restaurant.casher;

import com.smtech.restaurant.common.Module;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgDiningTable;

/**
 * Casher
 * 1，提供交互窗口
 * 2，收银业务处理
 * 3，操作数据
 */
public class Casher implements Module{

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));//系统的classpaht路径
        Casher casher = new Casher();
        casher.start();
    }

    private void showWelcomeDlg(){
        DlgDiningTable dlg = (DlgDiningTable) DlgManager.getInstance().getDlg(DlgDiningTable.class);
        dlg.display();
    }

    @Override
    public void start() {
        showWelcomeDlg();
    }

    @Override
    public void exit() {

    }
}
