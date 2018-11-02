package com.smtech.restaurant.order;

import java.awt.*;

public interface Constants {

    String FONT = "SimKai";
    String FONT_SimSun = "SimSun";
    String FONT_Simkai = "SimKai";
    String FONT_SHEET = "SimKai";

    // 颜色
    Color TEXT_COLOR = Color.BLACK;
    Color TEXT_COLOR_LIGHT = new Color(0x999999);
    Color TEXT_COLOR_REVERSE = Color.WHITE;
    Color COLOR_STATUS_BG = Color.RED;


    //尺寸
    int FONT_SIZE_INPUT = 24;
    int gapInBorder = 10;

    //按钮字体
    Font fontInBtn =  new Font(FONT, Font.PLAIN, 18);
    //标题字体
    Font fontInTitle = new Font(FONT, Font.PLAIN, 30);
    // 文本框中的字体
    Font fontInTextBox = new Font(FONT, Font.PLAIN, FONT_SIZE_INPUT);
    //标签字体
    Font fontInLabel = new Font(FONT, Font.PLAIN, 18);

}
