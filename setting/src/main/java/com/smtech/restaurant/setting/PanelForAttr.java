package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.restaurant.setting.paraforinput.ParaForInputBase;
import com.smtech.swing.common.util.CommonFunc;
import com.smtech.swing.common.util.UIUtil;
import com.smtech.swing.common.view.TextFieldEx;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.relation.RelationType;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

/**
 * 用于放置属性的面板
 *
 */
public class PanelForAttr extends JPanel {

    Class<?> cls;

	public PanelForAttr(Window owner,Class<?> cls) {
		this.owner = owner;
		this.cls = cls;
	}

	@Override
	public void requestFocus() {
		super.requestFocus();

	}

	/**
	 * 生成
	 */
	public void init() {

        Field[] fields = cls.getFields();

		createParas(fields);
		layoutParas();
	}

	/**
	 * 创建各个参数
	 */
	private void createParas(Field[] fields) {
		// 存放自身属性的面板
		panelForSelfAttr = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            Class<?> cls = f.getDeclaringClass();

			ParaForInputBase para = createParaForInput(attr);
			// 添加监听器，于便判断界面的数据是否被修改r
			JComponent componet = para.getComponent();
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
			nameToPara.put(attr.getName(), para);
		}
	}

	/**
	 * 布局各个参数，调整各个参数的大小
	 */
	protected void layoutParas() {
		// 调用属性面板的大小
		Dimension fixSize = new Dimension(
				2 * (ParaForInputBase.fixSizeForLable.width
						+ ParaForInputBase.fixSizeForComponent.width + 20),
				(selfAttrs.size() / 2)
						* (ParaForInputBase.fixSizeForLable.height + 10));
		UIUtil.fixSize(panelForSelfAttr, fixSize);

		for (ParaForInputBase para : parasForInput) {
			panelForSelfAttr.add(para.getContentPanel());
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
	protected ParaForInputBase getParaByName(String attrName) {
		return nameToPara.get(attrName);
	}

	public void reflash() {
		if (null == bean) {// 无实例
			for (int i = 0; i < parasForInput.size(); i++) {
				parasForInput.get(i).cleanData();
			}
		} else { // 有实例
			for (int i = 0; i < parasForInput.size(); i++) {
				ParaForInputBase para = parasForInput.get(i);
				String funcName = String.format("#{get%s()}", selfAttrs.get(i)
						.getName());
				Expression exp = parser.parseExpression(funcName,
						new TemplateParserContext());
				para.setValue(exp.getValue(bean));
			}
		}

		resetIdFormat();

		if (status == PanelStatus.ForMod) {// 如果是MOD状态，需要禁止编辑不允许修改的属性
			for (int i = 0; i < parasForInput.size(); i++) {
				ParaForInputBase para = parasForInput.get(i);
				Attr attr = selfAttrs.get(i);
				para.setEnabled(attr.getCanBeMod());
				// setGoodsNameStatus(para, attr); // 禁止被商品变动单关联的物品修改该名称属性
			}
			if (paraID != null) {
				paraID.setEnabled(false);
			}
		} else {
			for (int i = 0; i < parasForInput.size(); i++) {
				parasForInput.get(i).setEnabled(true);
			}
		}
		if (paraParent != null) {
			if (status == PanelStatus.ForMod) {
				paraParent.setEnabled(paraParent.getAttr().getCanBeMod());
			} else if (status == PanelStatus.ForAdd) {
				paraParent.setEnabled(false); // 添加不允许更改
			}
		}
	}

	/**
	 * 判断属性是否需要更新
	 *
	 * @param attr
	 * @return
	 */
	private Boolean needUpgAttr(Attr attr) {
		if (getStatus() != PanelStatus.ForMod) {
			// 非修改模式下都能修改
			return true;
		}
		String objName = attr.getMOCName();
		String attrName = attr.getName();

		List<String> lst = GlobalAttributes.CLASS_EXECLUDE_ATTR.get(objName);
		if (lst == null) {
			return true;
		}

		if (lst.contains(attrName)) {
			// 该属性不需要更新
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据当前界面上各参数的值来更新BEAN
	 */
	public Boolean updateBean() {
		if (paraID != null) {
			String tips = getMessageSource().getMessage("DspBeanBaseDlg.idErr",
					new String[] { paraID.getAttr().getDspName() });
			String idVal = (String) paraID.getValue();// 自身ID的值
			if (idVal == null) {
				JOptionPane.showMessageDialog(owner, tips);
				paraID.requestFocus();
				return false;
			}

			if (!paraID.getAttr().getMOCName().equals("ZuoTai")
					&& !paraID.getAttr().getMOCName().equals("XiTongYongHu")
					&& !paraID.getAttr().getMOCName().equals("Crm_member")) {
				idVal = idVal.replace("_", "");
				Integer len = getIdLen(paraID.getAttr());// ID属性的长度
				String parentIdVal = getParentIdVal(paraParent);// 父类ID的值
				// 添加时才检查id是否符合规则，避免已经存在的ID不符合规则时候无法保存，避免修改父类为null时提示错误问题
				boolean flag = status == PanelStatus.ForAdd
						|| (status == PanelStatus.ForMod && paraID.getAttr()
						.getCanBeMod());
				if (len >= 0 && flag
						&& idVal.length() != (len + parentIdVal.length())) {
					JOptionPane.showMessageDialog(owner, tips);
					paraID.requestFocus();
					return false;
				}
			}
		}

		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setRootObject(bean);
		for (int i = 0; i < selfAttrs.size(); i++) {
			Attr attr = selfAttrs.get(i);
			if (!needUpgAttr(attr)) {
				continue;
			}
			Object attrValue = parasForInput.get(i).getValue();
			if (attr.getType() == AttrType.relatedObj) {// 命名属性不能为空
				if (!attr.getCanBeNull() && attrValue == null) {
					JOptionPane.showMessageDialog(this,
							getMessage("PleaseSelect") + attr.getDspName(),
							getMessage("Error"), JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} else if (!attr.getCanBeNull()) {
				if (attrValue == null || attrValue.equals("")) {
					JOptionPane.showMessageDialog(this,
							getMessage("PleaseFill") + attr.getDspName(),
							getMessage("Error"), JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}

			// 增加状态下，需要检查不允许重复的属性
			if (!attr.getCanBeRepeat() && beanIsExist(attr, attrValue)) {
				parasForInput.get(i).requestFocus();
				JOptionPane.showMessageDialog(
						owner,
						getMessageSource().getMessage(
								"beanWhitAttrExsit",
								new String[] { attr.getDspName(),
										String.valueOf(attrValue) }),
						getMessage("Error"), JOptionPane.ERROR_MESSAGE);
				return false;
			}
			Expression exp;
			ctx.setVariable("arrtValue", attrValue);
			exp = parser.parseExpression(
					String.format("#{set%s(#arrtValue)}", attr.getName()),
					new TemplateParserContext());
			exp.getValue(ctx);
		}
		return true;
	}

	public void setStatus(PanelStatus status) {
		this.status = status;
	}

	public PanelStatus getStatus() {
		return status;
	}






	public void setParasForInput(List<ParaForInputBase> parasForInput) {
		this.parasForInput = parasForInput;
	}

	public List<ParaForInputBase> getParasForInput() {
		return parasForInput;
	}

	public void setDataHasChange(Boolean dataHasChange) {
		this.dataHasChange = dataHasChange;
	}

	public Boolean getDataHasChange() {
		return dataHasChange;
	}

	private ParaForInputBase createParaForInput(Field field) {
		ParaForInputBase para = null;

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
	private void resetIdFormat() {
		paraID = null;// 本对象的ID属性
		paraParent = null;// 父类对象
		for (ParaForInputBase para : parasForInput) {
			Attr attr = para.getAttr();
			if (paraID == null && attr.getType() == AttrType.logicIdAttr) {
				paraID = (ParaForInputID) para;
			}
			if (paraParent == null && attr.getType() == AttrType.relatedObj
					&& attr.getRelationType() == RelationType.BothWayN_1
					&& !attr.getName().equals("AutoSubWarehouse")) {
				paraParent = (ParaForInputOtherObj) para;
			}
		}
		if (paraID == null) {
			return;
		}

		if (paraID.getAttr().getMOCName().equals("BaseStation")
				&& status == PanelStatus.ForAdd) {
			JTextField tf = new TextFieldEx();
			tf.setDocument(new PatternConsoleDocument("^[1-9]{0,1}$"));
			paraID.setComponent(tf);
			return;
		}
		if (paraParent != null && !paraParent.getAttr().getCanBeMod()) {
			paraParent.setEnabled(false);
		}
		if (paraID.getAttr().getMOCName().equals("ZuoTai")) {
			return;
		}
		if (paraID.getAttr().getMOCName().equals("Crm_member")) {
			return;
		}
		if (paraID.getAttr().getName().equals("StaffID")
				&& bean instanceof Staff && status == PanelStatus.ForMod) {
			return;
		}

		if (paraParent != null) {
			if (status == PanelStatus.ForMod) {
				paraParent.setEnabled(paraParent.getAttr().getCanBeMod());
				if (paraParent.getAttr().getCanBeMod()) {
					// 父类属性能被修改，则不在其编号前强加父类ID
					return;
				}
			} else if (status == PanelStatus.ForAdd) {
				paraParent.setEnabled(false); // 添加不允许更改
			}
		}

		Integer len = getIdLen(paraID.getAttr());
		String strFormat = "";
		for (int i = 0; i < len; i++) {
			strFormat += "A";
		}
		String parentID = getParentIdVal(paraParent).replace("U", "'U")
				.replace("L", "'L").replace("A", "'A").replace("H", "'H");
		strFormat = String.format("%s%s", parentID, strFormat);
		try {
			String funcName = String.format("#{get%s()}", paraID.getAttr()
					.getName());
			Expression exp = parser.parseExpression(funcName,
					new TemplateParserContext());
			String orgID = (String) exp.getValue(bean);// 默认ID
			MaskFormatter formatter = new MaskFormatter(strFormat);
			formatter.setPlaceholderCharacter('_');
			JTextField tf = null;
			if (len < 0) { // 长度小于零则不限制
				tf = new JTextField(25);
				PatternConsoleDocument doc = new PatternConsoleDocument("\\w");
				tf.setDocument(doc);
			} else {
				tf = new JFormattedTextField(formatter);
			}
			paraID.setComponent(tf);
			if (status == PanelStatus.ForAdd && orgID != null
					&& orgID.length() > parentID.length()) {
				orgID = orgID.substring(parentID.length());
			}
			if (status == PanelStatus.ForAdd && bean != null) {
				parentID = parentID.equals("") ? null : parentID.replace("'",
						"");
				Class cls = bean.getClass();
				while (cls.getSuperclass() != Object.class) {
					cls = cls.getSuperclass();
				}
				try {
					orgID = CommonFunc.nextIdGen(cls.getSimpleName(), parentID);
				} catch (Error e) {
					logger.error(StackTraceToString.getExceptionTrace(e));
				}
			}
			tf.setText(orgID);
		} catch (Exception e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
		}
	}

	/**
	 * 获取父类对象的ID
	 *
	 * @param parentPara
	 * @return
	 */
	private String getParentIdVal(ParaForInputOtherObj parentPara) {
		if (parentPara == null) {
			return "";
		}
		Attr attr = parentPara.getAttr();

		Set<String> spcObj = new HashSet<String>();
		spcObj.add("BaseStation");
		spcObj.add("Terminal");
		// 点菜宝基站编号位数为1，所以不能随父类
		if (spcObj.contains(attr.getMOCName())) {
			return "";
		}

		Object val = parentPara.getValue();
		if (val == null) {
			return "";
		}

		Obj obj = iwd.getObjByName(attr.getRelatedObjName());
		String funcName = String.format("#{get%s()}",
				IWD.setFirstCharToUpper(obj.getLogicIdAttr().getName()));
		Expression exp = parser.parseExpression(funcName,
				new TemplateParserContext());
		return (String) exp.getValue(val);
	}

	/**
	 * 获取下一个自增逻辑id
	 *
	 * @param tableName
	 * @param parentID
	 * @return
	 */
	public static String nextIdGen(String tableName, String parentID) {
		return CommonFunc.nextIdGen(tableName, parentID);
	}

	public static Integer getIdLen(Attr attr) {
		return CommonFunc.getIdLen(attr);
	}

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
	public List<ParaForInputBase> parasForInput = new Vector<ParaForInputBase>();
	// KEY为属性名称，VALUE为该属性对应的控件
	private Map<String, ParaForInputBase> nameToPara = new HashMap<String, ParaForInputBase>();

	/**
	 * 本面板当前的对象实例，是各个属性值的来源
	 */

	private Window owner;
	protected PanelStatus status = PanelStatus.ForAdd;



	protected Logger logger = LoggerFactory.getLogger(getClass());

}
