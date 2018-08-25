package com.smtech.restaurant.enums;

public enum PrinterStatus{
	ZC("正常"), QZ("缺纸"), WHG("未盒盖"), GZ("故障");

	String name;
	PrinterStatus(String name) {
		this.name = name;
	}
}
