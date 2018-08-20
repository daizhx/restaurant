package com.smtech.swing.common.util;

import javax.swing.*;
import java.awt.*;

//ui层面上的一些公共方法
public class UIUtil {

    /**
     * 固定某控件的大小
     *
     * @param component
     * @param size
     */
    public static void fixSize(JComponent component, Dimension size) {
        component.setPreferredSize(size);
        component.setMaximumSize(size);
        component.setMinimumSize(size);
        component.setSize(size);
    }
}
