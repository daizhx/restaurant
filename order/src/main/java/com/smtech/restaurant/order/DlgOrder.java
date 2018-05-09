package com.smtech.restaurant.order;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridBagLayoutAdp;
import com.smtech.swing.common.panel.ListView;
import com.smtech.swing.common.panel.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DlgOrder extends DlgBase {

    int orderId;

    public DlgOrder(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel onCrtContntView() {
        TransparentPanel content = new TransparentPanel();
        GridBagLayoutAdp gla = GridBagLayoutAdp.getInstance();
        gla.add(crtOrderView(),1);
        gla.addWithFixSize(crtFuncBtns(),60);
        gla.add(crtRightPane(),2);
        gla.doLayout(content);
        return content;
    }

    private Component crtRightPane() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        return p;
    }

    private Component crtFuncBtns() {
        return new TransparentPanel();
    }

    private Component crtOrderView() {
        ListView listView = new ListView(3);

        java.util.List<String> testData = new ArrayList<String>();
//        listView.setData(testData);
        return listView;
    }
}
