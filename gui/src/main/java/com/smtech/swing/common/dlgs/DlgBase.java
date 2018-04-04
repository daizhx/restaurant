package com.smtech.swing.common.dlgs;

import javax.swing.*;
import java.awt.*;

public abstract class DlgBase extends JDialog{

    public DlgBase() {
        this(null);
    }

    public DlgBase(Window owner) {
        super(owner);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        setContentPane(crtContnt());
    }

    protected abstract Container crtContnt();
}
