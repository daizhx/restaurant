package com.smtech.swing.common.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputEnumWithLabel extends InputWithLabel {

	@Override
	public void setEnabled(Boolean enabled) {
		comboBox.setEnabled(enabled);
	}

	@Override
	public JComponent createComponent() {
		comboBox = new JComboBox();
		//TODO
		comboBox.setEditable(false);
		return comboBox;
	}

	@Override
	public void requestFocus() {
		comboBox.requestFocus();
	}

	@Override
	public Object getValue() {
		// 枚举名称
		return null;
	}

	@Override
	public void setValue(Object value) {
		// 枚举名称

	}

	@Override
	public void cleanData() {
		comboBox.setSelectedItem(null);
	}

	@Override
	public JComponent getComponent() {
		return comboBox;
	}

	private JComboBox comboBox;

//	private Vector<EnumDef> enumItems;
//
//	private ExpressionParser parser = new SpelExpressionParser();

	// 需要隐藏的枚举类型
	private static Map<String, Set<String>> needHideNames = new HashMap<String, Set<String>>();
	static {
		// 点菜宝类型
		Set<String> set = new HashSet<String>();
		set.add("PX");
		needHideNames.put("BaseStationType", set);

		// 打印机类型
		set = new HashSet<String>();
		set.add("OPOS_USB");
		needHideNames.put("PrinterType", set);

		// 组织机构性质
		set = new HashSet<String>();
		set.add("ZBJG");
		needHideNames.put("ZuZhiXingZhi", set);

		// 支付类型
		set = new HashSet<String>();
		set.add("XMSZF");
		set.add("MSCYL");
		set.add("MSCZFB");
		set.add("MSCWXZF");
		needHideNames.put("ZFFSType", set);
	}

}
