package com.smtech.restaurant.setting;

import javax.swing.*;
import java.awt.*;

//基本的对象管理对话框
public class DlgBeanManage extends XDialog {

    private BeanPresenter presenter;

    public DlgBeanManage(Window parent) {
        super(parent);
        setPreferredSize(new Dimension(1000, 595));
        pack();
        setLocationRelativeTo(null);
    }

    public void setPresenter(BeanPresenter t){
        presenter = t;
    }

    public void reflash(){
        if(presenter == null){
            return;
        }
//        setTitle("设置");
        JComponent contentPanel = presenter.getView();
        JPanel dlgPanel = (JPanel) getContentPane();
        dlgPanel.removeAll();
        dlgPanel.setLayout(new BorderLayout());
        dlgPanel.add(contentPanel, BorderLayout.CENTER);
    }

}
