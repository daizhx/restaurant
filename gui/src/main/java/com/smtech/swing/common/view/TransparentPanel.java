package com.smtech.swing.common.view;

import javax.swing.*;
import java.awt.*;

/**
 * 透明面板
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

	public void setEmptyBorder(int top, int left, int bottom, int right) {
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
}