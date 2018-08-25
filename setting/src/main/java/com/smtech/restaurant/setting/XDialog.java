package com.smtech.restaurant.setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.smtech.swing.common.view.ViewGroup;
import com.sun.awt.AWTUtilities;

/**
 * ��360����
 */
public class XDialog extends JDialog {
	public XDialog() {
		this(null);
	}

	public XDialog(Window parent) {
		super(parent);
		ViewGroup imgPanel = new ViewGroup();
		imgPanel.setBackground(DIALOG_COLOR);
		imgPanel.setLayout(new BorderLayout());
		imgPanel.add(createTitlePanel(), BorderLayout.NORTH);
		imgPanel.add(createSelfPanel(), BorderLayout.CENTER);
		imgPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		JPanel superContentPanel = (JPanel) super.getContentPane();
		superContentPanel.setLayout(new BorderLayout());
		superContentPanel.add(imgPanel, BorderLayout.CENTER);
		setUndecorated(true);
		setRoundRectWin();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		new Dragger(this, imgPanel); // �����ק
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
	 * ����������
	 * 
	 * @return
	 */
	private JPanel createTitlePanel() {
		labTitle = new JLabel();
		labTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
		labTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		clsBtn = new XImageButton(new Close());
		clsBtn.setImgPath("close.png");
		clsBtn.setHoverImgPath("bgClose.png");
		PopDlgBase.fixSize(clsBtn, new Dimension(24, 24));
		PanelBuilder pb = new PanelBuilder();
		pb.setInsets(new Insets(2, 0, 2, 0));
		pb.add(labTitle);
		pb.addHorizontalGlue();
		pb.add(clsBtn);
		pb.add((JComponent) Box.createHorizontalStrut(5));

		JPanel panel = new TransparentPanel();
		pb.doLayout(panel);
		return panel;
	}

	private JPanel createSelfPanel() {
		selfPanel = new TransparentPanel();
		selfPanel.setLayout(new BorderLayout());
		return selfPanel;
	}

	/**
	 * ���ô���ΪԲ��
	 */
	private void setRoundRectWin() {
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0, 0,
				this.getWidth(), this.getHeight(), 15, 15));
	}
	public void setCloseAction(Action closeAction) {
		this.closeAction = closeAction;
		clsBtn.setAction(closeAction);
	}

	public Action getCloseAction() {
		return closeAction;
	}
	/**
	 * ����ر�һ���ڲ����塣
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

	// �������ϵĿؼ�
	private Action closeAction;
	private XImageButton clsBtn;
	private JLabel labTitle;
	private JPanel selfPanel;
	private Rectangle preBounds;
	public static final int MIN_WIDTH = 660;
	public static final int MIN_HEIGHT = 484;
	public static final Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);
}
