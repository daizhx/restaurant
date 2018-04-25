package com.smtech.restaurant.order;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.panel.TransparentPanel;

import javax.swing.*;
import java.awt.*;

public class DlgOrder extends DlgBase {

    int orderId;

    public DlgOrder(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel crtContnt() {
        return new TransparentPanel();
    }
}
