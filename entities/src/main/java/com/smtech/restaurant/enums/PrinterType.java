package com.smtech.restaurant.enums;

public enum PrinterType{
	OPOS_NET("网口指令打印机"), Com("串口指令打印机"), OPOS_LPT("并口指令打印机"), OPOS_USB("USB口指令打印机"), Driver("驱动打印机");

	String name;
	PrinterType(String name) {
		this.name = name;
	}
}
