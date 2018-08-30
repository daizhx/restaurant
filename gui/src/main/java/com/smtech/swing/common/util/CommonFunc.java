package com.smtech.swing.common.util;

import java.awt.*;

public class CommonFunc {
    /**
     * 获取屏幕可用的最大范围除去周边的间隔
     *
     * @return
     */
    static public Dimension getScreenMaxValidSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension fullSize = tk.getScreenSize();
        Insets insets = tk.getScreenInsets(GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration());

        return new Dimension(fullSize.width - insets.left - insets.right,
                fullSize.height - insets.bottom - insets.top);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }
}
