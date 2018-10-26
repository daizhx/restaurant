package com.smtech.restaurant.order.ui;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.view.ViewGroup;

import java.awt.*;

public class DlgAuth extends DlgBase{

    public DlgAuth(Window owner) {
        super(owner);
    }

    @Override
    protected void onCrtContntView(ViewGroup content) {
        content.setBackgroundImagePath("bg.png");
    }
}
