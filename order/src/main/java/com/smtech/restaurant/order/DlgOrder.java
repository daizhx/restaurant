package com.smtech.restaurant.order;

import com.smtech.restaurant.common.Pager;
import com.smtech.restaurant.entities.FoodOrderBill;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridBagLayoutAdp;
import com.smtech.swing.common.view.LinearLayout;
import com.smtech.swing.common.view.PagerListView;
import com.smtech.swing.common.view.TransparentView;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DlgOrder extends DlgBase {
    // 消费单
    private FoodOrderBill foodOrderBill;


    public DlgOrder(Window owner) {
        super(owner);
    }

    // 刷新对话框
    public void reflash(FoodOrderBill foodOrderBill){
        this.foodOrderBill = foodOrderBill;
    }

    @Override
    protected void onCrtContntView(JPanel content) {
        GridBagLayoutAdp gla = GridBagLayoutAdp.getInstance();
        gla.add(crtOrderView(),1);
        gla.addWithFixSize(crtFuncBtns(),60);
        gla.add(crtRightPane(),2);
        gla.doLayout(content);
    }

    private Component crtRightPane() {
        FoodMenu fm = new FoodMenu();
        fm.setBackground(Color.WHITE);
        return fm;
    }

    private Component crtFuncBtns() {
        return new TransparentView();
    }

    private Component crtOrderView() {

        PagerListView pv = new PagerListView();

        java.util.List<Object[]> testData = new ArrayList<Object[]>();

        Pager<Object[]> pager = new Pager<>(testData,20);
        pv.setPager(pager);

        LinearLayout ll = new LinearLayout();
        ll.setOrientation(ViewGroup.VERTICAL);

        ll.add(pv);
        ll.add(pv.getPagerBtnsView());

        return ll;
    }
}
