package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.ImageManager;
import com.smtech.swing.common.XFrame;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.util.UIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * 功能项基类，所有的功能面板都将继承该类
 *
 */
public abstract class FunctionItemBase {
	protected Dimension getOwnerDialogSize() {
		return new Dimension(XFrame.MIN_WIDTH, XFrame.MIN_HEIGHT);
	}


	public String getIconName() {
		if (iconName == null) {
			iconName = String.format("%s%s", this.getClass().getSimpleName(),
					".png");
		}
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window owner;

	/**
	 * 获取关闭窗体的Action
	 *
	 * @param owner
	 * @return
	 */
	@SuppressWarnings("serial")
	public Action getCloseAction(final Window owner) {
		if (owner == null) {
			return null;
		}
		this.owner = owner;
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setVisible(false);
				owner.dispose();

			}
		};
	}

	public void close() {
		owner.setVisible(false);
	}

	public abstract void init();

	public abstract void reflash();

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JPanel getContentPanel() {

		if (!hasInit) {
			try {
				init();
				progressBar = new JProgressBar(0, 100);
				progressBar.setValue(0);

				panelForProcessbar = new JPanel(new BorderLayout());
				UIUtil.fixSize(panelForProcessbar, new Dimension(0, 20));
				hasInit = true;
			} catch (Exception e) {
				logger.error(StackTraceToString.getExceptionTrace(e));
			}
		}
		reflash();

		if (!getNeedProgressBar()) {// 不需要进度条
			return contentPanel;
		}
		contentPanelInclueProcessbar.removeAll();
		contentPanelInclueProcessbar.setLayout(new BorderLayout());
		contentPanelInclueProcessbar.add(contentPanel, BorderLayout.CENTER);
		contentPanelInclueProcessbar
				.add(panelForProcessbar, BorderLayout.SOUTH);
		return contentPanelInclueProcessbar;
	}


	public JPanel getErrorPanel(String errorTips) {
		logger.trace("enter:" + errorTips);
		if (errorPanel == null) {
			errorPanel = new JPanel(new BorderLayout());
			JLabel l = new JLabel(createImageIcon("64-64/mb5u6_mb5ucom.png"));
			l.setText(errorTips);
			l.setHorizontalTextPosition(SwingConstants.CENTER);
			l.setVerticalTextPosition(SwingConstants.BOTTOM);
			errorPanel.add(l, BorderLayout.CENTER);

		}
		return errorPanel;
	}




	protected JButton createBtn(AbstractAction action, int key) {
		JButton btn = new JButton(action);
		addHotKeyForBtn(btn, key);
		return btn;
	}

	/**
	 * 判断对象实例关联的其他实例中，是否存在不能删除的情况
	 *
	 * @return
	 */
	public Boolean checkRelateObj(Object objInstance) {
		//TODO
		return false;
	}

	public Boolean deleteBean(Object bean) {
	    return false;
	}






	protected JPanel createSubPanel(List<JButton> btns) {
		logger.trace("enter:" + btns);
		PanelBuilder pb = new PanelBuilder();
		pb.setInsets(new Insets(50, 50, 50, 50));

		for (int i = 0; i < btns.size(); i++) {
			if (i > 0 && (i + 1) % 3 == 0) {
				pb.addLn(btns.get(i));
			} else {
				pb.add(btns.get(i));
			}

		}

		JPanel panelTemp = new JPanel();
		pb.doLayout(panelTemp);

		pb.setInsets(new Insets(0, 0, 0, 0));
		pb.addVerticalGlue();
		pb.addBlankLn();
		pb.add(panelTemp);
		pb.addHorizontalGlue();
		pb.addBlankLn();
		pb.addVerticalGlue();
		JPanel panel = new JPanel();
		pb.doLayout(panel);
		return panel;
	}

	public static ImageIcon createImageIcon(String filename) {
		String path = "Icon/" + filename;
		return ImageManager.getImgIcon(path);
	}

	public static Boolean isNull(String str) {
		return str == null || str.equals("");
	}

	/**
	 * 固定某控件的大小
	 *
	 * @param component
	 * @param size
	 */
	public static void fixSize(JComponent component, Dimension size) {
		component.setPreferredSize(size);
		component.setMaximumSize(size);
		component.setMinimumSize(size);
		component.setSize(size);
	}

	/**
	 * 为按钮添加热键
	 *
	 * @param btn
	 * @param key
	 */
	protected void addHotKeyForBtn(AbstractButton btn, int key) {
		InputMap imap = btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = btn.getActionMap();
		imap.put(KeyStroke.getKeyStroke(key, 0), btn.getText());
		amap.put(btn.getText(), btn.getAction());
	}






	/**
	 * 显示进度条的线程
	 */
	class ShowingProgressTask extends SwingWorker<Void, Integer> {
		@Override
		public Void doInBackground() {
			for (int i = 0; i <= 100; i++) {
				if (!getShowingProgressBar()) {
					break;
				}
				publish(i);
				if (i == 99) {
					i = 0;
				}

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					logger.error(StackTraceToString.getExceptionTrace(e));
				}
			}
			return null;
		}

		@Override
		protected void process(List<Integer> chunks) {
			if (chunks == null) {
				return;
			}
			if (chunks.isEmpty()) {
				return;
			}
			progressBar.setValue(chunks.get(0));
		}

		@Override
		protected void done() {
			progressBar.setValue(0);
		}
	}

	/**
	 * 显示、关闭进度条
	 *
	 * @param visibale
	 */
	protected void showProgressBar(final Boolean visibale) {
		if (visibale) {
			panelForProcessbar.removeAll();
			panelForProcessbar.add(progressBar, BorderLayout.CENTER);
			panelForProcessbar.updateUI();
			setShowingProgressBar(true);
			new ShowingProgressTask().execute();
		} else {
			panelForProcessbar.removeAll();
			panelForProcessbar.updateUI();
			setShowingProgressBar(false);
		}
	}

	public void setShowingProgressBar(Boolean showingProgressBar) {
		synchronized (lockForShowingProgressBar) {
			this.showingProgressBar = showingProgressBar;
		}
	}

	public Boolean getShowingProgressBar() {
		synchronized (lockForShowingProgressBar) {
			return showingProgressBar;
		}
	}

	public void setNeedProgressBar(Boolean needProgressBar) {
		this.needProgressBar = needProgressBar;
	}

	public Boolean getNeedProgressBar() {
		return needProgressBar;
	}

	protected void resetCombox(JComboBox cbx, Collection<String> names,
							   Boolean needNull, Object curVal) {
		if (names.isEmpty()) {
			return;
		}
		cbx.removeAllItems();
		if (needNull) {
			cbx.addItem(null);
		}
		for (String name : names) {
			cbx.addItem(name);
		}
		if (needNull || curVal != null) {
			cbx.setSelectedItem(curVal);
		}
	}

	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}

	/**
	 * 保存或更新对象
	 *
	 * @param bean
	 * @return 对象的PID
	 */
	public Integer saveOrUpdate(Object bean) {
	    //TODO
		return 0;
	}

	/**
	 * 删除对象实例
	 *
	 * @param className
	 * @param pid
	 * @return
	 */
	public Boolean deleteBean(String className, Integer pid) {
	    return false;
	}


	/**
	 * 文件过滤器
	 *
	 * @param msg
	 * @return
	 */
	public static FileFilter crtImgFileFilter(String msg) {
		msg += "(*.png, *.gif, *.jpg, *.jpeg, *.bmp)";
		return new FileNameExtensionFilter(msg, "png", "gif", "jpg", "jpeg",
				"bmp");
	}

	/**
	 * 获取基于面板的缩放图片
	 *
	 * @param comp
	 * @param img
	 * @return
	 */
	public static Image getScaleImg(JComponent comp, Image img) {
		if (img == null) {
			return null;
		}
		int width = comp.getWidth();
		int height = comp.getHeight();
		int imgWidth = img.getWidth(comp);
		int imgHeight = img.getHeight(comp);
		if (imgWidth <= width && imgHeight <= height) {
			return img;
		}

		BigDecimal panelWidth = BigDecimal.valueOf(width);
		BigDecimal panelHeight = BigDecimal.valueOf(height);
		BigDecimal imageWidth = BigDecimal.valueOf(imgWidth);
		BigDecimal imageHeight = BigDecimal.valueOf(imgHeight);

		BigDecimal widthScale = imageWidth.divide(panelWidth, 100,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal heightScale = imageHeight.divide(panelHeight, 100,
				BigDecimal.ROUND_HALF_UP);
		double scale = Math.max(widthScale.doubleValue(),
				heightScale.doubleValue());
		int realWidth = imageWidth.divide(BigDecimal.valueOf(scale), 100,
				BigDecimal.ROUND_HALF_UP).intValue();
		int realHeight = imageHeight.divide(BigDecimal.valueOf(scale), 100,
				BigDecimal.ROUND_HALF_UP).intValue();
		// logger.error(String.format("width[%d]", width));
		// logger.error(String.format("height[%d]", height));
		// logger.error(String.format("imgWidth[%d]", imgWidth));
		// logger.error(String.format("imgHeight[%d]", imgHeight));
		// logger.error(String.format("realWidth[%d]", realWidth));
		// logger.error(String.format("realHeight[%d]", realHeight));
		// logger.error(String.format("scale[%s]", scale));
		return img
				.getScaledInstance(realWidth, realHeight, Image.SCALE_DEFAULT);
	}

	/**
	 * 获取删除后需要选择的行标
	 *
	 * @param table
	 * @return
	 */
	public int getNextSelIndex(JTable table) {
		int rowCount = table.getRowCount();
		int[] selectedRows = table.getSelectedRows();
		int nextSelRowIdx = selectedRows[0];
		if (selectedRows.length == rowCount || nextSelRowIdx == rowCount - 1) {
			--nextSelRowIdx;
		}
		return nextSelRowIdx;
	}

	/**
	 * 滚动到选择行所处位置
	 *
	 * @param selRow
	 */
	public void scrollToSelRow(JTable table, int selRow) {
		table.getSelectionModel().setSelectionInterval(selRow, selRow);
		Rectangle rect = table.getCellRect(selRow, 0, true);
		table.scrollRectToVisible(rect);
	}

	public void setNeedQuanXian(Boolean needQuanXian) {
		this.needQuanXian = needQuanXian;
	}

	public Boolean getNeedQuanXian() {
		return needQuanXian;
	}




	private Boolean needProgressBar = false;
	private JProgressBar progressBar;
	private Boolean showingProgressBar = false;
	private Object lockForShowingProgressBar = new Object();

	public static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	protected JPanel contentPanel = new JPanel();
	protected JPanel panelForProcessbar = new JPanel();
	protected JPanel contentPanelInclueProcessbar = new JPanel();


	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Boolean hasInit = false;
	private Boolean needQuanXian = true;// 是否需要校验权限

	// 权限不足时的提示面板
	private JPanel errorPanel;
	private String iconName;
	private String title = "";

}
