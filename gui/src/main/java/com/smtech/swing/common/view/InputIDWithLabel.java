package com.smtech.swing.common.view;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputIDWithLabel extends InputWithLabel {

	@Override
	public void cleanData() {
		tf.setText("");
	}

	@Override
	public void requestFocus() {
		tf.requestFocus();
		tf.selectAll();
	}

	@Override
	public JComponent getComponent() {
		return tf;
	}

	public void setComponent(JTextField field) {
		tf = field;
		tf.setPreferredSize(fixSizeForComponent);
		tf.setMaximumSize(fixSizeForComponent);
		tf.setMinimumSize(fixSizeForComponent);
	}

	@Override
	public JComponent createComponent() {
		if (getComponent() != null) {
			return tf;
		}
		tf = new TextFieldEx();
		tf.setDocument(new PlainDocument() {
			public void insertString(int offset, String s, AttributeSet a)
					throws BadLocationException {
				String str = getText(0, getLength());
				String strNew = str.substring(0, offset) + s
						+ str.substring(offset, getLength());
				Pattern pt = Pattern.compile("^\\w*$");
				Matcher m = pt.matcher(strNew);
				if (m.find()) {
					super.insertString(offset, s, a);
				}
			}
		});
		return tf;
	}

	@Override
	public Object getValue() {
		return tf.getText().toUpperCase();
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof String) {
			String str = (String) value;
			value = str.trim();
		}
		tf.setText((String) value);
	}

	@Override
	public void setEnabled(Boolean enabled) {
		tf.setEditable(enabled);
	}

	private JTextField tf;
}
