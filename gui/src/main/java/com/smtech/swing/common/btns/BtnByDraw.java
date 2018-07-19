package com.smtech.swing.common.btns;

import com.smtech.swing.common.view.SelectedAble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用画笔绘制的按钮，减少系统对图片的加载所耗用的内存
 */
public class BtnByDraw extends ButtonWrapper implements SelectedAble {
	public BtnByDraw() {
		super(new JButton());
	}

	public BtnByDraw(AbstractButton btn) {
		super(btn, null);
	}

	public BtnByDraw(AbstractAction action) {
		super(new JButton(action));
	}

	public BtnByDraw(String s) {
		this();
		setText(s);
	}

	public void setPadding(int topPadding, int bottomPadding, int leftPadding,
			int rightPadding) {
		this.topPadding = topPadding;
		this.bottomPadding = bottomPadding;
		this.leftPadding = leftPadding;
		this.rightPadding = rightPadding;
	}

	public void setPadding(int topPadding) {
		this.topPadding = topPadding;
		this.bottomPadding = topPadding;
		this.leftPadding = topPadding;
		this.rightPadding = topPadding;
	}

	public void setBgColor(BgColor color) {
		this.curBgColor = color;
		setCurColor();
	}

	private void setCurColor() {
		BgColor color = curBgColor;
		if (status == ACTION_SELECTED) {
			color = BgColor.RED;
		}
		topColorUp = g_topUpColor.get(color);
		topColorDown = g_topDownColor.get(color);
		if (solid) {
			// 纯色时，上下使用同样的颜色
			bottomColorUp = topColorUp;
			bottomColorDown = topColorDown;
		} else {
			bottomColorUp = g_bottomUpColor.get(color);
			bottomColorDown = g_bottomDownColor.get(color);
		}

		topColorCur = topColorUp;
		bottomColorCur = bottomColorUp;
		validate();
		repaint();
	}

	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		setCurColor();
	}

	@Override
	public boolean isSelected() {
		return false;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(noNeedToDraw) {
			return;
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// 填充上面部分
		g2d.setColor(topColorCur);
		int halfHeight = getHeight() / 2;
		g2d.fillRoundRect(leftPadding, topPadding, getWidth() - rightPadding
				- leftPadding, halfHeight, arcWidth, arcHeight);
		g2d.fillRect(leftPadding, halfHeight - arcWidth, getWidth()
				- rightPadding - leftPadding, arcWidth);

		// 填充下面部分
		g2d.setColor(bottomColorCur);
		g2d.fillRoundRect(leftPadding, halfHeight, getWidth() - rightPadding
				- leftPadding, getHeight() - halfHeight - bottomPadding,
				arcWidth, arcHeight);
		g2d.fillRect(leftPadding, halfHeight, getWidth() - rightPadding
				- leftPadding, arcWidth);

		// 画边框
		if (borderColor == null) {
			return;
		}
		Stroke s = new BasicStroke(borderSize, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER);
		g2d.setStroke(s);
		g2d.setColor(borderColor);
		g2d.drawRoundRect(leftPadding, topPadding, getWidth() - leftPadding
				- rightPadding, getHeight() - topPadding - bottomPadding - 1,
				arcWidth, arcHeight);// 画一个圆角矩形
	}

	@Override
	protected void setPressedBackGroundImage() {
		super.setPressedBackGroundImage();
		topColorCur = topColorDown;
		bottomColorCur = bottomColorDown;
		repaint();
	}

	@Override
	protected void setReleasedBackGroundImage() {
		super.setReleasedBackGroundImage();
		topColorCur = topColorUp;
		bottomColorCur = bottomColorUp;
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderSize(float borderSize) {
		this.borderSize = borderSize;
	}

	public float getBorderSize() {
		return borderSize;
	}

	public void setText(String text) {
		((AbstractButton) getComponet()).setText(text);
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
		repaint();
	}

	public boolean isSolid() {
		return solid;
	}
	
	public boolean isNoNeedToDraw() {
		return noNeedToDraw;
	}

	public void setNoNeedToDraw(boolean noNeedToDraw) {
		this.noNeedToDraw = noNeedToDraw;
	}


	private Color topColorCur = g_topUpColor.get(BgColor.RED);// 顶部颜色（正在使用的）
	private Color bottomColorCur = g_bottomUpColor.get(BgColor.RED);// 底部颜色（正在使用的）
	private Color topColorUp = g_topUpColor.get(BgColor.RED);// 顶部颜色（未按下）
	private Color bottomColorUp = g_bottomUpColor.get(BgColor.RED);// 底部颜色（未按下）
	private Color topColorDown = g_topUpColor.get(BgColor.RED);// 顶部颜色（按下）
	private Color bottomColorDown = g_bottomDownColor.get(BgColor.RED);// 底部颜色（按下）
	private BgColor curBgColor;
	private int arcWidth = 15;// 垂直方向的弧度
	private int arcHeight = 15;// 水平方向的弧度
	private int topPadding = 2;// 顶部空隙
	private int bottomPadding = 2;// 底部空隙
	private int leftPadding = 2;// 左边空隙
	private int rightPadding = 2;// 右边空隙
	private Color borderColor;// 边框颜色，没有设置表示不需要边框
	private float borderSize = 1f;// 边框大小
	private boolean solid = false;// 纯色（上面和下面使用同样的颜色）
	private boolean noNeedToDraw = false; // 不需要绘制

	private static Map<BgColor, Color> g_topUpColor = new HashMap<BgColor, Color>();
	private static Map<BgColor, Color> g_bottomUpColor = new HashMap<BgColor, Color>();
	private static Map<BgColor, Color> g_topDownColor = new HashMap<BgColor, Color>();
	private static Map<BgColor, Color> g_bottomDownColor = new HashMap<BgColor, Color>();
	static {
		// 蓝色
		g_topUpColor.put(BgColor.BLUE, new Color(27, 127, 171));
		g_bottomUpColor.put(BgColor.BLUE, new Color(6, 106, 156));
		g_topDownColor.put(BgColor.BLUE, new Color(20, 93, 130));
		g_bottomDownColor.put(BgColor.BLUE, new Color(5, 77, 115));

		// 青色
		g_topUpColor.put(BgColor.CYAN, new Color(45, 209, 209));
		g_bottomUpColor.put(BgColor.CYAN, new Color(27, 202, 202));
		g_topDownColor.put(BgColor.CYAN, new Color(41, 170, 170));
		g_bottomDownColor.put(BgColor.CYAN, new Color(23, 168, 168));

		// 羊红色
		g_topUpColor.put(BgColor.FUCKSIN, new Color(238, 53, 238));
		g_bottomUpColor.put(BgColor.FUCKSIN, new Color(238, 31, 238));
		g_topDownColor.put(BgColor.FUCKSIN, new Color(185, 46, 185));
		g_bottomDownColor.put(BgColor.FUCKSIN, new Color(177, 24, 177));

		// 灰度1
		g_topUpColor.put(BgColor.GRAYSCALE1, new Color(215, 215, 215));
		g_bottomUpColor.put(BgColor.GRAYSCALE1, new Color(205, 205, 205));
		g_topDownColor.put(BgColor.GRAYSCALE1, new Color(190, 190, 190));
		g_bottomDownColor.put(BgColor.GRAYSCALE1, new Color(185, 185, 185));

		// 灰度2
		g_topUpColor.put(BgColor.GRAYSCALE2, new Color(190, 190, 190));
		g_bottomUpColor.put(BgColor.GRAYSCALE2, new Color(185, 185, 185));
		g_topDownColor.put(BgColor.GRAYSCALE2, new Color(114, 114, 114));
		g_bottomDownColor.put(BgColor.GRAYSCALE2, new Color(102, 102, 102));

		// 灰度3
		g_topUpColor.put(BgColor.GRAYSCALE3, new Color(114, 114, 114));
		g_bottomUpColor.put(BgColor.GRAYSCALE3, new Color(102, 102, 102));
		g_topDownColor.put(BgColor.GRAYSCALE3, new Color(67, 67, 67));
		g_bottomDownColor.put(BgColor.GRAYSCALE3, new Color(51, 51, 51));

		// 灰度4
		g_topUpColor.put(BgColor.GRAYSCALE4, new Color(67, 67, 67));
		g_bottomUpColor.put(BgColor.GRAYSCALE4, new Color(51, 51, 51));
		g_topDownColor.put(BgColor.GRAYSCALE4, new Color(32, 32, 32));
		g_bottomDownColor.put(BgColor.GRAYSCALE4, new Color(13, 13, 13));

		// 绿色
		g_topUpColor.put(BgColor.GREEN, new Color(20, 197, 39));
		g_bottomUpColor.put(BgColor.GREEN, new Color(0, 197, 16));
		g_topDownColor.put(BgColor.GREEN, new Color(15, 154, 28));
		g_bottomDownColor.put(BgColor.GREEN, new Color(0, 153, 12));

		// 橙色
		g_topUpColor.put(BgColor.ORANGE, new Color(223, 139, 38));
		g_bottomUpColor.put(BgColor.ORANGE, new Color(217, 127, 17));
		g_topDownColor.put(BgColor.ORANGE, new Color(184, 102, 34));
		g_bottomDownColor.put(BgColor.ORANGE, new Color(179, 93, 12));

		// 粉红色
		g_topUpColor.put(BgColor.PINK, new Color(242, 144, 144));
		g_bottomUpColor.put(BgColor.PINK, new Color(242, 130, 130));
		g_topDownColor.put(BgColor.PINK, new Color(213, 117, 117));
		g_bottomDownColor.put(BgColor.PINK, new Color(209, 109, 109));

		// 红色
		g_topUpColor.put(BgColor.RED, new Color(225, 60, 66));
		g_bottomUpColor.put(BgColor.RED, new Color(218, 17, 25));
		g_topDownColor.put(BgColor.RED, new Color(189, 34, 34));
		g_bottomDownColor.put(BgColor.RED, new Color(180, 13, 18));

		// 灰色
		g_topUpColor.put(BgColor.GRAY, new Color(241, 241, 241));
		g_bottomUpColor.put(BgColor.GRAY, new Color(229, 229, 229));
		g_topDownColor.put(BgColor.GRAY, new Color(225, 60, 66));
		g_bottomDownColor.put(BgColor.GRAY, new Color(218, 17, 25));

		// 黄色
		g_topUpColor.put(BgColor.YELLOW, new Color(215, 215, 39));
		g_bottomUpColor.put(BgColor.YELLOW, new Color(207, 207, 20));
		g_topDownColor.put(BgColor.YELLOW, new Color(173, 173, 35));
		g_bottomDownColor.put(BgColor.YELLOW, new Color(175, 175, 18));

		// 淡蓝色
		g_topUpColor.put(BgColor.PALE_BULE, new Color(200, 237, 255));
		g_bottomUpColor.put(BgColor.PALE_BULE, new Color(200, 237, 255));
		g_topDownColor.put(BgColor.PALE_BULE, new Color(200, 237, 255));		
		g_bottomDownColor.put(BgColor.PALE_BULE, new Color(200, 237, 255));
		
	}

	
	/**
	 * 把btn的事件传递到底部面板上，
	 * 如果调用了这个方法，不要再调用setAction
	 */
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		if(l == null){
			return;
		}
		
		super.addMouseListener(l);
//		for (MouseListener ml : btn.getMouseListeners()) {
//			removeMouseListener(ml);
//		}
//		btn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				for (MouseListener ml : getMouseListeners()) {
//					if(ml != null){
//						ml.mouseClicked(e);
//					}
//				}
//
//			}
//		});
	}
}