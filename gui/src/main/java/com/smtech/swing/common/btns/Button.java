package com.smtech.swing.common.btns;

import com.smtech.swing.common.Res;

import javax.swing.*;
import java.awt.*;

/**
 */
public class Button extends JButton {

    public Button() {
    }

    public Button(Icon icon) {
        super(icon);
    }

    public Button(String text) {
        super(text);
    }

    public Button(Action a) {
        super(a);
    }

    public Button(String text, Icon icon) {
        super(text, icon);
    }

    public void setTextSize(int size){
        setFont(new Font(Res.FONT, Font.PLAIN,size));
    }

    public static Button crtNormalBtn(){
        Button btn = new Button();
        btn.setTextSize(Res.FONT_SIZE_BTN);
        return btn;
    }
}
