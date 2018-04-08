package com.smtech.swing.common.util;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 */
public class PanelBuilder {
	public PanelBuilder() {
		setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						10, 10, 10, 10), 0, 0));
	}
	
	public PanelBuilder(int i) {
		setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						i, i, i, i), 0, 0));
	}

	/**
	 *
	 * @return
	 */
	public Set<JComponent> getComponents() {
		return componentsInfo.keySet();
	}

	/**
	 *
	 * @param insets
	 */
	public void setInsets(Insets insets) {
		commonConstraints.insets = insets;
	}

	/**
	 *
	 * @param component
	 * @param c
	 */
	public void add(JComponent component, GridBagConstraints c) {
		componentsInfo.put(component, c);
	}
	
	public void addWithSpan(JComponent component, double weightx,double weighty) {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, weightx, weighty, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, commonConstraints.insets, 0, 0);
		componentsInfo.put(component, c);
	}

	/**
	 *
	 * @param component
	 */
	public void add(JComponent component) {
		GridBagConstraints c = null;
		if (commonConstraints != null) {
			c = new GridBagConstraints();
			c.anchor = commonConstraints.anchor;
			c.fill = commonConstraints.fill;
			c.gridheight = commonConstraints.gridheight;
			c.gridwidth = commonConstraints.gridwidth;
			c.gridx = getCurCol();
			c.gridy = getCurRow();
			c.insets = commonConstraints.insets;
			c.ipadx = commonConstraints.ipadx;
			c.ipady = commonConstraints.ipady;
			c.weightx = commonConstraints.weightx;
			c.weighty = commonConstraints.weighty;

		} else {
			c = new GridBagConstraints(getCurCol(), getCurRow(), 1, 1, 1.0,
					1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0);
		}

		setCurCol(getCurCol() + 1);
		add(component, c);
	}
	
	public void add(JComponent component,int gridW,int gridH) {
		GridBagConstraints c = null;
		if (commonConstraints != null) {
			c = new GridBagConstraints();
			c.anchor = commonConstraints.anchor;
			c.fill = commonConstraints.fill;
			c.gridheight = gridH;
			c.gridwidth = gridW;
			c.gridx = getCurCol();
			c.gridy = getCurRow();
			c.insets = commonConstraints.insets;
			c.ipadx = commonConstraints.ipadx;
			c.ipady = commonConstraints.ipady;
			c.weightx = commonConstraints.weightx;
			c.weighty = commonConstraints.weighty;

		} else {
			c = new GridBagConstraints(getCurCol(), getCurRow(), gridW, gridH, 1.0,
					1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0);
		}

		setCurCol(getCurCol() + 1);
		add(component, c);
	}

	/**
	 *
	 * @param c
	 */
	public void setGridBagConstraints(GridBagConstraints c) {
		this.commonConstraints = c;
	}

	/**
	 *
	 * @param component
	 */
	public void addLn(JComponent component) {
		add(component);
		setCurRow(getCurRow() + 1);
		setCurCol(0);
	}

	/**
	 */
	public void addHorizontalGlue() {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, commonConstraints.insets, 0, 0);
		setCurCol(getCurCol() + 1);
		add((JComponent) Box.createHorizontalGlue(), c);
	}
	
	/**
	 */
	public void addHorizontalGlue(int v) {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, commonConstraints.insets, 0, 0);
		setCurCol(getCurCol() + 1);
		add((JComponent) Box.createHorizontalStrut(v), c);
	}
	
	public void addHorizontalGlue2(int v) {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, commonConstraints.insets, 0, 0);
		setCurCol(getCurCol() + 1);
		add((JComponent) Box.createHorizontalStrut(v), c);
	}


	/**
	 */
	public void addVerticalGlue() {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, 0.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, commonConstraints.insets, 0, 0);
		setCurCol(getCurCol() + 1);
		add((JComponent) Box.createVerticalGlue(), c);
	}
	
	/**
	 */
	public void addVerticalGlue(int height) {
		GridBagConstraints c = new GridBagConstraints(getCurCol(), getCurRow(),
				1, 1, 0.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, commonConstraints.insets, 0, 0);
		setCurCol(getCurCol() + 1);
		add((JComponent) Box.createVerticalStrut(height), c);
	}

	/**
	 */
	public void addBlank() {
		setCurCol(getCurCol() + 1);
	}

	/**
	 */
	public void addBlankLn() {
		setCurRow(getCurRow() + 1);
		setCurCol(0);
	}

	/**
	 *
	 * @param container
	 */
	public void doLayout(Container container) {
		container.removeAll();
		container.setLayout(new GridBagLayout());

		Iterator<Entry<JComponent, GridBagConstraints>> it = componentsInfo
				.entrySet().iterator();
		while (it.hasNext()) {
			Entry<JComponent, GridBagConstraints> entry = it.next();
			container.add(entry.getKey(), entry.getValue());
		}
		if (container instanceof JPanel) {
			((JPanel) container).updateUI();
		}
		reset();
	}

	/**
	 */
	public void reset() {
		setCurCol(0);
		setCurCol(0);
		componentsInfo.clear();
	}

	/**
	 *
	 * @param curRow
	 */
	public void setCurRow(int curRow) {
		this.curRow = curRow;
	}

	/**
	 *
	 * @return
	 */
	public int getCurRow() {
		return curRow;
	}

	/**
	 *
	 * @param curCol
	 */
	public void setCurCol(int curCol) {
		this.curCol = curCol;
	}

	/**
	 *
	 * @return
	 */
	public int getCurCol() {
		return curCol;
	}

	/**
	 */
	private GridBagConstraints commonConstraints;
	/**
	 */
	private int curRow = 0;
	/**
	 */
	private int curCol = 0;

	/**
	 */
	private Map<JComponent, GridBagConstraints> componentsInfo = new HashMap<JComponent, GridBagConstraints>();
}
