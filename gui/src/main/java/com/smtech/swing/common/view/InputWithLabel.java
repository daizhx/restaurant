package com.smtech.swing.common.view;

import javax.swing.*;
import java.awt.*;

//带标签的输入项
public abstract class InputWithLabel extends JPanel{
//    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private JLabel label;
    public static final Dimension fixSizeForLable = new Dimension(100, 20);
    public static final Dimension fixSizeForComponent = new Dimension(100, 20);

    public InputWithLabel() {
        init();
    }

    public abstract Object getValue();

    public abstract void setValue(Object value);

    public abstract void requestFocus();
	public abstract JComponent getComponent();

    public abstract JComponent createComponent();

	public abstract void cleanData();

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
		return label;
	}

	public void setLabel(String l){
	    label.setText(l);
    }

	public abstract void setEnabled(Boolean enabled);


}
