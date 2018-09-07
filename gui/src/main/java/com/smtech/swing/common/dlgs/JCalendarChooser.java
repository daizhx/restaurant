package com.smtech.swing.common.dlgs;

import com.smtech.swing.common.layout.GridBagLayoutEx;
import com.smtech.swing.common.util.PanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 2010-7-9 下午09:42:10
 * <p>
 * 日期选择器<br>
 * 使用方法: 1.创建JCalendarChooser对象;<br>
 * 2.调用showCalendarDialog()方法获取选择的Calendar对象
 *
 * @author mkk(monkeyking1987@126.com)
 * @version 1.0
 * @see JFileChooser
 */
public class JCalendarChooser extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static Frame parent = null;

	private JSpinner spiTime;
	private boolean isShowTime = false;


	/**
	 * 构造器<br>
	 * 在创建对象后调用showCalendarDialog()方法获取选择的日期值
	 */
	public JCalendarChooser(Calendar calendar) {
		super(parent, true);
		this.setTitle(title);
		DEFAULT_WIDTH = (int) (DEFAULT_WIDTH * 1.2);
		DEFAULT_HEIGHT = (int) (DEFAULT_HEIGHT * 1.2);
		this.initDatas(calendar);
	}

	/**
	 * 构造器<br>
	 * 在创建对象后调用showCalendarDialog()方法获取选择的日期值
	 *
	 */
	public JCalendarChooser(Calendar calendar, Font font) {
		super(parent, true);
		this.innerFont = font;
		DEFAULT_WIDTH = (int) (DEFAULT_WIDTH * 1.8);
		DEFAULT_HEIGHT = (int) (DEFAULT_HEIGHT * 1.8);

		setUndecorated(true);
		this.setTitle(title);
		this.initDatas(calendar);
	}

	/**
	 *
	 * @param width
	 *            长度
	 * @param height
	 *            宽度
	 * @return
	 */
	private Dimension getStartDimension(int width, int height) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.width = dim.width / 2 - width / 2;
		dim.height = dim.height / 2 - height / 2;
		return dim;
	}

	/**
	 * 初始化数据
	 */
	private void initDatas(Calendar calendar) {
		this.chooser = this;
		this.mdcEvent = new MouseDoubleClickedEvent();
		this.calendar = calendar;
		this.year1 = this.calendar.get(Calendar.YEAR);
		this.month1 = this.calendar.get(Calendar.MONTH);
		this.day1 = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.years = new String[showYears];
		this.months = new String[12];
		// init label1
		label1 = new JLabel();
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setForeground(Color.MAGENTA);
		SimpleDateFormat sdf = new SimpleDateFormat("JCalendarChooser.TimeLine");
		label1 = new JLabel("JCalendarChooser.today"
				+ sdf.format(new Date()));
		label1.setToolTipText("JCalendarChooser.backtoday");
		label1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				label1.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent me) {
				label1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				label1.setForeground(Color.BLACK);
			}

			public void mousePressed(MouseEvent me) {
				label1.setForeground(Color.WHITE);
				chooser.calendar.setTime(new Date());
				chooser.dispose();
			}

			public void mouseReleased(MouseEvent me) {
				label1.setForeground(Color.BLACK);
			}
		});

		// init months
		for (int i = 0; i < this.months.length; i++) {
			this.months[i] = " " + formatDay(i + 1);
		}
		// init years
		int start = this.year1 - this.showYears / 2;
		for (int i = start; i < start + showYears; i++) {
			this.years[i - start] = String.valueOf(i);
		}
	}

	/**
	 * 显示Dialog的方法
	 */
	private void showDialog() {
		// 北面面板
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		showNorthPanel(panel3);
		// 中间面板
		panelCenter = new JPanel(new BorderLayout());
		panelCenter.add(printCalendar(), BorderLayout.CENTER);
		// 南边面板
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.showSouthPanel(panel2);

		GridBagLayoutEx gx = new GridBagLayoutEx(9, 1, new Insets(0, 0, 5, 0));
		gx.addComponent(panel3, 0, 0, 1, 1);
		gx.addComponent(panelCenter, 0, 1, 1, 7);
		gx.addComponent(panel2, 0, 8, 1, 1);
		gx.doLayout((JPanel) getContentPane());

		// 设置大小
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 设置显示的位置
		if (this.location == null) {
			Dimension dim = getStartDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			setLocation(dim.width, dim.height);
		} else {
			setLocation(this.location);
		}
		setVisible(true);
	}

	/**
	 * 固定某控件的大小
	 *
	 * @param component
	 * @param size
	 */
	public void fixSize(JComponent component, Dimension size) {
		if (innerFont == null) {
			return;
		}
		component.setPreferredSize(size);
		component.setMaximumSize(size);
		component.setMinimumSize(size);
		component.setSize(size);
	}

	private void setInnerFont(JComponent c) {
		if (innerFont == null) {
			return;
		}
		c.setFont(innerFont);
	}

	/**
	 * 显示北面面板
	 *
	 * @param panel
	 */
	private void showNorthPanel(JPanel panel) {
		PanelBuilder pb = new PanelBuilder();
		if (innerFont == null) {
			pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0,
					1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(3, 3, 3, 3), 0, 0));
		} else {
			pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0,
					1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}

		pb.addHorizontalGlue();

		Dimension dim = new Dimension(100, 0);

		this.button2 = new JButton("JCalendarChooser.lastmonth");
		this.button2.setToolTipText("JCalendarChooser.lastmonth");
		this.button2.addActionListener(this);
		fixSize(button2, dim);
		setInnerFont(button2);
		pb.add(this.button2);

		this.comboBox1 = new JComboBox(this.years);
		this.comboBox1.setSelectedItem(String.valueOf(year1));
		this.comboBox1.setToolTipText("JCalendarChooser.year");
		this.comboBox1.setActionCommand("year");
		this.comboBox1.addActionListener(this);
		setInnerFont(comboBox1);
		fixSize(comboBox1, dim);
		pb.add(this.comboBox1);

		this.comboBox2 = new JComboBox(this.months);
		this.comboBox2.setSelectedItem(" " + formatDay(month1 + 1));
		this.comboBox2.setToolTipText("JCalendarChooser.month");
		this.comboBox2.setActionCommand("month");
		this.comboBox2.addActionListener(this);
		fixSize(comboBox2, dim);
		setInnerFont(comboBox2);
		pb.add(this.comboBox2);

		this.button3 = new JButton("JCalendarChooser.nextmonth");
		this.button3.setToolTipText("JCalendarChooser.nextmonth");
		this.button3.addActionListener(this);
		fixSize(button3, dim);
		setInnerFont(button3);
		pb.add(this.button3);

		pb.addHorizontalGlue();
		pb.doLayout(panel);
	}

	/**
	 * 显示南边面板信息
	 *
	 * @param panel
	 */
	private void showSouthPanel(JPanel panel) {

		this.button1 = new JButton("JCalendarChooser.Commit");
		this.button1.setToolTipText("JCalendarChooser.Commit");
		this.button1.addActionListener(this);
		fixSize(button1, new Dimension(100, 0));
		spiTime = crtSpi(calendar.getTime());
		fixSize(spiTime, new Dimension(400, 0));
		JPanel paneWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		paneWrap.add(new JLabel("JCalendarChooser.Time"));
		paneWrap.add(spiTime, BorderLayout.WEST);
		setInnerFont(this.button1);
		setInnerFont(this.label1);
		setInnerFont(spiTime);
		panel.setLayout(new BorderLayout(5, 5));
		panel.add(label1, BorderLayout.WEST);
		panel.add(paneWrap, BorderLayout.CENTER);
		panel.add(button1, BorderLayout.EAST);

		paneWrap.setVisible(isShowTime);
	}

	private JSpinner crtSpi(Date date) {
		JSpinner timeSpin = new JSpinner(new SpinnerDateModel());
		timeSpin.setName("time");
		timeSpin.setEditor(new JSpinner.DateEditor(timeSpin, "HH:mm:ss"));
		timeSpin.setValue(date);
		return timeSpin;
	}

	/**
	 * 在构造对象后调用此方法获取选择的日历值
	 *
	 * @return 选择日历对象Calendar
	 */
	public Calendar showCalendarDialog() {
		this.showDialog();
		if (spiTime.isVisible()) {
			Date time = (Date) spiTime.getValue();
			calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
			calendar.set(Calendar.MINUTE, time.getMinutes());
			calendar.set(Calendar.SECOND, time.getSeconds());
		}
		return this.calendar;
	}

	/**
	 * 设置显示的数字,若小于10则在前面加0
	 *
	 * @param day
	 * @return
	 */
	private String formatDay(int day) {
		if (day < 10) {
			return "0" + day;
		}
		return String.valueOf(day);
	}

	/**
	 * 输出日期的面板
	 *
	 * @return
	 */
	private JPanel printCalendar() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		// 将日期设为当月第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// 获取第一天是星期几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 打印标头
		JButton b = null;
		for (int i = 0; i < tits.length; i++) {
			b = new JButton("<html><b>" + tits[i] + "</b></html>");
			b.setForeground(Color.BLACK);
			b.setBackground(Color.WHITE);
			b.setBorder(BorderFactory.createEmptyBorder());
			b.setEnabled(false);
			setInnerFont(b);
			panel.add(b);
		}
		int count = 0;
		for (int i = Calendar.SUNDAY; i < weekDay; i++) {
			b = new JButton("");
			b.setEnabled(false);
			setInnerFont(b);
			panel.add(b);
			count++;
		}
		int currday = 0;
		String dayStr = null;
		do {
			currday = calendar.get(Calendar.DAY_OF_MONTH);
			dayStr = formatDay(currday);
			// 日,月,年相等则显示
			b = new JButton(dayStr);
			if (currday == day1 && month1 == month2 && year1 == year2) {
				b.setForeground(Color.RED);
			} else {
				b.setForeground(Color.BLACK);
			}
			count++;
			b.setToolTipText(year2 + "-" + formatDay(month2 + 1) + "-" + dayStr);
			b.setBorder(BorderFactory.createEtchedBorder());
			b.addActionListener(this);
			b.addMouseListener(this.mdcEvent);
			setInnerFont(b);
			panel.add(b);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			// 循环完成时月份实际上已经加1
		} while (calendar.get(Calendar.MONTH) == month2);
		// 减1,保持为当前月
		this.calendar.add(Calendar.MONTH, -1);
		if (!flag) {
			// 设置日期为当天
			this.calendar.set(Calendar.DAY_OF_MONTH, this.day1);
			flag = true;
		}
		for (int i = count; i < 42; i++) {
			b = new JButton("");
			b.setEnabled(false);
			setInnerFont(b);
			panel.add(b);
		}
		return panel;
	}

	/**
	 * 内部类,用于监听日期按钮的双击事件 2010-7-10 下午11:34:26
	 */
	private class MouseDoubleClickedEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (MouseEvent.BUTTON1 == e.getButton()) {
				// 鼠标左键双击事件
				JButton b = (JButton) e.getSource();
				String s = b.getText();
				if (s.matches("^\\d+$")) {
					int day = Integer.parseInt(s);
					calendar.set(Calendar.DAY_OF_MONTH, day);
				} else if (s.startsWith("[")) {
					calendar.set(Calendar.DAY_OF_MONTH, day1);
				}
				// dispose
				chooser.dispose();
			}
		}

	}

	/**
	 * 更新界面
	 */
	private void updatePanel() {
		panelCenter.removeAll();
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(printCalendar(), BorderLayout.CENTER);
		this.validate();
	}

	/**
	 * 响应点击事件
	 *
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("JCalendarChooser.nextmonth".equals(command)) {
			// 1.月份加一
			this.calendar.add(Calendar.MONTH, 1);
			// 2.更新显示的年与月
			int year5 = calendar.get(Calendar.YEAR);
			// 判断是否超出显示的最大范围
			int maxYear = this.year1 + this.showYears / 2 - 1;
			if (year5 > maxYear) {
				this.calendar.add(Calendar.MONTH, -1);
				return;
			}
			int month5 = calendar.get(Calendar.MONTH) + 1;
			this.comboBox1.setSelectedItem(String.valueOf(year5));
			this.comboBox2.setSelectedItem(" " + this.formatDay(month5));
			// 3.更新界面
			this.updatePanel();
		} else if ("JCalendarChooser.lastmonth".equals(command)) {
			// 1.月份减一
			this.calendar.add(Calendar.MONTH, -1);
			// 2.更新显示的年与月
			int year5 = calendar.get(Calendar.YEAR);
			// 判断是否超出显示的最大范围
			int minYear = this.year1 - this.showYears / 2;
			if (year5 < minYear) {
				this.calendar.add(Calendar.MONTH, 1);
				return;
			}
			int month5 = calendar.get(Calendar.MONTH) + 1;
			this.comboBox1.setSelectedItem(String.valueOf(year5));
			this.comboBox2.setSelectedItem(" " + this.formatDay(month5));
			// 3.更新界面
			this.updatePanel();
		} else if ("JCalendarChooser.Commit".equals(command)) {
			chooser.dispose();
		} else if (command.matches("^\\d+$")) {
			// 选择一个具体的日期的事件
			int day9 = Integer.parseInt(command);
			this.calendar.set(Calendar.DAY_OF_MONTH, day9);
			String str = this.calendar.get(Calendar.YEAR) + "-"
					+ this.formatDay(this.calendar.get(Calendar.MONTH) + 1)
					+ "-"
					+ this.formatDay(this.calendar.get(Calendar.DAY_OF_MONTH));
			// TODO
		} else if (command.startsWith("[")) {
			// 处理为当前日期的情况
			this.calendar.set(Calendar.DAY_OF_MONTH, this.day1);
			String str = this.calendar.get(Calendar.YEAR) + "-"
					+ this.formatDay(this.calendar.get(Calendar.MONTH) + 1)
					+ "-"
					+ this.formatDay(this.calendar.get(Calendar.DAY_OF_MONTH));
		} else if ("year".equalsIgnoreCase(command)) {
			// 选择年事件
			int value = Integer.parseInt(this.comboBox1.getSelectedItem()
					.toString().trim());
			this.calendar.set(Calendar.YEAR, value);
			this.updatePanel();
		} else if ("month".equalsIgnoreCase(command)) {
			// 选择月事件
			int value = Integer.parseInt(this.comboBox2.getSelectedItem()
					.toString().trim());
			this.calendar.set(Calendar.MONTH, value - 1);
			this.updatePanel();
		}
	}

	public void setShowTime(boolean isShowTime) {
		this.isShowTime = isShowTime;
	}

	public boolean isShowTime() {
		return isShowTime;
	}

	// 默认宽度与高度
	private int DEFAULT_WIDTH = 285;
	private int DEFAULT_HEIGHT = 280;
	// 默认显示的年份为100年,即当年的前后50年
	private int showYears = 250;
	// 状态栏与确认按钮
	// 状态栏最多放置17个汉字
	private JLabel label1 = null;
	private JButton button1 = null;
	// 上一个月,下一个月按钮
	private JButton button2 = null;
	private JButton button3 = null;
	// 选择年与月的下拉框
	private JComboBox comboBox1 = null;
	private JComboBox comboBox2 = null;
	// 日历对象
	private Calendar calendar = null;
	// 年与月的选择集合对象
	private String[] years = null;
	private String[] months = null;
	// 当前年,月,日
	private int year1, month1, day1;
	private JPanel panelCenter = null;
	private String tits[] = { "Sun", "Mon",
			"Tues", "Wed", "Thur",
			"Fri", "Sat"};
	private String title = "JCalendarChooser.SelectDate";
	private Point location = null;
	private MouseDoubleClickedEvent mdcEvent = null;
	private JCalendarChooser chooser = null;
	// 标识字段
	private boolean flag;

	private Font innerFont;

}