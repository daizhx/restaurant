package com.smtech.swing.common.view;

import javax.swing.*;

public class TextView extends JLabel {
    public TextView(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    public TextView(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public TextView(String text) {
        super(text);
    }

    public TextView(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public TextView(Icon image) {
        super(image);
    }

    public TextView() {
    }

    public void setPadding(int left,int top ,int right,int bottom){
        setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
    }

}
