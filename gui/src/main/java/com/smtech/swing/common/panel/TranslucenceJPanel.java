package com.smtech.swing.common.panel;

/*
 */
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class TranslucenceJPanel extends JPanel {

	private Double transparency;

	public TranslucenceJPanel(double transparency) {
		this.transparency = transparency;
	}

	/**
	 *
	 * @param transparency
	 *
	 * @return void
	 */
	public void setTransparent(double transparency) {
		this.transparency = transparency;
		validate();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.setComposite(AlphaComposite.SrcOver.derive(transparency
				.floatValue()));
		graphics2d.fillRect(0, 0, getWidth(), getHeight());
		graphics2d.dispose();
	}

}
