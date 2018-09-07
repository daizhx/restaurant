package com.smtech.swing.common.view;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputIntWithLabel extends InputWithLabel {


	private JTextField textField;

	@Override
	public void setEnabled(Boolean enabled) {
		textField.setEditable(enabled);
	}

	protected Integer minValue;
	protected Integer maxValue;

	@Override
	public JComponent createComponent() {
		Pattern pt = Pattern.compile("Int\\((\\d+)\\-(\\d+)\\)");
		Matcher m = pt.matcher("1-10000");
		if (m.find()) {
			minValue = Integer.valueOf(m.group(1));
			maxValue = Integer.valueOf(m.group(2));
		} else {
			maxValue = Integer.MAX_VALUE;
			minValue = Integer.MIN_VALUE;
		}

		textField = new JNumberField(666, 0, minValue, maxValue, false);

		return textField;
	}

	@Override
	public void requestFocus() {
		textField.requestFocus();
		textField.selectAll();
	}

	class JNumberField extends JTextField implements ActionListener,
			FocusListener, Serializable {
		public JNumberField() {
			this(true);
		}

		public JNumberField(boolean addAction) {
			this(16, 0, addAction);
		}

		public JNumberField(int intPartLen) {
			this(intPartLen, true);
		}

		public JNumberField(int intPartLen, boolean addAction) {
			this(intPartLen, 0, addAction);
		}

		public JNumberField(int maxLen, int decLen) {
			this(maxLen, decLen, true);
		}

		public JNumberField(int maxLen, int decLen, boolean addAction) {
			setPreferredSize(new Dimension(150, 25));
			setDocument(new NumberDocument(maxLen, decLen));
			super.setHorizontalAlignment(JTextField.LEFT);
			if (addAction)
				addActionListener(this);
			addFocusListener(this);
		}

		public JNumberField(int maxLen, int decLen, double minRange,
				double maxRange, boolean addAction) {
			setPreferredSize(new Dimension(150, 25));
			setDocument(new NumberDocument(maxLen, decLen, minRange, maxRange));
			super.setHorizontalAlignment(JTextField.LEFT);
			if (addAction)
				addActionListener(this);
			addFocusListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			transferFocus();
		}

		public void focusGained(FocusEvent e) {
			selectAll();
		}

		public void focusLost(FocusEvent e) {
		}

		class NumberDocument extends PlainDocument {
			int maxLength = 16;
			int decLength = 0;
			double minRange = -Double.MAX_VALUE;
			double maxRange = Double.MAX_VALUE;

			public NumberDocument(int maxLen, int decLen) {
				maxLength = maxLen;
				decLength = decLen;
			}

			/**
			 * @param decLen
			 *            int С��λ����
			 * @param maxLen
			 *            int ��󳤶�(��С��λ)
			 * @param minRange
			 *            double ��Сֵ
			 * @param maxRange
			 *            double ���ֵ
			 */
			public NumberDocument(int maxLen, int decLen, double minRange,
					double maxRange) {
				this(maxLen, decLen);
				this.minRange = minRange;
				this.maxRange = maxRange;
			}

			public NumberDocument(int decLen) {
				decLength = decLen;
			}

			public NumberDocument() {
			}

			public void insertString(int offset, String s, AttributeSet a)
					throws BadLocationException {
				String str = getText(0, getLength());
				boolean negative = false;
				if ((minRange >= 0) && s.equals("-")) {
					negative = true;
				}

				if (
				// ����Ϊf,F,d,D
				s.equals("F") || s.equals("f")
						|| negative
						|| s.equals("D")
						|| s.equals("d")
						// ��һλ��0ʱ,�ڶ�λֻ��ΪС����
						|| (str.trim().equals("0")
								&& !s.substring(0, 1).equals(".") && offset != 0)
						// ����ģʽ��������С����
						|| (s.equals(".") && decLength == 0)) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				String strIntPart = "";
				String strDecPart = "";
				String strNew = str.substring(0, offset) + s
						+ str.substring(offset, getLength());
				strNew = strNew.replaceFirst("-", ""); // ���������븺��
				int decPos = strNew.indexOf(".");
				if (decPos > -1) {
					strIntPart = strNew.substring(0, decPos);
					strDecPart = strNew.substring(decPos + 1);
				} else {
					strIntPart = strNew;
				}
				if (strIntPart.length() > (maxLength - decLength)
						|| strDecPart.length() > decLength
						|| (strNew.length() > 1
								&& strNew.substring(0, 1).equals("0") && !strNew
								.substring(1, 2).equals("."))) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				try {
					if (!strNew.equals("") && !strNew.equals("-")) {// ���������븺��
						double d = Double.parseDouble(strNew);
						if (d < minRange || d > maxRange) {
							throw new Exception();
						}
					}
				} catch (Exception e) {
					Toolkit.getDefaultToolkit().beep();
					return;
				}
				super.insertString(offset, s, a);
			}

		}
	}

	@Override
	public Object getValue() {
		Integer value = null;
		try {
			value = Integer.valueOf(textField.getText());
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	@Override
	public void setValue(Object value) {
		if (value == null) {
			textField.setText("");
		} else {
			textField.setText(String.valueOf(value));
		}

	}

	@Override
	public void cleanData() {
		textField.setText("");
	}

	@Override
	public JComponent getComponent() {
		return textField;
	}
}
