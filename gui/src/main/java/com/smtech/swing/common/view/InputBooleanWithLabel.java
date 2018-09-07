package com.smtech.swing.common.view;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class InputBooleanWithLabel extends InputWithLabel {



	@Override
	public void setEnabled(Boolean enabled) {
		checkBox.setEnabled(enabled);
	}

	@Override
	public JComponent createComponent() {
		checkBox = new JCheckBox();
		return checkBox;
	}

	@Override
	public void requestFocus() {
		checkBox.requestFocus();
	}

	@Override
	public Object getValue() {
		return checkBox.isSelected();
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			checkBox.setSelected((Boolean) value);
		}
	}

	private JCheckBox checkBox;

	@Override
	public void cleanData() {
		checkBox.setSelected(false);
	}

	@Override
	public JComponent getComponent() {
		return checkBox;
	}

}
