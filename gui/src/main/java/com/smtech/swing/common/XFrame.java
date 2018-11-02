package com.smtech.swing.common;

import com.smtech.swing.common.btns.XImageButton;
import com.smtech.swing.common.util.CommonFunc;
import com.smtech.swing.common.util.Dragger;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.view.TransparentView;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

/**
 * 仿360窗体
 */
public class XFrame extends JFrame {
	private TransparentView titlePanel;
	public XFrame() {
		imgPanel = new ViewGroup();
		imgPanel.setBackground(FRAME_COLOR);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(createTitlePanel(), BorderLayout.NORTH);
		imgPanel.add(createSelfPanel(), BorderLayout.CENTER);

		JPanel superContentPanel = (JPanel) super.getContentPane();
		superContentPanel.setLayout(new BorderLayout());
		superContentPanel.add(imgPanel, BorderLayout.CENTER);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Dragger(this, imgPanel);
	}

	@Override
	public void setTitle(String title) {
		labTitle.setText(title);
		labTitle.setForeground(Color.white);
		super.setTitle(title);
	}

	@Override
	public void setIconImage(Image image) {
		ImageIcon icon = new ImageIcon(image);
		Image temp = icon.getImage().getScaledInstance(16, 16,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(temp);
		super.setIconImage(image);
	}

	@Override
	public Container getContentPane() {
		return selfPanel;
	}

	public ViewGroup getTopPanel() {
		return imgPanel;
	}

	public void resetTopPanel() {

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

	public void setBackgroupImg(Image img) {
		imgPanel.setBackgroundImage(img);
	}

	public Image getBackgroupImg() {
		return imgPanel.getBackgroundImage();
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

		Dimension btnSize = new Dimension(24, 24);
		List<Object[]> btnInfo = new Vector<Object[]>();
		btnInfo.add(new Object[] { "min.png", "bgNormal.png", new Min() });
		btnInfo.add(new Object[] { "max.png", "bgNormal.png", new Max() });
		btnInfo.add(new Object[] { "close.png", "bgClose.png", new Close() });

		PanelBuilder pb = new PanelBuilder();
		pb.setInsets(new Insets(0, 0, 0, 0));
		pb.addHorizontalGlue();
		for (Object[] v : btnInfo) {
			String imgPath = "app/" + (String) v[0];
			String hoverImgPath ="app/" +  (String) v[1];
			AbstractAction ac = (AbstractAction) v[2];
			XImageButton btn = new XImageButton(ac);
			btn.setImgPath(imgPath);
			btn.setHoverImgPath(hoverImgPath);
//			fixSize(btn, btnSize);
			pb.add(btn);

			if (ac instanceof Max) {
				btnMax = btn;
			}
		}
		pb.add((JComponent) Box.createHorizontalStrut(5));
		titlePanel = new TransparentView();
		pb.doLayout(titlePanel);
		return titlePanel;
	}

	private JPanel createSelfPanel() {
		selfPanel = new TransparentView();
		selfPanel.setLayout(new BorderLayout());
		return selfPanel;
	}

	public void resetSize() {
		if (!isResizable()) {
			return;
		}
		Dimension srcSize = CommonFunc.getScreenMaxValidSize();
		if (srcSize.width == getWidth() && srcSize.height == getHeight()) {
			setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
			btnMax.setImgPath("max.png");
			btnMax.updateUI();
		} else {
			setPreferredSize(srcSize);
			btnMax.setImgPath("resize.png");
			btnMax.updateUI();
		}
		pack();
		setLocationRelativeTo(null);
	}

	public void hideTitle(){
		titlePanel.setVisible(false);
	}

	public void showTitle(){
		titlePanel.setVisible(true);
	}

	/**
	 * 建议按钮
	 */
	protected class SuggestAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	/**
	 * 皮肤按钮
	 */
	protected class Skin extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	/**
	 * 主菜单
	 */
	protected class Menu extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	/**
	 * 最小化
	 */
	protected class Min extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			setExtendedState(Frame.ICONIFIED);
		}
	}

	/**
	 * 处理最大化一个内部窗体。
	 */
	protected class Max extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isResizable()) {
				return;
			}
			resetSize();
		}
	}

	/**
	 * 处理关闭一个内部窗体。
	 */
	protected class Close extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	// 标题栏上的控件
	private JLabel labTitle;
	private JPanel selfPanel;
	private Rectangle preBounds;
	private ViewGroup imgPanel;
	private XImageButton btnMax;

	// 窗体背景
	public static final Color FRAME_COLOR = new Color(14, 98, 155);
	public static final String FRAME_BACKGROUP = "frame/frameBg.png";

	public static final String SUGGERST_ICON = "frame/suggest.png";
	public static final String SKIN_ICON = "frame/skin.png";
	public static final String MENU_ICON = "frame/menu.png";
	// 窗体状态控制按钮
	public static final String FRAME_MIN_ICON = "frame/min.png"; // frame/min/sysbtn_min.png
	public static final String FRAME_MAX_ICON = "frame/max.png"; // frame/max/sysbtn_max.png
	public static final String FRAME_RESET_ICON = "frame/reset.png"; // frame/reset/sysbtn_reset.png
	public static final String FRAME_CLOSE_ICON = "frame/close.png"; // frame/close/sysbtn_close.png

	public static final String FRAME_CLOSE_BG = "frame/frameConsoleBg.png";
	public static int MIN_WIDTH = 1000;
	public static int MIN_HEIGHT = 595;
	public static Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);
}

