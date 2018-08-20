package com.smtech.swing.common.util;

import com.smtech.swing.common.ImageManager;
import com.smtech.swing.common.view.RoundRectPanel;
import com.smtech.swing.common.view.TranslucenceView;
import com.smtech.swing.common.view.TransparentView;

import javax.swing.*;
import java.awt.*;

/**
 * 面板包装器，利用面板外边框构造盒子模型中内边距的效果
 */
public class PanelWraper {

	/**
	 * 获得具有指定空白边框的面板
	 * 
	 * @param content
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * @return
	 */
	public static TransparentView getEmptyBordPanel(Component content, int top, int left, int bottom, int right) {
		TransparentView p = new TransparentView(new BorderLayout());
		p.add(content, BorderLayout.CENTER);
		p.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		return p;
	}



	// 用背景图包装
	public static JPanel wraper2(Container contentPane, final String shadowBgPath) {
		// 圆角底图，四边带有阴影的底图
		JPanel backgroupPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics arg0) {
				Graphics2D g2 = (Graphics2D) arg0;
				Image img = ImageManager.getImage(shadowBgPath);
				g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		backgroupPanel.setLayout(new BorderLayout());
		backgroupPanel.add(getEmptyBordPanel(contentPane, 0, 50, 50, 50), // 阴影范围
				BorderLayout.CENTER);
		return backgroupPanel;
	}

	public static JPanel wraperWithImgBg(JPanel contentPane, final String bgImg) {
		JPanel backgroupPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics arg0) {
				Graphics2D g2 = (Graphics2D) arg0;
				Image img = ImageManager.getImage(bgImg);
				g2.drawImage(img, 0, 0, getWidth(), getHeight() - 2, this);
			}
		};
		backgroupPanel.setLayout(new BorderLayout());
		backgroupPanel.add(contentPane, // 阴影范围
				BorderLayout.CENTER);
		return backgroupPanel;
	}

	/**
	 * 包装成一个全屏面板，底图半透明
	 * 
	 * @param component
	 * @return
	 */
	public static JPanel wrapForFullScr(JComponent component) {
		JPanel pTmp = new JPanel() {
			@Override
			public void paintComponent(Graphics arg0) {
				Graphics2D g2 = (Graphics2D) arg0;
				g2.setColor(new Color(230, 230, 230)); // 对话框背景色
				g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 12, 12); // 填充圆角矩形，颜色为状态栏色调
			}
		};
		pTmp.setLayout(new BorderLayout());
		pTmp.add(component, BorderLayout.CENTER);

		PanelBuilder pb = new PanelBuilder();
		pb.addVerticalGlue();
		pb.addBlankLn();

		pb.addHorizontalGlue();
		pb.add(pTmp);
		pb.addHorizontalGlue();
		pb.addBlankLn();

		pb.addVerticalGlue();

		TranslucenceView p = new TranslucenceView(0.2);
		p.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		pb.doLayout(p);
		return p;
	}

	/**
	 * 用纯色背景包装面板
	 * 
	 * @param component
	 * @param bg
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * @return
	 */
	public static JPanel wrapWithColorBg(JComponent component, Color bg, int top, int left, int bottom, int right) {
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(bg);
		p.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		p.add(component, BorderLayout.CENTER);
		return p;
	}

	/**
	 * 用圆角纯色面板包装
	 * 
	 * @param component
	 * @param bg
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * @return
	 */
	public static JPanel wrapWithRoundBg(JComponent component, Color bg, int top, int left, int bottom, int right) {
		component.setOpaque(false);
		RoundRectPanel p = new RoundRectPanel();
		p.setLayout(new BorderLayout());
		p.setBackground(bg);
		p.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		p.add(component, BorderLayout.CENTER);
		return p;
	}

	public static JPanel wrapWithRoundBg(JComponent component, Color bg) {
		return wrapWithRoundBg(component, bg, 0, 0, 0, 0);
	}

}