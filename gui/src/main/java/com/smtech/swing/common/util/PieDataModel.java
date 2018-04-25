package com.smtech.swing.common.util;

//饼图数据模型
public class PieDataModel {
	public String title;
	public String[] key;
	public double[] value;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getKey() {
		return key;
	}
	public void setKey(String[] key) {
		this.key = key;
	}
	public double[] getValue() {
		return value;
	}
	public void setValue(double[] value) {
		this.value = value;
	}
	
}