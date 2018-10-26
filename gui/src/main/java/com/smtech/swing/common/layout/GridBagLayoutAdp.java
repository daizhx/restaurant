package com.smtech.swing.common.layout;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 网格包布局
 */
public class GridBagLayoutAdp {

	private static final GridBagLayoutAdp INSTANCE = new GridBagLayoutAdp();

	public GridBagLayoutAdp() {
	}

	public static GridBagLayoutAdp getInstance(){
		return INSTANCE;
	}

	public void addWithFixSize(Component child, int size) {
		fixSizeIdx.add(childs.size());
		add(child, size);
	}

	public void add(Component child, int span) {
		childs.add(child);
		spans.add(span);
	}

	public void addGlue(int span) {
		add(null, span);
	}

	public void addGlueWithFixSize(int span) {
		addWithFixSize(null, span);
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public JPanel doLayout() {
		JPanel p = new JPanel();
		p.setOpaque(false);
		doLayout(p);
		return p;
	}

	public void doLayout(JPanel panel) {
		if (orientation == SwingConstants.HORIZONTAL) {
			doLayoutH(panel);
		} else {
			doLayoutV(panel);
		}
	}

	/**
	 * 水平方向
	 * 
	 * @param panel
	 */
	private void doLayoutH(JPanel panel) {
		panel.removeAll();
		panel.setLayout(new GridBagLayout());

		long sumSpan = 0;
		for (int i = 0; i < childs.size(); i++) {
			if (fixSizeIdx.contains(i)) {
				// 固定大小的控件不参与计算
				continue;
			}
			sumSpan += spans.get(i);
		}

		Insets insets = new Insets(0, 0, 0, 0);
		for (int i = 0; i < childs.size(); i++) {
			Component child = childs.get(i);
			long span = spans.get(i);
			if (child == null) {
				child = Box.createGlue();
			}

			int gridx = i;
			int gridy = 0;
			int gridwidth = 1;
			int gridheight = 1;
			double weightx = 0.0;
			if (fixSizeIdx.contains(i)) {
				// 该控件需要固定大小
				setSize(child, new Dimension((int) span, 0));
			} else {
				setSize(child, new Dimension(0, 0));
				weightx = span * 1.0 / sumSpan;
			}
			double weighty = 1.0;
			GridBagConstraints c = new GridBagConstraints(gridx, gridy,
					gridwidth, gridheight, weightx, weighty,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
					0, 0);
			panel.add(child, c);
		}
		panel.updateUI();
		childs.clear();
		spans.clear();
	}

	/**
	 * 垂直方向
	 * 
	 * @param panel
	 */
	private void doLayoutV(JPanel panel) {
		panel.removeAll();
		panel.setLayout(new GridBagLayout());

		long sumSpan = 0;
		for (int i = 0; i < childs.size(); i++) {
			if (fixSizeIdx.contains(i)) {
				// 固定大小的控件不参与计算
				continue;
			}
			sumSpan += spans.get(i);
		}

		Insets insets = new Insets(0, 0, 0, 0);
		for (int i = 0; i < childs.size(); i++) {
			Component child = childs.get(i);
			long span = spans.get(i);
			if (child == null) {
				child = Box.createGlue();
			}

			int gridx = 0;
			int gridy = i;
			int gridwidth = 1;
			int gridheight = 1;
			double weightx = 1.0;
			double weighty = 0.0;
			if (fixSizeIdx.contains(i)) {
				setSize(child, new Dimension(0, (int) span));
			} else {
				setSize(child, new Dimension(0, 0));
				weighty = span * 1.0 / sumSpan;
			}

			GridBagConstraints c = new GridBagConstraints(gridx, gridy,
					gridwidth, gridheight, weightx, weighty,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
					0, 0);

			panel.add(child, c);
		}
		panel.updateUI();
		childs.clear();
		spans.clear();
	}

	public static void setSize(Component c, Dimension size) {
		c.setPreferredSize(size);
		c.setSize(size);
		c.setMaximumSize(size);
		c.setMinimumSize(size);
	}

	private Set<Integer> fixSizeIdx = new HashSet<Integer>();// 需要固定大小的控件位置
	private List<Component> childs = new ArrayList<Component>();
	private List<Integer> spans = new Vector<Integer>();
	private int orientation = SwingConstants.HORIZONTAL;
}