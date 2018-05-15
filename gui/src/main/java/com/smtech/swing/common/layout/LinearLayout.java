package com.smtech.swing.common.layout;

import com.smtech.swing.common.panel.View;

import javax.swing.*;
import java.awt.*;

public class LinearLayout extends View {

    private int oritation = View.VERTICAL;

    public LinearLayout() {
    }

    public void setOrientation(int oritation){
        this.oritation = oritation;
        if(oritation == View.HORIZONTAL){
            setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        }else{
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        }
    }

    public Component add(JComponent comp) {
        if(oritation == View.VERTICAL){
            comp.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        return super.add(comp);
    }


    public void addHorizontalGlue(){
        add(Box.createHorizontalGlue());
    }

    public void setPadding(int left,int top ,int right,int bottom){
        setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
    }
}
