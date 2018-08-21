package com.smtech.swing.common.layout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * 模拟GridBagLayout，增加了平均分配的布局功能
 */
public class GridBagLayoutEx {

	public GridBagLayoutEx(int row, int col) {
		this(row, col, new Insets(0, 0, 0, 0));
	}

	public GridBagLayoutEx(int row, int col, Insets insets) {
		this.row = col;
		this.col = row;
		this.insets = insets;
	}

	/**
	 * 增加控件
	 * 
	 * @param component 控件
	 * @param colIdx x轴的位置(列)
	 * @param rowIdx y轴的位置(行)
	 * @param gridwidth x轴的权重
	 * @param gridheight y轴的权重
	 */
	public void addComponent(Component component, int colIdx, int rowIdx, int gridwidth, int gridheight) {
		components.add(component);
		rowIdxs.add(colIdx);
		colIdxs.add(rowIdx);
		gridwidths.add(gridwidth);
		gridheights.add(gridheight);
		if (insets != null) {
			insetsList.add(insets);
		}
	}

	public void addComponent(Component component, int colIdx, int rowIdx, int gridwidth, int gridheight, Insets inset) {
		components.add(component);
		rowIdxs.add(colIdx);
		colIdxs.add(rowIdx);
		gridwidths.add(gridwidth);
		gridheights.add(gridheight);
		insetsList.add(inset);
	}

	public void addInOneColumn(Component component) {
		int i = components.size();
		components.add(component);
		rowIdxs.add(0);
		colIdxs.add(i);
		gridwidths.add(1);
		gridheights.add(1);
		if (insets != null) {
			insetsList.add(insets);
		}
	}

	public void addInOneRow(Component component) {
		int i = components.size();
		components.add(component);
		rowIdxs.add(i);
		colIdxs.add(0);
		gridwidths.add(1);
		gridheights.add(1);
		if (insets != null) {
			insetsList.add(insets);
		}
	}

	public void doLayout(JPanel panel) {
		panel.removeAll();
		panel.setLayout(new GridBagLayout());
		// panel.add(Box.createGlue(), new GridBagConstraints(0, 0, 1, 1, 0.0,
		// 0.0, anchor, fill, new Insets(0, 0, 0, 0), 0, 0));
		// 添加分隔x轴的填充物
		GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, anchor, fill, new Insets(0, 0, 0, 0), 0, 0);
		for (int i = 1; i <= row; i++) {
			c.gridx = i;
			c.gridy = 0;
			panel.add(Box.createHorizontalGlue(), c);
		}
		// 添加分隔y轴的填充物
		c = new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, anchor, fill, new Insets(0, 0, 0, 0), 0, 0);
		for (int i = 1; i <= col; i++) {
			c.gridx = 0;
			c.gridy = i;
			panel.add(Box.createVerticalGlue(), c);
		}
		for (int i = 0; i < components.size(); i++) {
			Component component = components.get(i);
			Integer rowIdx = rowIdxs.get(i);
			Integer colIdx = colIdxs.get(i);
			Integer gridwidth = gridwidths.get(i);
			Integer gridheight = gridheights.get(i);
			if (fill == GridBagConstraints.BOTH) {
				component.setPreferredSize(new Dimension(0, 0));
				component.setSize(new Dimension(0, 0));
				component.setMaximumSize(new Dimension(0, 0));
				component.setMinimumSize(new Dimension(0, 0));
			}
			c = new GridBagConstraints(rowIdx + 1, colIdx + 1, gridwidth, gridheight, 0.0, 0.0, anchor, fill,
					insetsList.get(i), 0, 0);
			panel.add(component, c);
		}

		panel.updateUI();
		components.clear();
		rowIdxs.clear();
		colIdxs.clear();
		gridheights.clear();
		gridwidths.clear();
	}

	public void setAnchor(int anchor) {
		this.anchor = anchor;
	}

	public int getAnchor() {
		return anchor;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}

	public int getFill() {
		return fill;
	}

	public void setRow(int row) {
		this.col = row;
	}

	public int getRow() {
		return col;
	}

	public void setCol(int col) {
		this.row = col;
	}

	public int getCol() {
		return row;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	public Insets getInsets() {
		return insets;
	}

	public List<Component> getComponents() {
		return components;
	}

	private Insets insets;
	private int row;
	private int col;
	private int anchor = GridBagConstraints.CENTER;
	private int fill = GridBagConstraints.BOTH;

	private List<Component> components = new Vector<Component>();
	private List<Integer> rowIdxs = new Vector<Integer>();
	private List<Integer> colIdxs = new Vector<Integer>();
	private List<Integer> gridwidths = new Vector<Integer>();
	private List<Integer> gridheights = new Vector<Integer>();

	private List<Insets> insetsList = new Vector<Insets>();
}