package com.smtech.restaurant.app.widget;

import com.smtech.swing.common.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XImageButton extends JButton {
	public XImageButton(Action ac) {
		super(ac);
		addMouseListener(new Mouse());
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
	}

	@Override
	public void paint(Graphics g) {
		Image img = ImageManager.getImage(String.format("frame/%s", imgPath));
		Image hoverImg = ImageManager.getImage(String.format("frame/%s",
				hoverImgPath));
		if (isHover && hoverImg != null) {
			g.drawImage(hoverImg, 0, 0, getWidth(), getHeight(), this);
		}
		if (img != null) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		super.paint(g);
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setHoverImgPath(String hoverImgPath) {
		this.hoverImgPath = hoverImgPath;
	}

	public String getHoverImgPath() {
		return hoverImgPath;
	}

	public void setHover(boolean isHover) {
		this.isHover = isHover;
	}
	public boolean isHover() {
		return isHover;
	}

	private class Mouse extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			setHover(true);
			updateUI();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setHover(false);
			updateUI();
		}
		
	};

	private String imgPath;
	private String hoverImgPath;
	private boolean isHover;
}
