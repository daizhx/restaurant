package com.smtech.restaurant.setting;

import com.smtech.restaurant.entities.ColumnInfo;
import com.smtech.swing.common.view.InputIntWithLabel;
import com.smtech.swing.common.view.InputStringWithLabel;
import com.smtech.swing.common.view.InputWithLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

/**
 *
 * 编辑实体对象面板
 *
 */
public class PanelForBean<T> extends JPanel {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 本面板当前的对象实例，是各个属性值的来源
	 */
    private T bean;

	public PanelForBean(T t) {
		bean = t;
		init();
	}

	@Override
	public void requestFocus() {
		super.requestFocus();

	}

	/**
	 * 生成
	 */
	public void init() {
		createParas();
		layoutParas();
	}

	/**
	 * 创建各个参数
	 */
	private void createParas() {
		Class<?> cls = bean.getClass();
		Field[] fields = cls.getDeclaredFields();
		// 存放自身属性的面板
		panelForSelfAttr = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            //对象属性类对象
            Class<?> fcls = f.getType();
			Annotation anno = f.getAnnotation(ColumnInfo.class);
            InputWithLabel para;
            if(fcls == String.class){
                para = new InputStringWithLabel();
            } else if(fcls == Integer.class){
                para = new InputIntWithLabel();
            } else {
                para = new InputStringWithLabel();
            }

            String labelName = null;
            if(anno != null){
            	labelName = ((ColumnInfo) anno).dspName();
			}
			if(labelName == null){
				labelName = f.getName();
			}
            para.setLabel(labelName);

			//获取属性值
            try {
                //打开私有访问，否则不能获取private属性
                f.setAccessible(true);
                Object v = f.get(bean);
                para.setValue(v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // 添加监听器，于便判断界面的数据是否被修改r
			JComponent componet = para;
			if (componet instanceof JTextField) {
				JTextField tf = (JTextField) componet;
				tf.getDocument().addDocumentListener(new DocListener());
			} else if (componet instanceof JComboBox) {
				JComboBox cbx = (JComboBox) componet;
				cbx.addItemListener(new ItemList());
			} else if (componet instanceof JCheckBox) {
				JCheckBox cx = (JCheckBox) componet;
				cx.addItemListener(new ItemList());
			}
			parasForInput.add(para);
			nameToPara.put(f.getName(), para);
		}
	}

	/**
	 * 布局各个参数，调整各个参数的大小
	 */
	protected void layoutParas() {
		// 调用属性面板的大小
		Dimension fixSize = new Dimension(
				2 * (InputWithLabel.fixSizeForLable.width
						+ InputWithLabel.fixSizeForComponent.width + 20),
				(parasForInput.size() / 2)
						* (InputWithLabel.fixSizeForLable.height + 10));
		UIUtil.fixSize(panelForSelfAttr, fixSize);

		for (InputWithLabel para : parasForInput) {
            para.setPreferredSize(new Dimension(200,40));
			panelForSelfAttr.add(para);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelForSelfAttr);

		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * 根据属性名称获取相应的控件
	 *
	 * @param attrName
	 * @return
	 */
	protected InputWithLabel getParaByName(String attrName) {
		return nameToPara.get(attrName);
	}

	public void reflash() {}

	/**
	 * 判断属性是否需要更新
	 *
	 * @param attr
	 * @return
	 */

	/**
	 * 根据当前界面上各参数的值来更新BEAN
	 */
	public void updateBean() {
		for(String key : nameToPara.keySet()){
		    InputWithLabel il = nameToPara.get(key);
		    Object v = il.getValue();
		    if(v != null) {
                setFieldValueByFieldName(key, bean, v);
            }
        }
	}


    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private void setFieldValueByFieldName(String fieldName, Object object,Object value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

	public void setStatus(PanelStatus status) {
		this.status = status;
	}

	public PanelStatus getStatus() {
		return status;
	}






	public void setParasForInput(List<InputWithLabel> parasForInput) {
		this.parasForInput = parasForInput;
	}

	public List<InputWithLabel> getParasForInput() {
		return parasForInput;
	}

	public void setDataHasChange(Boolean dataHasChange) {
		this.dataHasChange = dataHasChange;
	}

	public Boolean getDataHasChange() {
		return dataHasChange;
	}

	private InputWithLabel createParaForInput(Field field) {
		InputWithLabel para = null;

		para.init();
		return para;
	}



	/**
	 * 面板状态
	 *
	 */
	public enum PanelStatus {
		ForAdd, ForRmv, ForMod,
	}

	private class DocListener implements DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			dataHasChange = true;
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			dataHasChange = true;
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			dataHasChange = true;
		}
	}

	private class ItemList implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			dataHasChange = true;
		}

	}

	/**
	 * 重新设置ID属性的格式
	 */
	private void resetIdFormat() {}

	/**
	 * 获取父类对象的ID
	 *
	 * @param parentPara
	 * @return
	 */


	/**
	 * 数据是否被修改
	 */
	private Boolean dataHasChange = false;
	/**
	 * 放置本对象实例的面板
	 */
	protected JPanel panelForSelfAttr;

	/**
	 * 自身属性（包含N-1的关联对象）
	 */
	public List<InputWithLabel> parasForInput = new Vector<InputWithLabel>();
	// KEY为属性名称，VALUE为该属性对应的控件
	private Map<String, InputWithLabel> nameToPara = new HashMap<String, InputWithLabel>();

	protected PanelStatus status = PanelStatus.ForAdd;




}
