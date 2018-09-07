package com.smtech.swing.common.view;

import javax.swing.JComponent;
import javax.swing.JTextArea;


/**
 */
public class InputTextWithLabel extends InputWithLabel {


	@Override
	public void setEnabled(Boolean enabled) {
		textArea.setEditable(enabled);
	}

	@Override
	public JComponent createComponent() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		// textArea.setDocument(new PlainDocument() {
		// public void insertString(int offset, String s, AttributeSet a)
		// throws BadLocationException {
		// logger.error(s);
		// s = s.replace("|", "");
		// String str = getText(0, getLength());
		// String strNew = str.substring(0, offset) + s
		// + str.substring(offset, getLength());
		// Pattern pt = Pattern.compile("^.{0,20}$"); //
		// Matcher m = pt.matcher(strNew);
		// if (m.find()) {
		// super.insertString(offset, s, a);
		// }
		// }
		// });
		// textArea.addKeyListener(new KeyListener() {
		// @Override
		// public void keyTyped(KeyEvent e) {
		// char ch = e.getKeyChar();
		// if (ch == '\"') {
		// e.setKeyChar('\0');
		// }
		// }
		//
		// @Override
		// public void keyReleased(KeyEvent arg0) {
		// }
		//
		// @Override
		// public void keyPressed(KeyEvent arg0) {
		// }
		// });
		return textArea;
	}

	@Override
	public void requestFocus() {
		textArea.requestFocus();
		textArea.selectAll();
	}

	@Override
	public String getValue() {
		return textArea.getText().trim();
	}

	@Override
	public void setValue(Object value) {
		textArea.setText((String) value);
	}

	@Override
	public void cleanData() {
		textArea.setText("");
	}

	@Override
	public JComponent getComponent() {
		return textArea;
	}

	private JTextArea textArea;
}
