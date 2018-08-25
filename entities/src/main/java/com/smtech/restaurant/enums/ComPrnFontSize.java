package com.smtech.restaurant.enums;

public enum ComPrnFontSize{
	STANDARD("标准"), DOUBLE_HIGHT("倍高"), DOUBLE_WIDTH("倍宽"), DOUBLE_BOTH("双倍"), TRIPLE("三倍"), QUADRUPLE("四倍");

	String name;
	ComPrnFontSize(String name) {
		this.name = name;
	}
}
