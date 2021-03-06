package com.smtech.swing.common.util;

import java.awt.*;
import java.math.BigDecimal;

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


    /**
     * 创建步长为1的新字符
     *
     * @param str
     * @return
     */
    public static String createIncStr(String str) {
        if (str == null || str.equals("")) {
            return null;
        }

        String ret = "";
        Boolean needInc = true;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (needInc) {
                if (!(c >= '0' && c <= '9') && !(c >= 'A' && c <= 'Z')) {
                    return null;
                }
                if (c == '9') {
                    if (i == 0) {
                        c = 'A';
                        needInc = false;
                    } else {
                        c = '0';
                        needInc = true;
                    }
                } else if (c == 'Z') {
                    c = '0';
                    needInc = true;
                } else {
                    c++;
                    needInc = false;
                }
            }
            ret = c + ret;
        }
        return ret;
    }


    /**
     * 保留小数
     *
     * @param b
     * @return
     */
    public static String formateWithDecimal(BigDecimal b) {
        if (b == null) {
            return "0";
        }
        String str = b.toString();
        if (str.equals("0E-10")) {
            str = "0";
        }
        int idx = str.indexOf(".");
        if (idx == -1) {
            return str;
        }
        String strInt = str.substring(0, idx);
        String strDouble = str.substring(idx + 1);
        String temp = "";
        Boolean found = false;
        for (int i = strDouble.length() - 1; i >= 0; i--) {
            char ch = strDouble.charAt(i);
            if (ch != '0') {
                found = true;
                temp = String.format("%c%s", ch, temp);
            } else {
                if (found) {
                    temp = String.format("%c%s", ch, temp);
                }
            }
        }
        if (temp.equals("")) {
            return strInt;
        }
        return String.format("%s.%s", strInt, temp);
    }
}
