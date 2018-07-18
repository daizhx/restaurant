package com.smtech.swing.common.view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * 通透视图，可以设置通透值
 */
public class TranslucenceView extends JPanel {

	private Double transparency;

	public TranslucenceView(double transparency) {
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
