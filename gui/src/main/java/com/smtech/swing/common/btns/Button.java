package com.smtech.swing.common.btns;

import javax.swing.*;

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
}
