package com.smtech.restaurant.setting;

import com.smtech.swing.common.util.CommonFunc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 编辑实体对话框，新增或修改实体对象
 *
 */
@SuppressWarnings("serial")
public class DlgEditBean<T> extends XDialog {

    // 子类的显示对话框
    // （本对话框有可能是由子类面板中，点击导航树按钮产生，此时增加后，需要刷新子类面板）
    private BeanPresenter childDspDlg;

    // 父类面板
    protected BeanPresenter dspDlg;
//	private SelectBeanDlgBase selDlg;
//	private QuanXianGuanLi qxglPanel;

    private PanelForBean beanPanel;

    protected JButton btnForCommit;
    protected JButton btnForCancel;

    //操作的实体对象
	private T bean;

	private BeanEditInteract beanEditInteract;

	public interface BeanEditInteract<T>{
		//新增对象
		void addBean(T t);
		//更新对象
		void updateBean(T t);
	}

    public BeanEditInteract getBeanEditInteract() {
        return beanEditInteract;
    }

    public void setBeanEditInteract(BeanEditInteract beanEditInteract) {
        this.beanEditInteract = beanEditInteract;
    }

    public DlgEditBean(Window owner, T t) {
		super(owner);
		this.bean = t;
		init();
	}


    public void init() {
		// 创建各类控件
        beanPanel = new PanelForBean<T>(bean);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createBtnPanel(), BorderLayout.NORTH);
		getContentPane().add(beanPanel, BorderLayout.CENTER);

		// 设置对话框属性
		setTitle("增加");
		setModal(true);
		setResizable(false);
		setPreferredSize(new Dimension(850, 400));
		pack();
		setLocationRelativeTo(null);
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
	    //获取输入的值
	    beanPanel.updateBean();

		// 保存当前编辑的Bean对象，如果bean的ID大于0，更新对象，否则为新增对象。
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor("id", bean.getClass());
            Method method = pd.getReadMethod();
            int id = (int) method.invoke(bean);

            //设置bean的属性值


            if(id == 0){
                if(beanEditInteract != null){
                    beanEditInteract.addBean(bean);
                }
            }else{
                if(beanEditInteract != null){
                    beanEditInteract.updateBean(bean);
                }
            }


        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        // 通过当前保存的bena克隆一个新bean继续编辑保存
//		crtNewBean(curBean);
	}

	/**
	 * 将当前已经保存的Bean克隆创建新的bean
	 *
	 * @param curBean
	 */
	protected void crtNewBean(Object curBean) {
		// 生成一个新 的BEAN，并计放置在属性面板中
//		getBeanPanel().setBean(createABeanInstanceWithClone(curBean));
//		getBeanPanel().requestFocus();
//		getBeanPanel().setDataHasChange(false);
	}

	/**
	 * 保存前预先处理
	 *
	 * @return
	 */
	private Object saveBeanBefore() {
	    return null;
    }


	private Object saveBeanAfter(Object curBean, Object pid) {
	    return null;
    }

	protected void cancel() {
		if (beanPanel.getDataHasChange()) {// 界面数据已经被修改，提示用户是否保存
			Integer iRet = JOptionPane.showConfirmDialog(getContentPane(),
					"是否保存", "提示",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (JOptionPane.YES_OPTION == iRet) {
				beanPanel.updateBean();
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
	 * @return
	 */



	public void setDspDlg(BeanPresenter dspDlg) {
		this.dspDlg = dspDlg;
	}

	public BeanPresenter getDspDlg() {
		return dspDlg;
	}



	public void requestFocus() {
		super.requestFocus();
	}

	public void setChildDspDlg(BeanPresenter childDspDlg) {
		this.childDspDlg = childDspDlg;
	}

	public BeanPresenter getChildDspDlg() {
		return childDspDlg;
	}



}
