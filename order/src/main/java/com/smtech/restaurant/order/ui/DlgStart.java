package com.smtech.restaurant.order.ui;

import com.smtech.restaurant.common.ServerDiscover;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.dlgs.DlgDiningTable;
import com.smtech.swing.common.layout.BorderLayoutEx;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import java.awt.*;

/**
 * 启动窗口
 * 1,搜索局域网内服务器的IP地址
 */
public class DlgStart extends DlgBase {

    // 异步任务
    private class ServerFinder extends SwingWorker<Void,Void>{
        ServerDiscover serverDiscover;
        @Override
        protected Void doInBackground() throws Exception {
            serverDiscover = new ServerDiscover();
            serverDiscover.execute(9876);
            return null;
        }

        @Override
        protected void done() {
            if(serverDiscover.getServerIP() != null){
                onStartSuccess(serverDiscover.getServerIP());
            }else{
                DlgStart.this.showMessageDialog("启动失败，找不到服务程序！");
                DlgStart.this.close();
                //退出程序
                System.exit(0);
            }
        }
    }
    public DlgStart(Window owner) {
        super(owner);
    }

    @Override
    protected void onCrtContntView(ViewGroup content) {
        JLabel hint = new JLabel("启动中。。。");
        content.setLayout(new BorderLayoutEx());
        content.setBackground(Color.WHITE);
        content.add(hint,BorderLayout.CENTER);

        new ServerFinder().execute();
    }

    protected void onStartSuccess(String ip){
        DlgDiningTable dlg = (DlgDiningTable) DlgManager.getInstance().getDlg(DlgDiningTable.class);
        dlg.setIp(ip);
        dlg.reflash();
        dlg.setVisible(true);

        DlgStart.this.close();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
