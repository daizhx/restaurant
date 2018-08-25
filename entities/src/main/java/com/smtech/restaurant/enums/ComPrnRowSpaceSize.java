package com.smtech.restaurant.enums;

public enum ComPrnRowSpaceSize{
	STANDARD_MIN("0.5倍"), STANDARD("单倍"), STANDARD_MAX("1.5倍"), DOUBLE_HIGHT("双倍"), TRIPLE("三倍"), QUADRUPLE("四倍");

	String name;
	ComPrnRowSpaceSize(String name) {
		this.name = name;
	}
}
