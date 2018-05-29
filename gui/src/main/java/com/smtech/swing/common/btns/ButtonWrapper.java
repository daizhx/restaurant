package com.smtech.swing.common.btns;

import com.smtech.swing.common.ImageManager;
import com.smtech.swing.common.Res;
import com.smtech.swing.common.panel.ComponentWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonWrapper extends ComponentWrapper {

	public ButtonWrapper() {
		this(new JButton());
	}

	public ButtonWrapper(AbstractButton btn) {
		this(btn, null);
	}

	public ButtonWrapper(AbstractAction action) {
		this(new JButton(action));
	}

	public ButtonWrapper(AbstractAction action, String imagePath) {
		this(new JButton(action), imagePath);
	}

	public ButtonWrapper(String imagePath) {
		this(new JButton(), imagePath);
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		textColor = fg;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}

	public ButtonWrapper(AbstractButton btn, String imagePath) {
		super(btn, imagePath);
		btn.setContentAreaFilled(false);
		setAction(btn.getAction());
		// 默认字体颜色
		btn.setForeground(Res.TEXT_COLOR);
		textColor = Res.TEXT_COLOR;
		this.btn = (JButton) btn;
	}

	public void setBackgroundImage(String imagePath) {
		super.setBackgroundImage(imagePath);
		nomalImgPath = imagePath;
	}

	public void setBackgroundImage(String imagePath, String modeName) {
		super.setBackgroundImage(imagePath, modeName);
		nomalImgPath = imagePath;
	}

	protected void setPressedBackGroundImage() {
		String img = pressedImgPath == null ? getDefaultPressedImgPath()
				: pressedImgPath;
		super.setBackgroundImage(img, getImageDisplayMode());
	}

	protected void setPressIconImage() {
		// icon变化
		String pressIconPath = pressIcon != null ? pressIcon
				: getDefaultPressedIconPath();
		resetIcon(pressIconPath);
	}
	
	protected void setReleasedIconImage() {
		String icon = pressIcon != null ? pressIcon
				: getDefaultReversePressedIconPath();
		resetIcon(icon);
	}
	
	protected void setReleasedBackGroundImage() {
		String img = releasedImgPath == null ? getDefaultReleasedImgPath()
				: releasedImgPath;
		super.setBackgroundImage(img, getImageDisplayMode());
	}

	private void setSelectedBackGroundImage() {
		String img = selectedImgPath == null ? getDefaultSelectedImgPath()
				: selectedImgPath;
		super.setBackgroundImage(img, getImageDisplayMode());
	}

	private void setDisableBackGroundImage() {
		String img = disableImgPath == null ? getDefaultDisableImgPath()
				: disableImgPath;
		super.setBackgroundImage(img, getImageDisplayMode());
	}

	public void setIcon(String iconPath) {
		setIcon(iconPath, SwingConstants.TOP);
	}

	public void setIcon(String iconPath, int gravity) {
		icon = iconPath;
		String RES_PATH = "../resources/";
		iconPath = RES_PATH + iconPath;
		btn.setIcon(new ImageIcon(iconPath));
		if (gravity == SwingConstants.LEFT) {
			btn.setVerticalTextPosition(SwingConstants.CENTER);
			btn.setHorizontalTextPosition(SwingConstants.RIGHT);
		} else {
			btn.setVerticalTextPosition(SwingConstants.BOTTOM);
			btn.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		iconGravity = gravity;
	}

	// 指定大小
	public void setIcon(String iconPath, int w, int h) {
		setIcon(iconPath, w, h, SwingConstants.TOP);
	}

	public void setIcon(String iconPath, int w, int h, int gravity) {
		icon = iconPath;
		String RES_PATH = "../resources/";
		iconPath = RES_PATH + iconPath;
		ImageIcon img = new ImageIcon(iconPath);
		img.setImage(img.getImage()
				.getScaledInstance(w, h, Image.SCALE_DEFAULT));
		btn.setIcon(img);
		if (gravity == SwingConstants.LEFT) {
			btn.setVerticalTextPosition(SwingConstants.CENTER);
			btn.setHorizontalTextPosition(SwingConstants.RIGHT);
		} else {
			btn.setVerticalTextPosition(SwingConstants.BOTTOM);
			btn.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		iconW = w;
		iconH = h;
		iconGravity = gravity;
	}

	public void setIconTextGap(int gap) {
		btn.setIconTextGap(gap);
		iconTextGap = gap;
	}

	public void setIcon(ImageIcon icon) {
		btn.setIcon(icon);
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	public Action getAction() {
		if (action == null) {
			AbstractButton btn = ((AbstractButton) getComponet());
			action = btn.getAction();
		}
		return action;
	}

	public void setAction(Action action) {
		// 清理之前的action
		final AbstractButton btn = ((AbstractButton) getComponet());
		for (MouseListener ml : btn.getMouseListeners()) {
			btn.removeMouseListener(ml);
		}
		this.action = null;
		if (action == null) {
			return;
		}

		if (!action.isEnabled()) {
			setDisableBackGroundImage();
			return;
		}

		// 拿到action的名称
		btn.setAction(action);
		String text = btn.getText();

		this.action = btn.getAction();
		btn.setAction(null);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				status = ACTION_DOWN;
				if (!isEnabled()) {
					return;
				}
				// 无需交互效果
				if (!isInteration) {
					return;
				}
				if (nomalImgPath == null) {
					if (ButtonWrapper.this instanceof BtnByDraw) {
						setPressedBackGroundImage();
						if (pressedTextColor != null) {
							btn.setForeground(pressedTextColor);
						}
						setPressedBackGroundImage();
					}
					return;
				}

				if (pressedTextColor != null) {
					btn.setForeground(pressedTextColor);
				}
				setPressedBackGroundImage();

				// icon变化
				setPressIconImage();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				status = ACTION_UP;
				int btnIdx = e.getButton();
				if (!isEnabled()) {
					return;
				}

				setReleasedBackGroundImage();

				if (textColor != null) {
					setForeground(textColor);
				}

				if (!btn.isEnabled()) {
					return;
				}
				if (!ButtonWrapper.this.action.isEnabled()) {
					return;
				}
				if (doClickSelectedSelf) {
					refreshBtnStatus(
							(JComponent) ButtonWrapper.this.getParent(),
							ButtonWrapper.this);
				}
				if (!ButtonWrapper.this.isEnabled() || !btn.isEnabled()
						|| !ButtonWrapper.this.action.isEnabled()) {
					// 按钮不可用
					return;
				}

				// icon变化
				setReleasedIconImage();
				
				if (btnIdx != MouseEvent.BUTTON3) {
					ButtonWrapper.this.action.actionPerformed(new ActionEvent(e
							.getSource(), e.getID(), null));
				}

			}
		});
		if (text != null && !text.equals("")) {
			btn.setText(text);
		}
		if (icon != null) {
			if (iconW != 0 && iconH != 0) {
				setIcon(icon, iconW, iconH, iconGravity);
			} else {
				setIcon(icon, iconGravity);
			}
		}
	}

	private boolean doClickSelectedSelf;

	public void doClick() {
		ButtonWrapper.this.action.actionPerformed(new ActionEvent(
				getComponet(), getComponet().hashCode(), null));
	}

	public void setText(String text) {
		((AbstractButton) getComponet()).setText(text);
	}

	public void setTextFont(Font f) {
		((AbstractButton) getComponet()).setFont(f);
	}

	public void setTextColor(Color c) {
		textColor = c;
		AbstractButton btn = (AbstractButton) getComponet();
		if (btn == null) {
			return;
		}
		if (btn instanceof TwoLineTextBtn) {
			((TwoLineTextBtn) btn).setTextColor(c);
		} else {
			btn.setForeground(textColor);
		}
	}

	public String getText() {
		return ((AbstractButton) getComponet()).getText();
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
		((AbstractButton) getComponet()).setIcon(ImageManager
				.getImgIcon(iconPath));
	}

	private String iconPath;

	public String getIconPath() {
		return iconPath;
	}

	public void addActionListener(ActionListener l) {
		AbstractButton btn = (AbstractButton) getComponet();
		if (btn != null) {
			btn.addActionListener(l);
		}
	}

	protected String getDefaultReleasedImgPath() {
		if (nomalImgPath == null) {
			return null;
		}
		String defaultReleasedImgPath = nomalImgPath.replace(".", "_r.");
		if (ImageManager.getImage(defaultReleasedImgPath) == null) {
			return nomalImgPath;
		}
		return defaultReleasedImgPath;
	}

	protected String getDefaultPressedImgPath() {
		if (nomalImgPath == null) {
			return null;
		}
		String defaultPressedImgPath = nomalImgPath.replace(".", "_p.");
		if (ImageManager.getImage(defaultPressedImgPath) == null) {
			return nomalImgPath;
		}
		return defaultPressedImgPath;
	}

	// 默认不可点击时的背景图
	protected String getDefaultDisableImgPath() {
		if (nomalImgPath == null) {
			return null;
		}
		String imgPath = nomalImgPath.replace(".", "_d.");
		if (ImageManager.getImage(imgPath) == null) {
			return nomalImgPath;
		}
		return imgPath;
	}

	// 获取默认按下去时的icon
	protected String getDefaultPressedIconPath() {
		if (icon == null) {
			return null;
		}
		String s = icon.replace(".", "_p.");
		if (ImageManager.getImage(s) == null) {
			return null;
		}
		return s;
	}

	protected String getDefaultReversePressedIconPath() {
		if (icon == null) {
			return null;
		}
		String s = icon.replace("_p.", ".");
		if (ImageManager.getImage(s) == null) {
			return null;
		}
		return s;
	}

	protected String getDefaultSelectedImgPath() {
		if (nomalImgPath == null) {
			return null;
		}
		String defaultPressedImgPath = nomalImgPath.replace(".", "_s.");
		if (ImageManager.getImage(defaultPressedImgPath) == null) {
			defaultPressedImgPath = nomalImgPath.replace(".", "_p.");
			if (ImageManager.getImage(defaultPressedImgPath) == null) {
				return nomalImgPath;
			}
		}
		return defaultPressedImgPath;
	}

	public void setPressedImgPath(String pressedImgPath) {
		this.pressedImgPath = pressedImgPath;
	}

	public String getPressedImgPath() {
		return pressedImgPath;
	}

	public void setReleasedImgPath(String releasedImgPath) {
		this.releasedImgPath = releasedImgPath;
	}

	public String getReleasedImgPath() {
		return releasedImgPath;
	}

	public void setSelectedImgPath(String selectedImgPath) {
		this.selectedImgPath = selectedImgPath;
	}

	public String getSelectedImgPath() {
		return selectedImgPath;
	}

	public void isSelected(String btnInScope) {
		setBackgroundImage(getDefaultSelectedImgPath());
	}

	public void setSelected(boolean b) {
		if (b) {
			status = ACTION_SELECTED;
			setSelectedBackGroundImage();
			if (selectedTextColor != null) {
				btn.setForeground(selectedTextColor);
			}
		} else {
			status = ACTION_UP;
			setBackgroundImage(nomalImgPath);
			if (selectedTextColor != null) {
				btn.setForeground(textColor);
			}
		}
	}

	public void setInteration(boolean isInteration) {
		this.isInteration = isInteration;
	}

	public void setDoClickSelectedSelf(boolean doClickSelectedSelf) {
		this.doClickSelectedSelf = doClickSelectedSelf;
	}

	public boolean isDoClickSelectedSelf() {
		return doClickSelectedSelf;
	}

	/**
	 * 排他刷新当前按钮
	 * 
	 * @param pane
	 * @param currBtn
	 */
	protected void refreshBtnStatus(JComponent pane, ButtonWrapper currBtn) {
		for (Component comp : pane.getComponents()) {
			if (!(comp instanceof ButtonWrapper)) {
				continue;
			}
			if (comp == currBtn) {
				currBtn.setSelected(true);
			} else {
				((ButtonWrapper) comp).setSelected(false);
			}
		}
	}

	public Color getPressedTextColor() {
		return pressedTextColor;
	}

	public void setPressedTextColor(Color pressedTextColor) {
		this.pressedTextColor = pressedTextColor;
	}

	protected void resetIcon(String pressIconPath) {
		if (pressIconPath != null) {
			if (iconW != 0 && iconH != 0) {
				setIcon(pressIconPath, iconW, iconH, iconGravity);
			} else {
				setIcon(pressIconPath, iconGravity);
			}
			if (iconTextGap != 0) {
				setIconTextGap(iconTextGap);
			}
		}
	}

	protected boolean isInteration = true; // 是否需要交互
	protected String nomalImgPath;
	protected String pressedImgPath;
	protected String releasedImgPath;
	protected String selectedImgPath;
	protected String disableImgPath;

	protected Action action;

	protected int status;
	public static final int ACTION_SELECTED = 2;
	public static final int ACTION_DOWN = 1;
	public static final int ACTION_UP = 0;

	public JButton btn;

	protected Color textColor = null;
	private Color pressedTextColor = null;
	protected Color selectedTextColor = null;

	public Color getSelectedTextColor() {
		return selectedTextColor;
	}

	public void setSelectedTextColor(Color selectedTextColor) {
		this.selectedTextColor = selectedTextColor;
	}

	public static enum BgColor {
		BLUE, // 蓝色
		CYAN, // 青色
		FUCKSIN, // 羊红色
		GRAYSCALE1, // 灰度1
		GRAYSCALE2, // 灰度2
		GRAYSCALE3, // 灰度3
		GRAYSCALE4, // 灰度4
		GREEN, // 绿色
		ORANGE, // 橙色
		PINK, // 粉红色
		RED, // 红色
		GRAY, // 灰色
		YELLOW, // 黄色
		PALE_BULE, // 淡蓝色
	}

	// 按下时的ICON
	private String icon;
	private String pressIcon = null;
	private int iconW;
	private int iconH;
	private int iconGravity = SwingConstants.TOP;
	private int iconTextGap;

	private Object tag;

	public String getBgImgPath() {
		return nomalImgPath;
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}
}