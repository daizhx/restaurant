package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.dlgs.DlgBase;
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
public class AddBeanBaseDlg extends XDialog {


	public AddBeanBaseDlg(Window owner) {
		super(owner);
	}

	public void init() {
		// 创建各类控件
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createBtnPanel(), BorderLayout.NORTH);
		getContentPane().add(createAttrPanel(), BorderLayout.CENTER);
		getAttrPanel().requestFocus();

		// 设置对话框属性
		setTitle(getMessageSource().getMessage("AddBeanBaseDlg.title",
				new String[] { obj.getDspName() }));
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
		logger.trace("enter");
		if (getAttrPanel() == null) {
			setAttrPanel(new PanelForAttr(this));
		}
		getAttrPanel().setIwd(getIwd());
		getAttrPanel().setMessageSource(getMessageSource());
		getAttrPanel().setSessionFactory(getSessionFactory());
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
		addHotKeyForBtn(btnForCommit, KeyEvent.VK_F1);

		btnForCancel = new JButton(new ActionForCancel());
		btnForCancel.setText(String.format("%s(ESC)", btnForCancel.getText()));
		btnPanel.add(btnForCancel);
		addHotKeyForBtn(btnForCancel, KeyEvent.VK_ESCAPE);
		return btnPanel;
	}

	/**
	 * 重新设置属性面板中需要显示的列
	 */
	protected void setShowAttrsInAttrPanel() {
		logger.trace("enter");
		Iterator<Attr> it = obj.getAllAttrs().iterator();
		while (it.hasNext()) {
			Attr attr = it.next();
			if (!attr.getVisible()) {
				continue;
			}
			if (attr.getType() == AttrType.relatedObj) {
				if (attr.getRelationType() == RelationType.BothWayN_N
						|| attr.getRelationType() == RelationType.BothWay1_N) {
					continue;
				}
			}
			getAttrPanel().getSelfAttrs().add(attr);
		}
	}

	/**
	 * 仓库做特殊处理
	 */
	private void specialProForWarse() {
		Warehouse curCangKu = (Warehouse) getAttrPanel().getBean();
		if (!curCangKu.getMoRen()) {
			return;
		}

		String sql = "UPDATE Warehouse SET moRen = 0";
		dspDlg.executeUpdateSql(sql);
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
		Object curBean = getAttrPanel().getBean();
		if (curBean instanceof Goods) {
			Goods g = (Goods) curBean;
			g.setKuCunJiaGe(g.getJiBenJiaGe());
		}
		try {
			if (curBean instanceof CaiPingDaiLei) {
				CaiPingDaiLei dl = (CaiPingDaiLei) curBean;
				dl.setIsBenDi(true);
			}
			if (curBean instanceof CaiPingXiaoLei) {
				CaiPingXiaoLei xl = (CaiPingXiaoLei) curBean;
				xl.setIsBenDi(true);
			}
			if (curBean instanceof CaiPing) {
				CaiPing cp = (CaiPing) curBean;
				cp.setOldJiaGe(cp.getJiaGe());
				cp.setIsBenDi(true);
			}
		} catch (Exception ex) {
			logger.error(StackTraceToString.getExceptionTrace(ex));
		}
		return curBean;
	}

	/**
	 * 保存当前Bean，成功则返回保存的bean对象，否则返回null
	 *
	 * @return
	 */
	protected Object saveBean() {
		getAttrPanel().setStatus(PanelStatus.ForAdd);
		if (!getAttrPanel().updateBean()) {
			return null;
		}
		Object curBean = saveBeanBefore();
		Object ret = GlobalFunctions.execCRUD(GlobalFunctions.CRUD.INSERT,
				curBean);
		if (ret != ActionBase.SUCCESS) {
			JOptionPane.showMessageDialog(getOwner(), ret);
			return null;
		}
		Integer curBeanPID = GlobalFunctions.getBeanPID(curBean);
		// 如果是仓库，并且设置为默认仓库，需要把其它仓库设置为非默认
		if (obj.getName().equals("Warehouse")) {
			specialProForWarse();
		}
		saveBeanAfter(curBean, curBeanPID);
		return curBean;
	}

	private Object saveBeanAfter(Object curBean, Object pid) {
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setRootObject(curBean);
		ctx.setVariable("id", pid);
		Expression exp = parser.parseExpression(
				String.format("#{set%s(#id)}", obj.getIdAttr().getName()),
				new TemplateParserContext());
		exp.getValue(ctx);

		if (childDspDlg != null) {
			childDspDlg.refalseParentTree(curBean);
			setVisible(true);
			dispose();
			return null;
		} else if (dspDlg != null) {
			dspDlg.reflashTablePanel();
			dspDlg.setSelectedBean(curBean);
		} else if (selDlg != null) {
			selDlg.reflash();
		} else if (qxglPanel != null) {
			if (obj.getName().equals("YongHuZu")) {
				qxglPanel.reflashUserTypePanel();
			} else if (obj.getName().equals("Staff")) {
				qxglPanel.setDefaultQuanXian((Staff) curBean);
				qxglPanel.reflashUserPanel();
			}
		}

		// 生成一个新 的BEAN，并计放置在属性面板中
		getAttrPanel().setBean(createABeanInstanceWithClone(curBean));
		getAttrPanel().requestFocus();
		getAttrPanel().setDataHasChange(false);
		return curBean;
	}

	protected void cancel() {
		if (attrPanel.getDataHasChange()) {// 界面数据已经被修改，提示用户是否保存
			Integer iRet = JOptionPane.showConfirmDialog(getContentPane(),
					getMessage("ShiFuoBaoCun"), getMessage("Tips"),
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
			super(getMessage("AddBeanBaseDlg.commitBtn.title"));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			commit();
		}
	}

	protected class ActionForCancel extends AbstractAction {
		public ActionForCancel() {
			super(getMessage("AddBeanBaseDlg.cancleBtn.title"));
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
	public Object createABeanInstanceWithDefault() {
		Object beanInstance = null;
		try {
			beanInstance = Class.forName(beansPackageName + obj.getName())
					.newInstance();
		} catch (Exception e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
		}
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setRootObject(beanInstance);

		for (int i = 0; i < obj.getAllAttrs().size(); i++) {
			Attr attr = obj.getAllAttrs().get(i);
			if (attr.getType() == AttrType.idAttr
					|| attr.getType() == AttrType.relatedObj) {
				continue;
			}
			Object defaultValue = attr.getDefaultValue();
			if (defaultValue != null && !defaultValue.equals("")) {
				Expression exp;
				ctx.setVariable("defaultValue", defaultValue);

				exp = parser.parseExpression(String.format(
						"#{set%s(#defaultValue)}", attr.getName()),
						new TemplateParserContext());
				exp.getValue(ctx);
			}
		}

		return beanInstance;
	}

	/**
	 * 利用已存在的对象实例克隆出新的实例
	 *
	 * @param otherBeanInstance
	 * @return
	 */
	public Object createABeanInstanceWithClone(Object otherBeanInstance) {
		Object beanInstance = null;
		try {
			beanInstance = Class.forName(beansPackageName + obj.getName())
					.newInstance();
		} catch (Exception e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
		}

		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setRootObject(beanInstance);
		ctx.setVariable("otherBeanInstance", otherBeanInstance);
		Expression exp;
		for (int i = 0; i < obj.getAllAttrs().size(); i++) {
			Attr attr = obj.getAllAttrs().get(i);
			if (attr.getType() != AttrType.relatedObj) {
				Object attrValue = null;
				switch (attr.getFillMode()) {
					case INCREASE:
						exp = parser.parseExpression(
								String.format("#{get%s()}", attr.getName()),
								new TemplateParserContext());
						Object oldValue = exp.getValue(otherBeanInstance);
						attrValue = createIncreaseValue(oldValue);
						break;
					case CLEAN:
						attrValue = null;
						break;
					case USE_DEFAULT:
						attrValue = attr.getDefaultValue();
						break;
					case USE_OLDVALUE:
						exp = parser.parseExpression(
								String.format("#{get%s()}", attr.getName()),
								new TemplateParserContext());
						attrValue = exp.getValue(otherBeanInstance);
						break;
					default:
						break;
				}
				ctx.setVariable("attrValue", attrValue);
				String strToExcute = String.format("#{set%s(#attrValue)}",
						attr.getName());
				exp = parser.parseExpression(strToExcute,
						new TemplateParserContext());
				exp.getValue(ctx);
			} else {
				String strToExcute;
				if (attr.getRelationType() == RelationType.BothWayN_1) {// N-1
					strToExcute = String.format(
							"#{set%s(#otherBeanInstance.get%s())}",
							attr.getName(), attr.getName());
					exp = parser.parseExpression(strToExcute,
							new TemplateParserContext());
					exp.getValue(ctx);
				}
			}
		}

		return beanInstance;
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

	public void setAttrPanel(PanelForAttr attrPanel) {
		this.attrPanel = attrPanel;
	}

	public PanelForAttr getAttrPanel() {
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

	private PanelForAttr attrPanel;

	protected JButton btnForCommit;
	protected JButton btnForCancel;
	private static String beansPackageName = "com.lemontree.framework.beans.";
	private ExpressionParser parser = new SpelExpressionParser();
}
