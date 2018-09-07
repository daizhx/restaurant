package com.smtech.swing.common.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class InputPwdWithLabel extends InputWithLabel {


	@Override
	public void setEnabled(Boolean enabled) {
		textField.setEditable(enabled);
	}

	@Override
	public JComponent createComponent() {
		textField = new JPasswordField();
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch == '\"') {
					e.setKeyChar('\0');
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		return textField;
	}

	@Override
	public void requestFocus() {
		textField.requestFocus();
		textField.selectAll();
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

	@Override
	public void setValue(Object value) {
		textField.setText((String) value);
	}

	@Override
	public void cleanData() {
		textField.setText("");
	}

	@Override
	public JComponent getComponent() {
		return textField;
	}

	private JTextField textField;
}
