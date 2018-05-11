package com.smtech.restaurant.order;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridBagLayoutAdp;
import com.smtech.swing.common.panel.PagerView;
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
        PagerView pv = new PagerView(20,3);

        java.util.List<Object[]> testData = new ArrayList<Object[]>();

        for (int i=0; i<30; i++){
            Object[] it = new Object[3];
            it[0] = "aaa";
            it[1] = 1;
            it[2] = "100";
            testData.add(it);
        }
        pv.setData(testData);

        return pv;
    }
}
