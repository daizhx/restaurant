package com.smtech.swing.common.view;

import javax.swing.*;
import java.awt.im.InputMethodRequests;

/**
 * 本类解决问题：输入中文时，编辑框中出现拼音
 */
public class TextFieldEx extends JTextField {
    public InputMethodRequests getInputMethodRequests() {
        return null;
    }
}
