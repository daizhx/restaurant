package com.smtech.restaurant.setting;

import com.smtech.swing.common.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 *设置桌台区域
 * 1，提供交互窗口
 * 2，交互功能增删查改
 */
public class TblAreaSetting {

    //功能启动
    public void start(){
        TblAreaDlg tad = new TblAreaDlg();
        JPanel contentPanel = tad.getContentPanel();
        XDialog dialog = new XDialog(MainFrame.getInstance());
//        dialog.setTitle("xx");

//        dialog.setCloseAction(funcItemPanel.getCloseAction(dialog));
//        ImageIcon imgIcon = funcItemPanel.getIcon();
//        if (imgIcon != null) {
//            dialog.setIconImage(imgIcon.getImage());
//        }
        JPanel dlgPanel = (JPanel) dialog.getContentPane();
        dlgPanel.removeAll();
        dlgPanel.setLayout(new BorderLayout());
        dlgPanel.add(contentPanel, BorderLayout.CENTER);
//        dlgPanel.add(new StatusInfoBar(), BorderLayout.SOUTH);
        dialog.setModal(true);
        dialog.setPreferredSize(new Dimension(1000, 595));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        TblAreaSetting m = new TblAreaSetting();
        m.start();
    }
}
