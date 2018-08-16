package com.smtech.swing.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


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
				images.put(imageName, new ImageIcon(fileName).getImage());
			} catch (Exception e) {
				return null;
			}
		}
		return images.get(imageName);
	}


	private static Map<String, String> imagesPhotoPath = new HashMap<String, String>();


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
	private final static String RES_PATH = "./images/";
	private final static String KEY = "32E41F3611D818C0";
}
