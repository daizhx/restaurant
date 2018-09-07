package com.smtech.swing.common.view;

import javax.swing.JComponent;


public class InputIpWithLabel extends InputWithLabel {

	@Override
	public void setEnabled(Boolean enabled) {
		tf.setEnabled(enabled);
	}

	@Override
	public JComponent createComponent() {
		tf = new JIpAddressField();
		return tf;
	}

	@Override
	public void requestFocus() {
		tf.requestFocus();
	}

	@Override
	public String getValue() {
		return tf.getIpAddress();
	}

	@Override
	public void setValue(Object value) {
		String ip = (String) value;
		if (ip == null || ip.equals("")) {
			ip = "0.0.0.0";
		}
		tf.setIpAddress(ip);
	}

	@Override
	public void cleanData() {
		tf.setIpAddress("0.0.0.0");
	}

	@Override
	public JComponent getComponent() {
		return tf;
	}

	private JIpAddressField tf;
}
