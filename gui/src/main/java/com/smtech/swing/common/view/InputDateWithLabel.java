package com.smtech.swing.common.view;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Date;


public class InputDateWithLabel extends InputWithLabel {

	@Override
	public void setEnabled(Boolean enabled) {
		textField.setEnabled(enabled);
	}

	@Override
	public JComponent createComponent() {
		textField = new DateChooser();
		return textField;
	}

	@Override
	public void requestFocus() {
		textField.requestFocus();
	}

	@Override
	public Object getValue() {
		return new Timestamp(textField.getDate().getTime());
	}

	@Override
	public void setValue(Object value) {
		if (value != null && value instanceof Date) {
			textField.setDate((Date) value);
		}
	}

	private DateChooser textField = new DateChooser();

	@Override
	public void cleanData() {
		textField.setText("");
	}

	@Override
	public JComponent getComponent() {
		return textField;
	}

}