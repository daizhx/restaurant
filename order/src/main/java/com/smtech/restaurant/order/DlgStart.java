package com.smtech.restaurant.order;

import com.smtech.restaurant.common.ServerDiscover;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.BorderLayoutEx;

import javax.swing.*;
import java.awt.*;

//启动窗口
public class DlgStart extends DlgBase {

    public DlgStart(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel onCrtContntView() {
        JLabel hint = new JLabel("启动中。。。");
        JPanel p = new JPanel(new BorderLayoutEx());
        p.setBackground(Color.WHITE);
        p.add(hint,BorderLayout.CENTER);
        new SwingWorker<Void,Void>(){
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
                    DlgOrder dlg = (DlgOrder) DlgManager.getInstance().getDlg(DlgOrder.class);
                    dlg.setVisible(true);
                }else{
                    DlgStart.this.showMessageDialog("启动失败，找不到服务程序！");
                }
                DlgStart.this.close();
                //退出程序
                System.exit(0);
            }
        }.execute();
        return p;
    }
}
