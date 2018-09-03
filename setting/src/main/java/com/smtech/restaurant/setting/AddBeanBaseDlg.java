package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.util.CommonFunc;
import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import jdk.nashorn.internal.runtime.GlobalFunctions;

import javax.management.relation.RelationType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.util.Iterator;

/**
 * 增加某对象实例
 *
 */
@SuppressWarnings("serial")
public class AddBeanBaseDlg<T> extends XDialog {

	private T bean;

	public AddBeanBaseDlg(Window owner) {
		super(owner);
		init();
	}

	public void init() {
		// 创建各类控件
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createBtnPanel(), BorderLayout.NORTH);
		getContentPane().add(createAttrPanel(), BorderLayout.CENTER);
		getAttrPanel().requestFocus();

		// 设置对话框属性
		setTitle("增加");
		setModal(true);
		setResizable(false);
		setPreferredSize(new Dimension(850, 400));
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * 创建属性面板
	 */
	protected JPanel createAttrPanel() {
		if (getAttrPanel() == null) {
			setAttrPanel(new PanelForBean(this,bean));
		}
		setShowAttrsInAttrPanel();
		getAttrPanel().init();
		return getAttrPanel();
	}

	/**
	 * 创建按钮面板
	 *
	 * @return
	 */
	protected JPanel createBtnPanel() {
		JPanel btnPanel = new JPanel(new GridLayout());
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		btnPanel.setPreferredSize(new Dimension(0, 50));

		btnForCommit = new JButton(new ActionForCommit());
		btnForCommit.setText(String.format("%s(F1)", btnForCommit.getText()));
		btnPanel.add(btnForCommit);
//		addHotKeyForBtn(btnForCommit, KeyEvent.VK_F1);

		btnForCancel = new JButton(new ActionForCancel());
		btnForCancel.setText(String.format("%s(ESC)", btnForCancel.getText()));
		btnPanel.add(btnForCancel);
//		addHotKeyForBtn(btnForCancel, KeyEvent.VK_ESCAPE);
		return btnPanel;
	}

	/**
	 * 重新设置属性面板中需要显示的列
	 */
	protected void setShowAttrsInAttrPanel() {

    }


	protected void commit() {
		// 保存当前编辑的Bean对象
		Object curBean = saveBean();
		if (curBean == null) {
			return;
		}
		// 通过当前保存的bena克隆一个新bean继续编辑保存
		crtNewBean(curBean);
	}

	/**
	 * 将当前已经保存的Bean克隆创建新的bean
	 *
	 * @param curBean
	 */
	protected void crtNewBean(Object curBean) {
		// 生成一个新 的BEAN，并计放置在属性面板中
		getAttrPanel().setBean(createABeanInstanceWithClone(curBean));
		getAttrPanel().requestFocus();
		getAttrPanel().setDataHasChange(false);
	}

	/**
	 * 保存前预先处理
	 *
	 * @return
	 */
	private Object saveBeanBefore() {
	    return null;
    }

	/**
	 * 保存当前Bean，成功则返回保存的bean对象，否则返回null
	 *
	 * @return
	 */
	protected Object saveBean() {
	    return null;
    }

	private Object saveBeanAfter(Object curBean, Object pid) {
	    return null;
    }

	protected void cancel() {
		if (attrPanel.getDataHasChange()) {// 界面数据已经被修改，提示用户是否保存
			Integer iRet = JOptionPane.showConfirmDialog(getContentPane(),
					"是否保存", "提示",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (JOptionPane.YES_OPTION == iRet) {
				if (!getAttrPanel().updateBean()) {// 界面填写不完整，提示用户重新填写
					return;
				}
				btnForCommit.doClick();
			} else if (JOptionPane.CANCEL_OPTION == iRet) {
				return;
			}
		}

		setVisible(false);
		dispose();
	}

	protected class ActionForCommit extends AbstractAction {
		public ActionForCommit() {
			super("确定");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			commit();
		}
	}

	protected class ActionForCancel extends AbstractAction {
		public ActionForCancel() {
			super("取消");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cancel();
		}
	}

	/**
	 * 创建一个对象实例，其属性使用默认值（填写在对象模板中）
	 *
	 * @return
	 */


	/**
	 * 利用已存在的对象实例克隆出新的实例
	 *
	 * @param otherBeanInstance
	 * @return
	 */
	public Object createABeanInstanceWithClone(Object otherBeanInstance) {
	    return null;
    }

	/**
	 * 在oldValue的基础上创建一个递增的值，如oldValue为"123"，那么新的值为"124"
	 *
	 * @return
	 */
	public Object createIncreaseValue(Object oldValue) {
		// 整型可以直接加1然后返回
		if (oldValue instanceof Integer) {
			return (Integer) oldValue + 1;
		} else if (oldValue instanceof String) {
			return createIncStr((String) oldValue);
		} else {
			return null;
		}
	}

	public static String createIncStr(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		String incrStr = CommonFunc.createIncStr(str);
		return incrStr;
	}

	/**
	 * 由某些对象有特殊的需求，这些对象需要继承本类，然后重写一些方法
	 *
	 * @param beanName
	 * @return
	 */
	public static AddBeanBaseDlg getDlgByBeanName(String beanName, Window owner) {
		final String prefix = "com.lemontree.client.ComCompent.Add";
		try {
			AddBeanBaseDlg dlg = null;
			if (owner != null) {
				@SuppressWarnings("rawtypes")
				Constructor demoConstructor = Class.forName(
						String.format("%s%sDlg", prefix, beanName))
						.getConstructor(new Class[] { Window.class });
				dlg = (AddBeanBaseDlg) demoConstructor
						.newInstance(new Object[] { owner });
			} else {
				dlg = (AddBeanBaseDlg) Class.forName(
						String.format("%s%sDlg", prefix, beanName))
						.newInstance();
			}

			return dlg;
		} catch (Exception e) {
			if (owner != null) {
				return new AddBeanBaseDlg(owner);
			} else {
				return new AddBeanBaseDlg();
			}

		}
	}

	public void setAttrPanel(PanelForBean attrPanel) {
		this.attrPanel = attrPanel;
	}

	public PanelForBean getAttrPanel() {
		return attrPanel;
	}

	public void setDspDlg(DspBeanBaseDlg dspDlg) {
		this.dspDlg = dspDlg;
	}

	public DspBeanBaseDlg getDspDlg() {
		return dspDlg;
	}

	public void setSelDlg(SelectBeanDlgBase selDlg) {
		this.selDlg = selDlg;
	}

	public SelectBeanDlgBase getSelDlg() {
		return selDlg;
	}

	public void setBeanInstane(Object beanInstane) {
		this.beanInstane = beanInstane;
		getAttrPanel().setBean(beanInstane);
	}

	public Object getBeanInstane() {
		return beanInstane;
	}

	public void requestFocus() {
		super.requestFocus();
		getAttrPanel().requestFocus();
	}

	public void setChildDspDlg(DspBeanBaseDlg childDspDlg) {
		this.childDspDlg = childDspDlg;
	}

	public DspBeanBaseDlg getChildDspDlg() {
		return childDspDlg;
	}

	public void setQxglPanel(QuanXianGuanLi qxglPanel) {
		this.qxglPanel = qxglPanel;
	}

	public QuanXianGuanLi getQxglPanel() {
		return qxglPanel;
	}

	// 子类的显示对话框
	// （本对话框有可能是由子类面板中，点击导航树按钮产生，此时增加后，需要刷新子类面板）
	private DspBeanBaseDlg childDspDlg;

	// 对应的对象实例
	private Object beanInstane;

	// 父类面板
	protected DspBeanBaseDlg dspDlg;
	private SelectBeanDlgBase selDlg;
	private QuanXianGuanLi qxglPanel;

	private PanelForBean attrPanel;

	protected JButton btnForCommit;
	protected JButton btnForCancel;
	private static String beansPackageName = "com.lemontree.framework.beans.";
}
