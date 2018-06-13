package com.smtech.restaurant.casher.dlg;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.panel.ViewGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DlgWelcome extends DlgBase{

    public DlgWelcome(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel onCrtContntView() {
        ActionListener a;
        JPanel p = new JPanel(new GridLayout());
        p.setBorder(BorderFactory.createEmptyBorder(200,400,200,400));
//            Image img = ImageIO.read(getClass().getResource("/images/zffs_bg.png"));
            ViewGroup jp = new ViewGroup();
            jp.setBackgroundImage("/images/zffs_bg.png", ViewGroup.SCALED);
            jp.setPreferredSize(new Dimension(100,100));
            p.add(jp,BorderLayout.NORTH);

        p.setBackground(Color.WHITE);

        return p;
    }
}
