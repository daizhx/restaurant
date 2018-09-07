package com.smtech.swing.common.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputTimeWithLabel extends InputWithLabel {

	@Override
	public void cleanData() {
		tf.setText("");
	}

	@Override
	public void requestFocus() {
		tf.requestFocusInWindow();
	}

	@Override
	public JComponent getComponent() {
		return tf;
	}

	@Override
	public JComponent createComponent() {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##:##:##");
			formatter.setValueContainsLiteralCharacters(false);
			formatter.setPlaceholderCharacter('0');
		} catch (ParseException e) {
//			logger.error(StackTraceToString.getExceptionTrace(e));
		}

		tf = new JFormattedTextField(formatter);
		return tf;
	}

	@Override
	public Object getValue() {
		Pattern pt = Pattern.compile("(\\d+)\\:(\\d+):(\\d+)");
		Matcher m = pt.matcher(tf.getText());
		if (m.find()) {
			Integer hour = Integer.valueOf(m.group(1));
			Integer minute = Integer.valueOf(m.group(2));
			Integer sec = Integer.valueOf(m.group(3));

			Calendar c = Calendar.getInstance();
			if (this.value != null) {
				c.setTime(value);
			}
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, minute);
			c.set(Calendar.SECOND, sec);
			c.set(Calendar.MILLISECOND, 0);
			return new Timestamp(c.getTimeInMillis());
		} else {
			return null;
		}
	}

	@Override
	public void setValue(Object value) {
		if (value == null || !(value instanceof Timestamp)) {
			tf.setText("");
			return;
		}
		this.value = (Timestamp) value;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		tf.setText(sdf.format(this.value));
	}

	@Override
	public void setEnabled(Boolean enabled) {
		tf.setEnabled(enabled);
//		if (attr.getName().equals("KuaiPiaoShiJian")) {
//			return;
//		}
	}

	private JTextField tf;
	private Timestamp value;

}
