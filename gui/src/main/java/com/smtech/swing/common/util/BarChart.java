package com.smtech.swing.common.util;

import com.smtech.swing.common.Res;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;


public final class BarChart {

	ChartPanel frame1;

	public BarChart(BarChartData bcd) {
		JFreeChart chart = ChartFactory.createBarChart(bcd.getTitle(), // 图表标题
				bcd.getxLabel(), // 目录轴的显示标签
				bcd.getyLabel(), // 数值轴的显示标签
				getDataSet(bcd), // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);

		new StandardBarPainter();
		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font(Res.FONT, Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font(Res.FONT, Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font(Res.FONT, Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font(Res.FONT, Font.BOLD, 15));
		chart.getTitle().setFont(new Font(Res.FONT, Font.BOLD, 20));// 设置标题字体
		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

	}

	//test code
	private static CategoryDataset getDataSet(BarChartData data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String[] category = data.getCategory();
		String[] x = data.getxAxisKeys();
		
		for(int i=0;i<x.length;i++){
			double v = data.getyAxisVals()[i];
			for(int j=0;j<category.length;j++){
				dataset.addValue(v, category[j], x[i]);
			}
		}
//		dataset.addValue(100, "北京", "苹果");
//		dataset.addValue(100, "上海", "苹果");
//		dataset.addValue(100, "广州", "苹果");
//		dataset.addValue(200, "北京", "梨子");
//		dataset.addValue(200, "上海", "梨子");
//		dataset.addValue(200, "广州", "梨子");
//		dataset.addValue(300, "北京", "葡萄");
//		dataset.addValue(300, "上海", "葡萄");
//		dataset.addValue(300, "广州", "葡萄");
//		dataset.addValue(400, "北京", "香蕉");
//		dataset.addValue(400, "上海", "香蕉");
//		dataset.addValue(400, "广州", "香蕉");
//		dataset.addValue(500, "北京", "荔枝");
//		dataset.addValue(500, "上海", "荔枝");
//		dataset.addValue(500, "广州", "荔枝");
		return dataset;
	}

	public JPanel getChartPanel() {
		return frame1;

	}

}