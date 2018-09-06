package com.smtech.restaurant.setting;

import com.smtech.swing.common.btns.XImageButton;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.util.Dragger;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.util.UIUtil;
import com.smtech.swing.common.view.TransparentView;
import com.smtech.swing.common.view.ViewGroup;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * 仿360窗体
 */
public class XDialog extends DlgBase {

	@Override
	protected void onCrtContntView(JPanel content) {
		ViewGroup imgPanel = new ViewGroup();
		imgPanel.setBackground(DIALOG_COLOR);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(createTitlePanel(), BorderLayout.NORTH);
		imgPanel.add(createSelfPanel(), BorderLayout.CENTER);
		imgPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        new Dragger(this, imgPanel); // 添加拖拽

        content.setLayout(new BorderLayout());
        content.add(imgPanel, BorderLayout.CENTER);

	}

	public XDialog(Window parent) {
		super(parent);

//		setUndecorated(true);
		setRoundRectWin();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}

	@Override
	public void setTitle(String title) {
		labTitle.setText(title);
		labTitle.setForeground(Color.white);
		super.setTitle(title);
	}

	//设置图标，在标题的左边
	@Override
	public void setIconImage(Image image) {
		ImageIcon icon = new ImageIcon(image);
		Image temp = icon.getImage().getScaledInstance(16, 16,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(temp);
		labTitle.setIcon(icon);
		super.setIconImage(image);
	}

	@Override
	public Container getContentPane() {
		return selfPanel;
	}

	public void setPreBounds(Rectangle preBounds) {
		this.preBounds = preBounds;
	}

	public Rectangle getPreBounds() {
		return preBounds;
	}

	public int getExtendedState() {
		Point loc = getLocation();
		if (loc.x != 0 || loc.y != 0) {
			return JFrame.NORMAL;
		}

		Dimension frameSize = getSize();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		if (frameSize.width != screenSize.width
				|| frameSize.height != screenSize.height) {
			return JFrame.NORMAL;
		}
		return JFrame.MAXIMIZED_BOTH;
	}

	@Override
	public void setVisible(boolean arg0) {
		if (!arg0) {
			clsBtn.setHover(false);
			clsBtn.updateUI();
		}
		super.setVisible(arg0);
	}
	/**
	 * 创建标题栏
	 *
	 * @return
	 */
	private JPanel createTitlePanel() {
		labTitle = new JLabel();
		labTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
		labTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		clsBtn = new XImageButton(new Close());
		clsBtn.setImgPath("dialog/close.png");
		clsBtn.setHoverImgPath("dialog/bgClose.png");
		UIUtil.fixSize(clsBtn, new Dimension(24, 24));
		PanelBuilder pb = new PanelBuilder();
		pb.setInsets(new Insets(2, 0, 2, 0));
		pb.add(labTitle);
		pb.addHorizontalGlue();
		pb.add(clsBtn);
		pb.add((JComponent) Box.createHorizontalStrut(5));

		JPanel panel = new TransparentView();
		pb.doLayout(panel);
		return panel;
	}

	private JPanel createSelfPanel() {
		selfPanel = new TransparentView();
		selfPanel.setLayout(new BorderLayout());

		return selfPanel;
	}

	/**
	 * 设置窗体为圆角
	 */
	private void setRoundRectWin() {
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0, 0,
				this.getWidth(), this.getHeight(), 15, 15));
	}

	//设置关闭action
	public void setCloseAction(Action closeAction) {
		this.closeAction = closeAction;
		clsBtn.setAction(closeAction);
	}

	public Action getCloseAction() {
		return closeAction;
	}
	/**
	 * 处理关闭一个内部窗体。
	 */
	protected class Close extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}

	public static final Color FRAME_COLOR = new Color(14, 98, 155);
	public static final Color DIALOG_COLOR = new Color(19, 86, 137);

	// 标题栏上的控件
	private Action closeAction;
	private XImageButton clsBtn;
	private JLabel labTitle;
	private JPanel selfPanel;
	private Rectangle preBounds;
	public static final int MIN_WIDTH = 660;
	public static final int MIN_HEIGHT = 484;
	public static final Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);
}
