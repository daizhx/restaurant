package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.http.HttpClient;
import com.smtech.swing.common.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class TblAreaSetting {

    public void loadData(){
        HttpClient httpClient = HttpClient.getInstance();
        httpClient.setLocalServerIP("127.0.0.1");
        httpClient.getLocal("/dining_table_area/all", new HttpClient.HttpRequestResult() {
            @Override
            public void onSuccess(String res) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public static void main(String[] args) {

        JPanel contentPanel = new TblAreaDlg().getContentPanel();
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
}
