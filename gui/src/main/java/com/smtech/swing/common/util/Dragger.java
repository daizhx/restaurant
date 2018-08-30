package com.smtech.swing.common.util;

import com.smtech.swing.common.XFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


/**
 * 拖拽类，实现组件的鼠标拖拽
 */
public class Dragger {

	/**
	 * 让指定的Component通过鼠标拖动来移动Window
	 */
	public Dragger(Window window, Component component) {
		this.frame = window;
		this.component = component;
		frame.addMouseListener(new Mouse());
		frame.addMouseMotionListener(new MouseMotion());
	}

	/**
	 * 获取窗体状态
	 *
	 * @return
	 */
	private int getExtendedState() {
		Point loc = frame.getLocation();
		if (loc.x != 0 || loc.y != 0) {
			return JFrame.NORMAL;
		}

		Dimension frameSize = frame.getSize();
		Dimension srcSize = CommonFunc.getScreenMaxValidSize();
		if (frameSize.width != srcSize.width
				|| frameSize.height != srcSize.height) {
			return JFrame.NORMAL;
		}
		return JFrame.MAXIMIZED_BOTH;
	}

	private class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			isDraging = true;
			clickPoint = new Point(e.getPoint());
			SwingUtilities.convertPointToScreen(clickPoint, component);
			// 记录鼠标按下点和窗体位置的距离
			dx = clickPoint.x - frame.getX();
			dy = clickPoint.y - frame.getY();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() <= 1) {
				return;
			}
			// 双击标题栏最大还原窗体
			if (frame instanceof XFrame) {
				((XFrame) frame).resetSize();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isDraging = false;
		}
	}

	private class MouseMotion extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			Point dragPoint = new Point(e.getPoint());
			SwingUtilities.convertPointToScreen(dragPoint, component); // 相对于屏幕的坐标
			if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
				return;
			}
			// 移动窗体
			if (frame.getCursor().getType() == Cursor.DEFAULT_CURSOR) {
				frame.setLocation(dragPoint.x - dx, dragPoint.y - dy);
				return;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// 鼠标进入窗体区域
			Point mousePosition = e.getPoint(); // 鼠标位置
			SwingUtilities.convertPointToScreen(mousePosition, component);
			if (isDraging) {
				return;
			}
		}

	}

	private Window frame; // 受控窗体
	private Component component; // 受控窗体中组件
	private int dx;
	private int dy;
	private Point clickPoint;
	private boolean isDraging = false; // 正在拖动
}

