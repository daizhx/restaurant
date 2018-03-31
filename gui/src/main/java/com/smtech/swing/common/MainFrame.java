package com.smtech.swing.common;

import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public static void setInstance(MainFrame instance) {
		synchronized (lockForInstance) {
			MainFrame.instance = instance;
		}
	}

	public static MainFrame getInstance() {
		synchronized (lockForInstance) {
			if (instance == null) {
				instance = new MainFrame();
			}
			return instance;
		}
	}

	private static MainFrame instance = null;
	private static Object lockForInstance = new Object();
	public static final Color MAIN_COLOR = new Color(15, 67, 105);
}
