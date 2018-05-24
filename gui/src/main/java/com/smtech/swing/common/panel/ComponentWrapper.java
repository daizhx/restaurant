package com.smtech.swing.common.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 控件包装器，用于为控件添加背景图片
 */
public class ComponentWrapper extends ViewGroup {
	public ComponentWrapper(JComponent componet, String imagePath) {
		this(componet, imagePath, ViewGroup.SCALED);
	}
	
	public ComponentWrapper(JComponent componet, String imagePath, String jImagePane) {
		componet.setOpaque(false);
		componet.setBackground(Color.black);
		componet.setFocusable(false);
		componet.setBorder(null);

		this.imagePath = imagePath;
		setOpaque(false);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		add(componet, BorderLayout.CENTER);
		setBackgroundImage(imagePath, jImagePane);
	}

	public Component getComponet() {
		Component[] componets = getComponents();
		if (componets.length == 0) {
			return null;
		}
		return componets[0];
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		if (getComponet() != null) {
			getComponet().setFont(font);
		}
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		if (getComponet() != null) {
			getComponet().setForeground(fg);
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (getComponet() != null) {
//			getComponet().setEnabled(enabled);
		}
	}

	private String imagePath;

}