package com.smtech.swing.common.panel;

import com.smtech.restaurant.util.StringUtil;
import com.smtech.swing.common.ImageManager;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 003
 */
public class ViewGroup extends JPanel {

    public static final int HORIZONTAL = SwingConstants.HORIZONTAL;
    public static final int VERTICAL = SwingConstants.VERTICAL;

	private boolean isDotNinePNG = false;

    public ViewGroup() {
        super();
        setOpaque(false);
    }

	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		setOpaque(true);
	}

	public void setBackgroundImage(String imagePath, String modeName) {
		Image iamge = ImageManager.getImage(imagePath);
		this.backgroundImage = iamge;
		this.bgPath = imagePath;
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
	 *
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public String getBgPath() {
		return bgPath;
	}

	/**
	 *
	 * @param modeName
	 */
	public void setImageDisplayMode(String modeName) {
		if (StringUtil.isNull(modeName)) {
			modeName = CENTER_INSIDE;
		}
		this.imageDisplayMode = modeName.trim();
		this.validate();
		this.repaint();
	}

	/**
	 *
	 */
	public String getImageDisplayMode() {
		return imageDisplayMode;
	}

	public void setIsDotNinePNG(boolean b){
		isDotNinePNG = b;
	}
	/**
	 *
	 * @see javax.swing.JComponent#paintComponent(Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {

		if (backgroundImage == null) {
			super.paintComponent(g);
			return;
		}
		
		if(isDotNinePNG ){
			String path = ImageManager.getAbsolutePath(bgPath);
//			InputStream stream = this.getClass().getResourceAsStream("content_bg2.9.png");
//			try {
//				InputStream stream = new FileInputStream(path);
//				NinePatch mPatch = NinePatch.load(stream, true /* is9Patch*/, false /* convert */);
//				Graphics2D g2 = (Graphics2D) g;
//				Rectangle clip = g2.getClipBounds();
//				mPatch.draw(g2, clip.x, clip.y, clip.width, clip.height);
//			} catch (IOException e) {
//				logger.error(StackTraceToString.getExceptionTrace(e));
//			}
			return;
		}

		if (CENTRE.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentCentre(g);
		} else if (TILED.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentTiled(g);
		} else if (SCALED.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentScaled(g);
		} else if (CENTRE_CHOP.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentCENTRE_CHOP(g);
		} else if (CENTER_INSIDE.equalsIgnoreCase(imageDisplayMode)) {
			paintComponentCENTER_INSIDE(g);
		}
		super.paintComponent(g);
	}

	/**
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
	 *
	 */
	private void paintComponentScaled(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		g.drawImage(backgroundImage, 0, 0, width, height, this);
	}

	/**

	 * 
	 */
	private void paintComponentCENTRE_CHOP(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		int maxW = 0;
		int maxH = 0;
		double d = 0;
		maxW = Math.max(width, imageWidth);
		d = Double.valueOf(maxW) / Double.valueOf(imageWidth);
		maxH = (int) (imageHeight * d);
		if (maxH < height) {
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
	 *
	 */
	private void paintComponentCENTER_INSIDE(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		int imageWidth = backgroundImage.getWidth(this);
		int imageHeight = backgroundImage.getHeight(this);
		int maxW = Math.min(width, imageWidth);
		double d = Double.valueOf(maxW) / Double.valueOf(imageWidth);
		int maxH = (int) (imageHeight * d);
		if (maxH >= height) {
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

	public void setPadding(int left,int top ,int right,int bottom){
		setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
	}

	/**
	 */
	public static final String CENTRE = "Centre";

	/**
	 */
	public static final String TILED = "Tiled";

	/**
	 */
	public static final String SCALED = "Scaled";

	/**
	 */
	public static final String CENTRE_CHOP = "CENTRE_CHOP";

	/**
	 */
	public static final String CENTER_INSIDE = "CENTER_INSIDE";

	/**
	 */
	private Image backgroundImage;

	/**
	 */
	private String imageDisplayMode;

	private String bgPath;


}
