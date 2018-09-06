package com.smtech.swing.common.util;

import com.smtech.swing.common.ImageManager;

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

    //通用字体
    public static String getComFontName() {
        return "Dialog";
    }


    /**
     * 为按钮添加热键
     *
     * @param btn
     * @param key
     */
    public static void addHotKeyForBtn(AbstractButton btn, int key) {
        InputMap imap = btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = btn.getActionMap();
        imap.put(KeyStroke.getKeyStroke(key, 0), btn.getText());
        amap.put(btn.getText(), btn.getAction());
    }


    /**
     * 设置某容器内所有控件的字体
     *
     * @param container
     */
    public static void setComponentsFont(Container container, Font font) {
        for (Component c : container.getComponents()) {
            if (c instanceof Container) {
                setComponentsFont((Container) c, font);
            }
            c.setFont(font);
        }
    }

    /**
     * 创建按钮
     * @param action
     * @param key
     * @return
     */
    public static JButton createBtn(AbstractAction action, int key) {
        JButton btn = new JButton(action);
        addHotKeyForBtn(btn, key);
        return btn;
    }

    //创建图标
    public static ImageIcon createImageIcon(String filename) {
        String path = "Icon/" + filename;
        return ImageManager.getImgIcon(path);
    }
}
