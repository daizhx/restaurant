package com.smtech.swing.common.panel;

import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 */
@SuppressWarnings("serial")
public class TransparentPanel extends JPanel {

	public TransparentPanel() {
		setOpaque(false);
	}

	public TransparentPanel(LayoutManager l) {
		super(l);
		setOpaque(false);
	}
	
	public void setEmptyBorder(int top,int left,int bottom,int right){
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
}
