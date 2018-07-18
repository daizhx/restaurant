package com.smtech.swing.common.view;

import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * 透明视图
 */
public class TransparentView extends JPanel {

	public TransparentView() {
		setOpaque(false);
	}

	public TransparentView(LayoutManager l) {
		super(l);
		setOpaque(false);
	}
	
	public void setPadding(int top,int left,int bottom,int right){
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
}
