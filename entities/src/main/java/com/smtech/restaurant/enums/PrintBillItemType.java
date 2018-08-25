package com.smtech.restaurant.enums;

public enum PrintBillItemType{
	Comment("注释"), SqlQuery("SQL查询"), Line("横线"), Text("文本"), BlankLine("空行"), CutPaper("切纸"), OpenCashBox("开钱箱"), QRCodeBar("二维码"), BarCode("条形码"), Img("图片");

	String name;
	PrintBillItemType(String name) {
		this.name = name;
	}
}
