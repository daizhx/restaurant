package com.smtech.swing.common.btns;

import com.smtech.swing.common.Res;

import javax.swing.*;
import java.awt.*;

/**
 * 基本的按钮控件
 */
public class XButton extends JButton {

    public XButton() {
    }

    public XButton(Icon icon) {
        super(icon);
    }

    public XButton(String text) {
        super(text);
    }

    public XButton(Action a) {
        super(a);
    }

    public XButton(String text, Icon icon) {
        super(text, icon);
    }

    public void setTextSize(int size){
        setFont(new Font(Res.FONT, Font.PLAIN,size));
    }

    public static XButton crtNormalBtn(){
        XButton btn = new XButton();
        btn.setTextSize(Res.FONT_SIZE_BTN);
        return btn;
    }
}
