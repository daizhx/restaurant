package com.smtech.swing.common.view;

import com.smtech.restaurant.util.StringUtil;
import com.smtech.swing.common.ImageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 可设置背景图片的JPanel，提供了三种显示背景图片的方式：居中、平铺和拉伸。 未设置背景图片的情况下，同JPanel。
 *
 * 该面板只能设置图片背景，设置背景颜色无效。。。
 * @author 003
 */
public class JImagePane extends JPanel {
	private boolean isDotNinePNG = false;

	public JImagePane(String imagePath) {
		setBackgroundImage(imagePath, TILED);
		setOpaque(false);
	}

	/**
	 * 构造一个具有指定背景图片和指定显示模式的JImagePane
	 * 
	 * @param image
	 *            背景图片
	 * @param modeName
	 *            背景图片显示模式
	 */
	public JImagePane(Image image, String modeName) {
		super();
		setBackgroundImage(image);
		setImageDisplayMode(modeName);
		setOpaque(false);
	}

	public JImagePane(String imagePath, String modeName) {
		setBackgroundImage(imagePath, modeName);
		setOpaque(false);
	}

	public JImagePane() {
		setOpaque(false);
	}

	public void setBackgroundImage(String imagePath, String modeName) {
		Image iamge = ImageManager.getImage(imagePath);
		this.backgroundImage = iamge;
		this.bgPath = imagePath;
		setImageDisplayMode(modeName);
	}
	
	public void setBackgroundImage(Image iamge, String modeName) {
		this.backgroundImage = iamge;
		setImageDisplayMode(modeName);
	}

	public void setBackgroundImage(String imagePath) {
		if (imagePath != null) {
			this.bgPath = imagePath;
			this.setBackgroundImage(imagePath, getImageDisplayMode());
		}
	}

	public void setBackgroundImage(Image image) {
		this.backgroundImage = image;
		setImageDisplayMode(getImageDisplayMode());
	}

	/**
	 * 获取背景图片
	 * 
	 * @return 背景图片
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public String getBgPath() {
		return bgPath;
	}

	/**
	 * 设置背景图片显示模式
	 * 
	 * @param modeName
	 *            模式名称，取值仅限于ImagePane.TILED ImagePane.SCALED ImagePane.CENTRE
	 */
	public void setImageDisplayMode(String modeName) {
		if (StringUtil.isNullStr(modeName)) {
			modeName = CENTER_INSIDE;
		}
		this.imageDisplayMode = modeName.trim();
		this.validate();
		this.repaint();
	}

	/**
	 * 获取背景图片显示模式
	 * 
	 * @return 显示模式
	 */
	public String getImageDisplayMode() {
		return imageDisplayMode;
	}

	public void setIsDotNinePNG(boolean b) {
		isDotNinePNG = b;
	}

	/**
	 * 绘制组件
	 * 
	 * @see javax.swing.JComponent#paintComponent(Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage == null) {
			// 没有设置图片
			return;
		}

		if (isDotNinePNG) {
			String path = ImageManager.getAbsolutePath(bgPath);
			// InputStream stream =
			// this.getClass().getResourceAsStream("content_bg2.9.png");
//			try {
//				InputStream stream = new FileInputStream(path);
//				NinePatch mPatch = NinePatch.load(stream, true /* is9Patch */, false /* convert */);
//				Graphics2D g2 = (Graphics2D) g;
//				Rectangle clip = g2.getClipBounds();
//				mPatch.draw(g2, clip.x, clip.y, clip.width, clip.height);
//			} catch (IOException e) {
//				logger.error(StackTraceToString.getExceptionTrace(e));
//			}
			return;
		}

		if (CENTRE.equalsIgnoreCase(imageDisplayMode)) {
			// 居中
			paintComponentCentre(g);
		} else if (TILED.equalsIgnoreCase(imageDisplayMode)) {
			// 平铺
			paintComponentTiled(g);
		} else if (SCALED.equalsIgnoreCase(imageDisplayMode)) {
			// 拉伸
			paintComponentScaled(g);
		} else if (CENTRE_CHOP.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentCENTRE_CHOP(g);
		} else if (CENTER_INSIDE.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentCENTER_INSIDE(g);
		}
	}

	/**
	 * 居中
	 */
	private void paintComponentCentre(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		g.drawImage(backgroundImage, x, y, this);
	}

	/**
	 * 平铺
	 * 
	 */
	private void paintComponentTiled(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		for (int ix = 0; ix < width; ix += imageWidth) {
			for (int iy = 0; iy < height; iy += imageHeight) {
				g.drawImage(backgroundImage, ix, iy, this);
			}
		}
	}

	/**
	 * 拉伸
	 */
	private void paintComponentScaled(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		g.drawImage(backgroundImage, 0, 0, width, height, this);
	}

	/**
	 * 缩放后再拉伸
	 * 
	 */
	private void paintComponentCENTRE_CHOP(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		// 伸缩后居中
		int maxW = 0;
		int maxH = 0;
		double d = 0;// 缩放系数
		// 以宽为缩放标准
		maxW = Math.max(width, imageWidth);
		d = Double.valueOf(maxW) / Double.valueOf(imageWidth);
		maxH = (int) (imageHeight * d);
		if (maxH < height) {
			// 以高为缩放标准
			maxH = Math.max(height, imageHeight);
			d = Double.valueOf(maxH) / Double.valueOf(imageHeight);
			maxW = (int) (imageWidth * d);
		}
		int x1 = (int) ((maxW - width) / 2 / d);
		int y1 = (int) ((maxH - height) / 2 / d);

		int x2 = (int) ((maxW - (maxW - width) / 2) / d);
		int y2 = (int) ((maxH - (maxH - height) / 2) / d);
		g.drawImage(backgroundImage, 0, 0, width, height, x1, y1, x2, y2, this);
	}

	/**
	 * 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽
	 * 
	 */
	private void paintComponentCENTER_INSIDE(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		// 以宽为缩放标准
		int maxW = Math.min(width, imageWidth);// 缩放后的宽度
		double d = Double.valueOf(maxW) / Double.valueOf(imageWidth);// 缩放系数
		int maxH = (int) (imageHeight * d);// 缩放后的高度
		if (maxH >= height) {
			// 以高为缩放标准
			maxH = Math.min(height, imageHeight);
			d = Double.valueOf(maxH) / Double.valueOf(imageHeight);
			maxW = (int) (imageWidth * d);
		}
		int x1 = (int) ((maxW - width) / 2 / d);
		int y1 = (int) ((maxH - height) / 2 / d);

		int x2 = (int) ((maxW - (maxW - width) / 2) / d);
		int y2 = (int) ((maxH - (maxH - height) / 2) / d);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(backgroundImage, 0, 0, width, height, x1, y1, x2, y2, this);
	}

	/**
	 * 居中
	 */
	public static final String CENTRE = "Centre";

	/**
	 * 平铺
	 */
	public static final String TILED = "Tiled";

	/**
	 * 拉伸
	 */
	public static final String SCALED = "Scaled";

	/**
	 * 按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
	 */
	public static final String CENTRE_CHOP = "CENTRE_CHOP";

	/**
	 * 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽
	 */
	public static final String CENTER_INSIDE = "CENTER_INSIDE";

	/**
	 * 背景图片
	 */
	private Image backgroundImage;

	/**
	 * 背景图片显示模式
	 */
	private String imageDisplayMode;

	private String bgPath;

	private static Logger logger = LoggerFactory.getLogger(JImagePane.class);

}