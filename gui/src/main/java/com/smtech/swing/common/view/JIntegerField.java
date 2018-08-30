package com.smtech.swing.common.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class JIntegerField extends JTextField implements ActionListener,
		FocusListener, Serializable  {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public JIntegerField() {
		this(true);
	}

	public JIntegerField(boolean addAction) {
		this(16, 0, addAction);
	}

	public JIntegerField(int intPartLen) {
		this(intPartLen, true);
	}

	public JIntegerField(int intPartLen, boolean addAction) {
		this(intPartLen, 0, addAction);
	}

	public JIntegerField(int maxLen, int decLen) {
		this(maxLen, decLen, true);
	}

	public JIntegerField(int maxLen, int decLen, boolean addAction) {
		setPreferredSize(new Dimension(150, 25));
		setDocument(new NumberDocument(maxLen, decLen));
		super.setHorizontalAlignment(JTextField.LEFT);
		if (addAction)
			addActionListener(this);
		addFocusListener(this);
	}

	public JIntegerField(int maxLen, int decLen, double minRange,
						 double maxRange, boolean addAction) {
		setPreferredSize(new Dimension(150, 25));
		setDocument(new NumberDocument(maxLen, decLen, minRange, maxRange));
		super.setHorizontalAlignment(JTextField.LEFT);
		if (addAction)
			addActionListener(this);
		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		transferFocus();
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
		 *            int 小数位长度
		 * @param maxLen
		 *            int 最大长度(含小数位)
		 * @param minRange
		 *            double 最小值
		 * @param maxRange
		 *            double 最大值
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
			logger.trace("enter");
			String str = getText(0, getLength());
			boolean negative = false;
			if ((minRange >= 0) && s.equals("-")) {
				negative = true;
			}
			if (
				// 不能为f,F,d,D
					s.equals("F")
							|| s.equals("f")
							|| negative
							|| s.equals("D")
							|| s.equals("d")
							// 第一位是0时,第二位只能为小数点
							|| (str.trim().equals("0")
							&& !s.substring(0, 1).equals(".") && offset != 0)
							// 整数模式不能输入小数点
							|| (s.equals(".") && decLength == 0)) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			String strIntPart = "";
			String strDecPart = "";
			String strNew = str.substring(0, offset) + s
					+ str.substring(offset, getLength());
			if (!strNew.equals("")) {

			}
			// strNew = strNew.replaceFirst("-", ""); // 控制能输入负数
			int decPos = strNew.indexOf(".");
			if (decPos != -1 && strNew.length() > decPos) {
				strNew = strNew.substring(0,decPos);
				remove(0, getLength());
				//setText(strNew);
				//selectAll();
				super.insertString(offset, strNew, a);
				select(0, getLength());
				return;
			}
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

				if (!strNew.equals("") && !strNew.equals("-")) {// 控制能输入负数
					double d = Double.parseDouble(strNew);

					if (d < minRange || d > maxRange) {
						throw new Exception();
					}
					if (strNew.length() > 3) {
						boolean b = (strNew.length() > 1
								&& strNew.substring(1, 2).equals("0")
								&& strNew.substring(0, 1).equals("-") && strNew
								.substring(2, 3).equals("0"));
						if (b)
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
