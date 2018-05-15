package com.smtech.restaurant.order;

import com.smtech.restaurant.common.Pager;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridBagLayoutAdp;
import com.smtech.swing.common.layout.LinearLayout;
import com.smtech.swing.common.panel.PagerView;
import com.smtech.swing.common.panel.TransparentPanel;
import com.smtech.swing.common.panel.View;

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

        PagerView pv = new PagerView();

        java.util.List<Object[]> testData = new ArrayList<Object[]>();

        for (int i=0; i<20; i++){
            Object[] it = new Object[3];
            it[0] = "aaa";
            it[1] = 1;
            it[2] = "100";
            testData.add(it);
        }
        for (int i=0; i<20; i++){
            Object[] it = new Object[3];
            it[0] = "bbb";
            it[1] = 2;
            it[2] = "10";
            testData.add(it);
        }

        Pager<Object[]> pager = new Pager<>(testData,20);
        pv.setPager(pager);

        LinearLayout ll = new LinearLayout();
        ll.setOrientation(View.VERTICAL);

        JPanel title = new JPanel();
        title.setBackground(Color.GREEN);
        title.setPreferredSize(new Dimension(40,10));

        ll.add(title);
        ll.add(pv);
        ll.add(pv.getPagerBtnsView());
        JLabel end = new JLabel("尾巴栏");
        ll.add(end);

        JButton button = new JButton("按钮。。。");
        ll.add(button);
        return ll;
    }
}
