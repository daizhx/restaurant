package com.smtech.swing.common.layout;

import com.smtech.swing.common.panel.ViewGroup;

import javax.swing.*;
import java.awt.*;

public class LinearLayout extends ViewGroup {

    private int oritation = ViewGroup.VERTICAL;

    public LinearLayout() {
    }

    public void setOrientation(int oritation){
        this.oritation = oritation;
        if(oritation == ViewGroup.HORIZONTAL){
            setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        }else{
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        }
    }

    public Component add(JComponent comp) {
        if(oritation == ViewGroup.VERTICAL){
            comp.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        return super.add(comp);
    }


    public void addHorizontalGlue(){
        add(Box.createHorizontalGlue());
    }


}
