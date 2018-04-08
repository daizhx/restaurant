package com.smtech.swing.common.dlgs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.smtech.swing.common.Res;
import com.smtech.swing.common.btns.ButtonWrapper;
import com.smtech.swing.common.panel.RoundRectPanel;
import com.smtech.swing.common.panel.TranslucenceJPanel;
import com.smtech.swing.common.panel.TransparentPanel;
import com.smtech.swing.common.util.PanelBuilder;
import com.sun.awt.AWTUtilities;

/**
 *
 */
public abstract class SmallDlgBase extends DlgBase {
	
	ButtonWrapper btnClose;
	
	public SmallDlgBase() {
		super();
	}

	public SmallDlgBase(Window owner) {
		super(owner);
	}

	public void reflash() {
		
	}
	
	public void initComponents() {
		TransparentPanel pH = new TransparentPanel(new BorderLayout());
		pH.add(Box.createHorizontalStrut(getLeftInset()), BorderLayout.WEST);
		pH.add(crtCenterPanel(), BorderLayout.CENTER);
		pH.add(Box.createHorizontalStrut(getRightInset()), BorderLayout.EAST);

		JPanel northPanel = crtNorthPanel();
		northPanel.setPreferredSize(new Dimension(0, getTitleHeight()));
		JPanel topPanel = new TransparentPanel(new BorderLayout());
		topPanel.add(northPanel, BorderLayout.CENTER);
		topPanel.add(Box.createVerticalStrut(getTopInset()), BorderLayout.SOUTH);

		TransparentPanel pV = new TransparentPanel(new BorderLayout());
		pV.add(topPanel, BorderLayout.NORTH);
		pV.add(pH, BorderLayout.CENTER);
		pV.add(Box.createVerticalStrut(getBottomInset()), BorderLayout.SOUTH);

		JPanel pTmp = createContentPanel();
		pTmp.setLayout(new BorderLayout());
		pTmp.add(pV, BorderLayout.CENTER);
		pTmp.setPreferredSize(getDlgSize());

		PanelBuilder pb = new PanelBuilder(0);
		pb.addVerticalGlue();
		pb.addBlankLn();

		pb.addHorizontalGlue();
		pb.add(pTmp);
		pb.addHorizontalGlue();
		pb.addBlankLn();

		pb.addVerticalGlue();

		TranslucenceJPanel p = new TranslucenceJPanel(0.4);
		pb.doLayout(p);
		setContentPane(p);
		setUndecorated(true);
		setModal(true);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setResizable(false);
		pack();
		setLocation(0, 0);
//		AWTUtilities.setWindowOpaque(innerDlg, false);
		doSomethingAfterInit();
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	protected JPanel createContentPanel() {
		JPanel pTmp = new RoundRectPanel(12, 12);
		pTmp.setBackground(new Color(230, 230, 230));
		return pTmp;
	}

	/**
	 *
	 * @return
	 */
	protected int getLeftInset() {
		return 20;
	}

	/**
	 *
	 * @return
	 */
	protected int getRightInset() {
		return 20;
	}

	/**
	 *
	 * @return
	 */
	protected int getTopInset() {
		return 0;
	}

	/**
	 *
	 * @return
	 */
	protected int getBottomInset() {
		return 20;
	}

	/**
	 */
	protected void doSomethingAfterInit() {

	}

	/**
	 *
	 * @return
	 */
	protected JPanel crtNorthPanel() {
		labTitle = new JLabel(getTitleStr());
		labTitle.setFont(new Font(Res.FONT, Font.PLAIN, Res.FONT_SIZE_TITLE));
		labTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		TransparentPanel pTop = new TransparentPanel(new BorderLayout());
		pTop.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		pTop.add(labTitle, BorderLayout.CENTER);
		if(!isHideBtnClose()) {
//			btnClose = new ButtonWrapper(new AcOnCancel());
//			btnClose.setIcon(getSaasImgPath("KaiTaiDlg/btnClose.png"));
//			btnClose.setPreferredSize(new Dimension(60, 0));
//			pTop.add(btnClose, BorderLayout.EAST);
		}
		
		TransparentPanel pTmp = new TransparentPanel(new BorderLayout());
		pTmp.add(pTop, BorderLayout.CENTER);
		
		if(!isHideDivider()){
			JPanel labLine = new JPanel() {
				@Override
				public void paintComponent(Graphics arg0) {
					Graphics2D g2 = (Graphics2D) arg0;
					g2.setColor(new Color(174, 0, 33));
					g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 3, 3);
				}
			};
			labLine.setPreferredSize(new Dimension(0, 5));
	
			TransparentPanel pBottom = new TransparentPanel(new BorderLayout());
			pBottom.add(Box.createHorizontalStrut(getLeftInset()), BorderLayout.WEST);
			pBottom.add(labLine, BorderLayout.CENTER);
			pBottom.add(Box.createHorizontalStrut(getRightInset()), BorderLayout.EAST);
			pTmp.add(pBottom, BorderLayout.SOUTH);
		}
		
		pTmp.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
		return pTmp;
	}
	
	/**
	 * @return
	 */
	protected boolean isHideDivider(){
		return false;
	}
	
	/**
	 * @return
	 */
	protected boolean isHideBtnClose() {
		return false;
	}

	/**
	 *
	 * @return
	 */
	protected abstract JComponent crtCenterPanel();

	/**
	 *
	 * @return
	 */
	protected abstract String getTitleStr();

	/**
	 *
	 * @return
	 */
	protected Dimension getDlgSize() {
		Dimension size = getContentPanelSize();
		return calcuDlgSize(size.width, size.height);
	}

	/**
	 *
	 * @return
	 */
	protected Dimension getContentPanelSize() {
		return new Dimension(0, 0);
	};

	/**
	 *
	 * @param centerW
	 * @param centerH
	 * @return
	 */
	protected Dimension calcuDlgSize(int centerW, int centerH) {
		int w = centerW + getLeftInset() + getRightInset();
		int h = centerH + getTopInset() + getBottomInset() + getTitleHeight();
		return new Dimension(w, h);
	}

	/**
	 */
	protected void onCancel() {
		setVisible(false);
		dispose();
	}

	/**
	 *
	 * @return
	 */
	protected int getTitleHeight() {
		return 70;
	}

	protected class AcOnCancel extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			onCancel();
		}
	}
	
	public void setOnCancel(AbstractAction action){
//		btnClose.setAction(action);
	}

	public void setTitle(String s) {
		labTitle.setText(s);
	}

	public void setTitleFont(Font font) {
		labTitle.setFont(font);
	}


	protected JLabel labTitle;

	public static final Color FONT_COLOR = Res.TEXT_COLOR;
}
