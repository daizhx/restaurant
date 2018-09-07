package com.smtech.swing.common.view;

import com.smtech.swing.common.util.CommonFunc;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDoubleWithLabel extends InputWithLabel {

	@Override
	public void setEnabled(Boolean enabled) {
		textField.setEditable(enabled);
	}

	@Override
	public JComponent createComponent() {
		Pattern pt = Pattern.compile("Double\\((\\d+)\\-(\\d+)\\)");
		Matcher m = pt.matcher("0-10000");
		Double minValue = null;
		Double maxValue = null;
		if (m.find()) {
			minValue = Double.valueOf(m.group(1));
			maxValue = Double.valueOf(m.group(2));
		} else {
			maxValue = Double.MAX_VALUE;
			minValue = -Double.MAX_VALUE;
		}
		textField = new JNumberField(1000, 100, minValue, maxValue, true);

		return textField;
	}

	@Override
	public void requestFocus() {
		textField.requestFocus();
		textField.selectAll();
	}

	@Override
	public Object getValue() {
		Double value = null;
		try {
			value = Double.valueOf(textField.getText());
		} catch (Exception e) {
			return null;
		}
		return BigDecimal.valueOf(value);
	}

	@Override
	public void setValue(Object value) {
		if (value == null || !(value instanceof BigDecimal)) {
			textField.setText("");
		} else {
			textField.setText(CommonFunc.formateWithDecimal((BigDecimal) value));
			getValue();
		}
	}

	private JTextField textField;

	@Override
	public void cleanData() {
		textField.setText("");
	}

	@Override
	public JComponent getComponent() {
		return textField;
	}
}
