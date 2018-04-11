package com.smtech.swing.common.dlgs;

import javax.swing.*;
import java.awt.*;

/**
 * 全屏对话框
 */
public abstract class DlgBase extends JDialog{

    public DlgBase(Window owner) {
        super(owner);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel content = crtContnt();
        content.setOpaque(true);
        setContentPane(content);

        // 设置对话框属性
//        setUndecorated(true);
        setModal(true);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        pack();
    }

    protected abstract JPanel crtContnt();
}
