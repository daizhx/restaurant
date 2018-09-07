package com.smtech.swing.common.view;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

/**
 * 放置关联对象的属性
 *
 */
public class InputOtherObjWithLabel extends InputWithLabel{


	@Override
	public void requestFocus() {
		comboBox.requestFocus();
	}

	@Override
	public JComponent createComponent() {
		comboBox = new JComboBox();
		final ListCellRenderer defaultRenderer = comboBox.getRenderer();
		ListCellRenderer renderer = new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
														  Object value, int index, boolean isSelected,
														  boolean cellHasFocus) {
				if (value instanceof JComponent) {
					return (Component) value;
				}
				return defaultRenderer.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
			}
		};
		comboBox.setRenderer(renderer);

		reflash();
		return comboBox;
	}

	@Override
	public Object getValue() {
		return bean;
	}

	@Override
	public void setValue(Object value) {
		bean = value;
		reflash();
	}

	@Override
	public void setEnabled(Boolean enabled) {
		comboBox.setEnabled(enabled);
	}


	@Override
	public void cleanData() {
		bean = null;
		reflash();
	}

	@Override
	public JComponent getComponent() {
		return comboBox;
	}

	/**
	 * 显示选择对话框
	 */
	private void showSelDlg() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

			}
		});
	}

	private void reflash() {}

	private void addBtn() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setShowOtherShopBean(boolean showOtherShopBean) {
		this.showOtherShopBean = showOtherShopBean;
	}

	public boolean isShowOtherShopBean() {
		return showOtherShopBean;
	}

	public void setShowAllShopBean(boolean showAllShopBean) {
		this.showAllShopBean = showAllShopBean;
	}

	public boolean isShowAllShopBean() {
		return showAllShopBean;
	}

	/**
	 * 新增
	 */
	class XinZengAction extends AbstractAction {
		XinZengAction() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
		}
	}

	private boolean showOtherShopBean = false;
	private boolean showAllShopBean = false;
	/**
	 * 当前编辑框中的对象实例
	 */
	private Object bean;
	private JComboBox comboBox;
	private Boolean shiFuoChuFaBiaoHua = true;// 当前选中项目改变是否触发变化

	// 该对象的所有实例
	private List beans = new Vector();

	private SessionFactory sessionFactory;

}
