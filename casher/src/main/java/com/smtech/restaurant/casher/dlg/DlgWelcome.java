package com.smtech.restaurant.casher.dlg;

import com.smtech.swing.common.dlgs.DlgBase;

import javax.swing.*;
import java.awt.*;

public class DlgWelcome extends DlgBase{

    public DlgWelcome(Window owner) {
        super(owner);
    }

    @Override
    protected Container crtContnt() {
        return new JLabel("WELCOME");
    }
}
