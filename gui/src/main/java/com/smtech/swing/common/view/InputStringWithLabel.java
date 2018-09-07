package com.smtech.swing.common.view;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputStringWithLabel extends InputWithLabel {

	@Override
	public void setEnabled(Boolean enabled) {
		textField.setEditable(enabled);
	}

	private void setDocument() {
		Set<String> spcName = new HashSet<String>();
		spcName.add("Link");
		spcName.add("LinkWeb");
		spcName.add("Url");
		spcName.add("Txt");
//		if (spcName.contains(attr.getName())) {
//			return;
//		}
		textField.setDocument(new PlainDocument() {
			public void insertString(int offset, String s, AttributeSet a)
					throws BadLocationException {
				s = s.replace("|", "");
				String str = getText(0, getLength());
				String strNew = str.substring(0, offset) + s
						+ str.substring(offset, getLength());
				Pattern pt = Pattern.compile("^.{0,100}$"); //
				Matcher m = pt.matcher(strNew);
				if (m.find()) {
					super.insertString(offset, s, a);
				}
			}
		});
	}

	@Override
	public JComponent createComponent() {
		textField = new TextFieldEx();
		setDocument();
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

		Font font = textField.getFont();
//		font = new Font(fontName, Font.PLAIN, font.getSize());
		textField.setFont(font);
		return textField;
	}

	@Override
	public void requestFocus() {
		textField.requestFocus();
		textField.selectAll();
	}

	@Override
	public String getValue() {
		return textField.getText().trim();
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
