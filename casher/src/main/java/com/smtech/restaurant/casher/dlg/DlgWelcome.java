package com.smtech.restaurant.casher.dlg;

import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.panel.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DlgWelcome extends DlgBase{

    public DlgWelcome(Window owner) {
        super(owner);
    }

    @Override
    protected JPanel onCrtContntView() {
        ActionListener a;
        JPanel p = new JPanel(new GridLayout());
        p.setBorder(BorderFactory.createEmptyBorder(200,400,200,400));
        try {
            Image img = ImageIO.read(getClass().getResource("/images/zffs_bg.png"));
            View jp = new View(img, View.SCALED);
            jp.setPreferredSize(new Dimension(100,100));
            p.add(jp,BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setBackground(Color.WHITE);

        return p;
    }
}
