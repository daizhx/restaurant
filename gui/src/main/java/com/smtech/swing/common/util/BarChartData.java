package com.smtech.swing.common.util;

//柱状图数据模型
public class BarChartData {
	//标题
	private String title;
	//x轴标签
	private String xLabel;
	//y轴标签
	private String yLabel;
	//x轴key值
	private String[] xAxisKeys;
	//y轴值
	private double[] yAxisVals;
	//类别
	private String[] category;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getxLabel() {
		return xLabel;
	}
	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}
	public String getyLabel() {
		return yLabel;
	}
	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}
	public String[] getxAxisKeys() {
		return xAxisKeys;
	}
	public void setxAxisKeys(String[] xAxisKeys) {
		this.xAxisKeys = xAxisKeys;
	}

	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public double[] getyAxisVals() {
		return yAxisVals;
	}
	public void setyAxisVals(double[] yAxisVals) {
		this.yAxisVals = yAxisVals;
	}
	
	
}