package com.smtech.restaurant.casher.dlg;

import com.smtech.swing.common.dlgs.DlgBase;

import javax.swing.*;
import java.awt.*;

public class DlgWelcome extends DlgBase{

    public DlgWelcome(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel crtContnt() {
        JPanel p = new JPanel();
        p.setBackground(Color.RED);

        return p;
    }
}
