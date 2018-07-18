package com.smtech.swing.common.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundRectPanel extends JPanel {
	private int arcWidth = 8;
	private int arcHeight = 8;

	public RoundRectPanel() {
		super();
	}

	public RoundRectPanel(int arcWidth, int arcHeight) {
		super();
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}

	@Override
	public void paintComponent(Graphics arg0) {
		Graphics2D g2 = (Graphics2D) arg0;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground()); // �Ի��򱳾�ɫ
		g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), arcWidth,
				arcHeight); // ���Բ�Ǿ��Σ���ɫΪ״̬��ɫ��
	}
}
