package com.smtech.restaurant.setting;

import com.smtech.swing.common.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TblAreaSetting {

    public static void main(String[] args) {
        XDialog dialog = new XDialog(MainFrame.getInstance());
        dialog.setTitle("台区");

//        dialog.setCloseAction(funcItemPanel.getCloseAction(dialog));
//        ImageIcon imgIcon = funcItemPanel.getIcon();
//        if (imgIcon != null) {
//            dialog.setIconImage(imgIcon.getImage());
//        }
        JPanel dlgPanel = (JPanel) dialog.getContentPane();
        dlgPanel.removeAll();
        dlgPanel.setLayout(new BorderLayout());
//        dlgPanel.add(contentPanel, BorderLayout.CENTER);
//        dlgPanel.add(new StatusInfoBar(), BorderLayout.SOUTH);
        dialog.setModal(true);
        dialog.setPreferredSize(new Dimension(1000, 595));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
