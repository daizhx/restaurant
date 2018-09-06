package com.smtech.restaurant.setting.paraforinput;

import com.smtech.swing.common.view.TextFieldEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//带标签的输入项
public class InputWithLabel<T> extends JPanel{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    T t;
    Class<T> cls;

    public InputWithLabel() {
        init();
    }

    //	public abstract void requestFocus();
//	public abstract JComponent getComponent();

	public JComponent createComponent(){
	    //获取T的class对象
        if(this instanceof ParameterizedType){
            cls = (Class<T>) ((ParameterizedType) this).getActualTypeArguments()[0];
        }
//        cls = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	    if(cls == String.class){
	        return createStringInputComponent();
        }
        return createStringInputComponent();
    }

	public T getValue(){
	    return t;
    }

	public void setValue(T value){
	    this.t = value;
    }
//
//	public abstract void cleanData();


    public JComponent createStringInputComponent() {
        TextFieldEx textField = new TextFieldEx();
        setDocument(textField);
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

//        String fontName = Res.FONT;
//        Font font = textField.getFont();
//        font = new Font(fontName, Font.PLAIN, font.getSize());
//        textField.setFont(font);
        return textField;
    }

    private void setDocument(JTextField textField) {
        Set<String> spcName = new HashSet<String>();
        spcName.add("Link");
        spcName.add("LinkWeb");
        spcName.add("Url");
        spcName.add("Txt");
//        if (spcName.contains(attr.getName())) {
//            return;
//        }
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

	public void init() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(layout);
		JLabel l = createLabel();
		l.setPreferredSize(fixSizeForLable);
		l.setMaximumSize(fixSizeForLable);
		l.setMinimumSize(fixSizeForLable);
		l.setHorizontalAlignment(SwingConstants.RIGHT);
		// 如果是必填参数，把字符设置成红色
		JComponent component = createComponent();
		component.setPreferredSize(fixSizeForComponent);
		component.setMaximumSize(fixSizeForComponent);
		component.setMinimumSize(fixSizeForComponent);
        add(l);
        add(Box.createHorizontalStrut(10));
        add(component);
	}

	public JLabel createLabel() {
		label = new JLabel();
        label.setText("标题");
		label.setForeground(Color.BLACK);
		return label;
	}

	public void setLabel(String s){
	    label.setText(s);
    }

	public JLabel getLabel() {
		return label;
	}

//	public abstract void setEnabled(Boolean enabled);

	protected JLabel label;

	public static final Dimension fixSizeForLable = new Dimension(100, 20);
	public static final Dimension fixSizeForComponent = new Dimension(100, 20);

}
