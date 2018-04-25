package com.smtech.swing.common.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 使用画笔绘制的面板，减少系统对图片的加载所耗用的内存
 */
public class PaneByDraw extends JPanel {
	public PaneByDraw() {
		super();
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		if (translucent) {
			// 半透明
			g2d.setColor(new Color(0, 0, 0, 40));
		} else {
			g2d.setColor(getBackground());
		}

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (bottomBorderSize == 0) {
			g2d.fillRoundRect(leftMargin, topMargin, getWidth() - leftMargin
					- rightMargin, getHeight() - topMargin - bottomMargin,
					arcWidth, arcHeight);
		} else {
			g2d.fillRoundRect(leftMargin, topMargin, getWidth() - leftMargin
					- rightMargin, getHeight() - topMargin - bottomMargin - 1,
					arcWidth, arcHeight);
		}

		// 画边框
		if (borderColor == null) {
			return;
		}

		if (arcWidth == 0 && arcHeight == 0) {
			// 顶部边框
			Stroke s;
			if (topBorderSize > 0) {
				s = new BasicStroke(topBorderSize, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER);
				g2d.setStroke(s);
				g2d.setColor(borderColor);
				g2d.drawLine(leftMargin, topMargin, getWidth() - rightMargin,
						topMargin);
			}
			// 底部边框
			if (bottomBorderSize > 0) {
				s = new BasicStroke(bottomBorderSize, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER);
				g2d.setStroke(s);
				if (bottomBorderSize == 0) {
					g2d.setColor(getBackground());
				} else {
					g2d.setColor(borderColor);
				}
				g2d.drawLine(leftMargin, getHeight() - bottomMargin, getWidth()
						- rightMargin, getHeight() - bottomMargin);
			}

			// 左边框
			if (leftBorderSize > 0) {
				s = new BasicStroke(leftBorderSize, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER);
				g2d.setStroke(s);
				if (leftBorderSize == 0) {
					g2d.setColor(getBackground());
				} else {
					g2d.setColor(borderColor);
				}
				g2d.drawLine(leftMargin, topMargin, leftMargin, getHeight()
						- bottomMargin);
			}

			// 右部边框
			if (rightBorderSize > 0) {
				s = new BasicStroke(rightBorderSize, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_MITER);
				g2d.setStroke(s);
				if (rightBorderSize == 0) {
					g2d.setColor(getBackground());
				} else {
					g2d.setColor(borderColor);
				}
				g2d.drawLine(getWidth() - rightMargin, topMargin, getWidth()
						- rightMargin, getHeight() - bottomMargin);
			}
		} else {
			Stroke s = new BasicStroke(borderSize, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_MITER);
			g2d.setStroke(s);
			g2d.setColor(borderColor);
			g2d.drawRoundRect(leftMargin, topMargin, getWidth() - leftMargin
					- rightMargin, getHeight() - topMargin - bottomMargin - 1,
					arcWidth, arcHeight);// 画一个圆角矩形
		}

	}

	/**
	 * 设置外边距
	 * 
	 * @param margin
	 */
	public void setMargin(int margin) {
		this.setMargin(margin, margin, margin, margin);
	}

	/**
	 * 设置外边距
	 * 
	 * @param topMargin
	 * @param bottomMargin
	 * @param leftMargin
	 * @param rightMargin
	 */
	public void setMargin(int topMargin, int leftMargin, int bottomMargin,
			int rightMargin) {
		this.topMargin = topMargin;
		this.leftMargin = leftMargin;
		this.bottomMargin = bottomMargin;
		this.rightMargin = rightMargin;
		genMarginAndPadding();
	}

	/**
	 * 设置内边距
	 * 
	 * @param padding
	 */
	public void setPadding(int padding) {
		this.setPadding(padding, padding, padding, padding);
	}


	/**
	 * 设置内边距
	 * @param topPadding
	 * @param leftPadding
	 * @param bottomPadding
	 * @param rightPadding
	 */
	public void setPadding(int topPadding, int leftPadding, int bottomPadding,
			int rightPadding) {
		this.topPadding = topPadding;
		this.leftPadding = leftPadding;
		this.bottomPadding = bottomPadding;
		this.rightPadding = rightPadding;
		genMarginAndPadding();
	}

	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}

	public int getArcWidth() {
		return arcWidth;
	}

	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}

	public int getArcHeight() {
		return arcHeight;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderSize(float borderSize) {
		this.borderSize = borderSize;
		this.topBorderSize = borderSize;
		this.leftBorderSize = borderSize;
		this.bottomBorderSize = borderSize;
		this.rightBorderSize = borderSize;
	}

	public float getBorderSize() {
		return borderSize;
	}

	public void setTranslucent(boolean translucent) {
		this.translucent = translucent;
		this.borderColor = Color.BLACK;
		this.borderSize = 0.5f;
		repaint();
	}

	public boolean isTranslucent() {
		return translucent;
	}

	/**
	 * 处理内外边距
	 */
	private void genMarginAndPadding() {
		int top = topMargin + topPadding;
		if (topPadding == -1) {
			top = topMargin + arcHeight;
		}

		int left = leftMargin + leftPadding;
		if (leftPadding == -1) {
			left = leftMargin + arcWidth;
		}

		int bottom = bottomMargin + bottomPadding;
		if (bottomPadding == -1) {
			bottom = bottomMargin + arcHeight;
		}

		int right = rightMargin + rightPadding;
		if (rightPadding == -1) {
			right = rightMargin + arcWidth;
		}
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}

	public void setTopBorderSize(float topBorderSize) {
		this.topBorderSize = topBorderSize;
	}

	public float getTopBorderSize() {
		return topBorderSize;
	}

	public void setLeftBorderSize(float leftBorderSize) {
		this.leftBorderSize = leftBorderSize;
	}

	public float getLeftBorderSize() {
		return leftBorderSize;
	}

	public void setBottomBorderSize(float bottomBorderSize) {
		this.bottomBorderSize = bottomBorderSize;
	}

	public float getBottomBorderSize() {
		return bottomBorderSize;
	}

	public void setRightBorderSize(float rightBorderSize) {
		this.rightBorderSize = rightBorderSize;
	}

	public float getRightBorderSize() {
		return rightBorderSize;
	}

	private boolean translucent = false;// 半透明
	private int arcWidth = 15;// 垂直方向的弧度
	private int arcHeight = 15;// 水平方向的弧度
	private int topMargin = 2;// 顶部外边距
	private int bottomMargin = 2;// 底部外边距
	private int leftMargin = 2;// 左边外边距
	private int rightMargin = 2;// 右边外边距

	private int topPadding = -1;// 顶部内边距
	private int bottomPadding = -1;// 底部内边距
	private int leftPadding = -1;// 左边内边距
	private int rightPadding = -1;// 右边内边距

	private Color borderColor;// 边框颜色，没有设置表示不需要边框
	private float borderSize = 1f;// 边框大小
	private float topBorderSize = 1f;
	private float leftBorderSize = 1f;
	private float bottomBorderSize = 1f;
	private float rightBorderSize = 1f;

}