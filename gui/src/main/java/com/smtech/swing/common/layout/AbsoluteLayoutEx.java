package com.smtech.swing.common.layout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * 绝对位置布局
 */
public class AbsoluteLayoutEx {
	public AbsoluteLayoutEx() {
	}

	/**
	 * 增加控件
	 * 
	 * @param child 控件
	 * @param idx 该控件对应的坐标
	 */
	public void add(Component child, long idx) {
		childs.add(child);
		idxs.add(idx);
	}

	/**
	 * 增加间隙
	 * 
	 * @param idx 间隙对应的坐标
	 */
	public void addGlue(int idx) {
		add(null, idx);
	}

	/**
	 * 设定布局方向
	 * 
	 * @param orientation SwingConstants.VERTICAL：垂直方向 <BR>
	 *            SwingConstants.HORIZONTAL水平方向
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public JPanel doLayout() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		doLayout(panel);
		return panel;
	}

	public void doLayout(JPanel panel) {
		GridBagLayoutAdp gl = new GridBagLayoutAdp();
		gl.setOrientation(orientation);
		long curPos = 0;
		for (int i = 0; i < childs.size(); i++) {
			Component child = childs.get(i);
			long idx = idxs.get(i) - beginIndex;
			gl.add(child, Long.valueOf(idx - curPos).intValue());
			curPos = idx;
		}
		gl.doLayout(panel);
		childs.clear();
		idxs.clear();
		beginIndex = 0;
	}

	public void setBeginIndex(int index) {
		this.beginIndex = index;
	}

	private int beginIndex = 0;
	private List<Component> childs = new Vector<Component>();
	private List<Long> idxs = new Vector<Long>();
	private int orientation = SwingConstants.HORIZONTAL;
}