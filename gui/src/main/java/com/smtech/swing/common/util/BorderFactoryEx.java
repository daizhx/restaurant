package com.smtech.swing.common.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class BorderFactoryEx {
	/**
	 * 圆角边框
	 */
	private static class RoundedBorderLine extends AbstractBorder {

		private final static int MARGIN = 10;
		private final static int MARGIN_TOP_BOTTOM = 5;
		private static final long serialVersionUID = 1L;
		private Color color;

		public RoundedBorderLine(Color clr) {
			color = clr;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);
			g.drawRoundRect(x, y, width, height, MARGIN, MARGIN);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(MARGIN_TOP_BOTTOM, MARGIN, MARGIN_TOP_BOTTOM, MARGIN);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = MARGIN;
			insets.top = MARGIN_TOP_BOTTOM;
			insets.right = MARGIN;
			insets.bottom = MARGIN_TOP_BOTTOM;
			return insets;
		}
	}

	/**
	 * 上下左右，能分别设置不同颜色，不通粗细的边框
	 */
	private static class BorderCanSetMoreColor extends AbstractBorder {
		private BorderSetting setting;

		public BorderCanSetMoreColor(BorderSetting setting) {
			this.setting = setting;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// 绘制上边线条
			g.setColor(setting.topColor);
			g.drawLine(x, y, x + width, y);
			// 绘制下边线条
			g.setColor(setting.bottomColor);
			g.drawLine(x, y + height - 1, x + width, y + height - 1);
			// 绘制左边线条
			g.setColor(setting.leftColor);
			g.drawLine(x, y, x, y + height);
			// 绘制右边线条
			g.setColor(setting.rightColor);
			g.drawLine(x + width - 1, y, x + width - 1, y + height);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return setting.insets;
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = setting.insets.left;
			insets.top = setting.insets.top;
			insets.right = setting.insets.right;
			insets.left = setting.insets.left;
			return insets;
		}

	}

	public static class BorderSetting {
		// 上下左右颜色设置
		public Color topColor = Color.BLACK;
		public Color bottomColor = Color.BLACK;
		public Color leftColor = Color.BLACK;
		public Color rightColor = Color.BLACK;
		// 上下左右粗细设置
		public Insets insets = new Insets(0, 0, 0, 0);
	}

	/**
	 * 创建圆角边框
	 * 
	 * @return
	 */
	public static Border crtRoundedBorder() {
		return crtRoundedBorder(defColor);
	}

	/**
	 * 创建圆角边框
	 * 
	 * @param empty
	 * @return
	 */
	public static Border crtRoundedBorder(Border empty) {
		return crtRoundedBorder(empty, defColor);
	}

	/**
	 * 创建圆角边框
	 * 
	 * @param color
	 * @return
	 */
	public static Border crtRoundedBorder(Color color) {
		Border empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		CompoundBorder compound = BorderFactory.createCompoundBorder(empty, new RoundedBorderLine(color));
		return compound;
	}

	/**
	 * 创建圆角边框，自定义外边框
	 * 
	 * @param empty
	 * @param color
	 * @return
	 */
	public static Border crtRoundedBorder(Border empty, Color color) {
		CompoundBorder compound = BorderFactory.createCompoundBorder(empty, new RoundedBorderLine(color));
		return compound;
	}

	/**
	 * 创建不同颜色，不同内边距的边框
	 * 
	 * @return
	 */
	public static Border crtCanSetMoreColorBorder(BorderSetting setting) {
		Border empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		CompoundBorder compound = BorderFactory.createCompoundBorder(empty, new BorderCanSetMoreColor(setting));
		return compound;
	}

	public static Border crtCanSetMoreColorBorder(Border empty, BorderSetting setting) {
		CompoundBorder compound = BorderFactory.createCompoundBorder(empty, new BorderCanSetMoreColor(setting));
		return compound;
	}

	public static Color defColor = new Color(181, 181, 181);
}