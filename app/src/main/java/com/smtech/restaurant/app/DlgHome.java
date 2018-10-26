package com.smtech.restaurant.app;

import com.smtech.swing.common.btns.XButton;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridLayoutEx;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import java.awt.*;

public class DlgHome extends DlgBase {
    public DlgHome(Window owner) {
        super(owner);
    }

    @Override
    protected void onCrtContntView(ViewGroup content) {
        content.setLayout(new GridLayoutEx(6,4,10,10));
        content.add(crtMenu("酒楼收银"));
        content.add(crtMenu("开台下单"));
        content.add(crtMenu("经营设置"));
    }

    private JComponent crtMenu(String name){
        XButton btn = new XButton();
        btn.setText(name);
        return btn;
    }
}
