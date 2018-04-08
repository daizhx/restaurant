package com.smtech.swing.common;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 */
public class ImageManager {
	private static final Logger logger = LoggerFactory
			.getLogger(ImageManager.class);

	synchronized static public Image getImage(String imageName) {
		String fileName = getAbsolutePath(imageName);
		if (!(new File(fileName).isFile())) {
			return null;
		}
		if (!images.containsKey(fileName)) {
			try {
				// byte[] data = getDecData(fileName);
				Image img = getXMSJBg(imageName);
				if (img != null) {
					images.put(imageName, img);
				} else {
					images.put(imageName, new ImageIcon(fileName).getImage());
				}
			} catch (Exception e) {
				return null;
			}
		}
		return images.get(imageName);
	}


	private static Map<String, String> imagesPhotoPath = new HashMap<String, String>();


	/**
	 * ��ȡ�������ض�����
	 * 
	 * @return
	 */
	public static Image getXMSJBg(String imageName) {
		if (true) {
			return null;
		}
		if (imageName == null
				|| !(imageName.endsWith("default.gif")
						|| imageName.endsWith("default.png")
						|| imageName.endsWith("standy.gif") || imageName
						.endsWith("standy.png"))) {
			return null;
		}
		Image img = null;
		try {
			img = ImageIO.read(ImageManager.class
					.getResourceAsStream("standy.gif"));
		} catch (IOException e) {

		}
		return img;
	}

	synchronized public static ImageIcon getImgIcon(String fileName) {
		fileName = getAbsolutePath(fileName);
		if (!(new File(fileName).isFile())) {
			return null;
		}
		try {
			return new ImageIcon(fileName);
		} catch (Exception e) {
			return null;
		}
	}

	synchronized public static ImageIcon getImgIcon(String fileName, int width,
			int height) {
		fileName = getAbsolutePath(fileName);
		if (!(new File(fileName).isFile())) {
			return null;
		}
		try {
			ImageIcon imgIcon = new ImageIcon(fileName);
			imgIcon.setImage(imgIcon.getImage().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			return imgIcon;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getAbsolutePath(String fileAllName) {
		String pngPath = String.format("%s%s", RES_PATH, fileAllName);
		String gifPath = pngPath.toLowerCase().replace(".png", ".gif");
		if (new File(gifPath).exists()) {
			return gifPath;
		}
		return pngPath;
	}

	private static Map<String, Image> images = new HashMap<String, Image>();
	private final static String RES_PATH = "../resources/"; // ��Դ�ļ���Ŀ¼
	private final static String KEY = "32E41F3611D818C0";
}
