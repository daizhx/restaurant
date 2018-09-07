package com.smtech.swing.common.view;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.dlgs.JCalendarChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author hadeslee
 */
public class DateChooser extends JTextField {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JDateDocument doc = new JDateDocument(this, sdf);
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void setDatePattern(String pattern) {
		sdf.applyPattern(pattern);
	}

	/**
	 * Creates a new instance of DateChooser
	 */
	public DateChooser() {

		this(new Date());
	}

	public DateChooser(Date date) {
		setDocument(doc);
		super.setHorizontalAlignment(JTextField.CENTER);
		initTextField();
	}

	public void setDate(Date date) {
		setText(sdf.format(date));
	}

	/**
	 * 得到当前选择框的日期
	 */
	public Date getDate() {
		try {
			return sdf.parse(getText());
		} catch (ParseException e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
			return new Date();
		}
	}

	// 初始化标签
	private void initTextField() {
		DateChooser.this.setText((sdf.format(new Date())));
		DateChooser.this.setEditable(true);
		DateChooser.this.setBackground(Color.WHITE);
		DateChooser.this.setPreferredSize(new Dimension(100, 20));
		DateChooser.this.setRequestFocusEnabled(true);
		DateChooser.this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				DateChooser.this.requestFocusInWindow();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				if (DateChooser.this.isEnabled()) {
					DateChooser.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					DateChooser.this.setForeground(Color.RED);
				}
			}

			public void mouseExited(MouseEvent me) {
				if (DateChooser.this.isEnabled()) {
					DateChooser.this
							.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					DateChooser.this.setForeground(Color.BLACK);
				}
			}

			public void mousePressed(MouseEvent me) {
				if (!DateChooser.this.isEnabled()) {
					return;
				}
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdf.parse(getText()));
					JCalendarChooser jcc = new JCalendarChooser(calendar);
					Calendar c = jcc.showCalendarDialog();
					setDate(c.getTime());
				} catch (Exception e) {
					logger.error(StackTraceToString.getExceptionTrace(e));
				}
			}

			public void mouseReleased(MouseEvent me) {
				if (DateChooser.this.isEnabled()) {
					DateChooser.this.setForeground(Color.BLACK);
				}
			}
		});
	}
}
