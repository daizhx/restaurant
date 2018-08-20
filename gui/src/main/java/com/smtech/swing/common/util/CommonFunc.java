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
}
