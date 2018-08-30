package com.smtech.swing.common.view;


import com.smtech.swing.common.util.CommonFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 本类提供的表中，表头和表格都居中
 *
 */
public class CommonTable extends JTable {
	public CommonTable(AbstractTableModel dm) {
		super(dm);
		setColumnSelectionAllowed(false);
		getTableHeader().setReorderingAllowed(false);
		putClientProperty("terminateEditOnFocusLost", true);
		setNumberEditor(19, 2);
		setIntegerNumberEditor(16);
	}

	@Override
	public void setModel(TableModel dataModel) {
		super.setModel(dataModel);
		if (dataModel == null) {
			return;
		}
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				dataModel);
		// setRowSorter(sorter);
		Comparator<Object> intComparator = new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				if (o1 == o2) {
					return 0;
				} else if (o1 == null) {
					return -1;
				} else if (o2 == null) {
					return 1;
				} else if (o1 instanceof Date && o2 instanceof Date) { // 时间
					return ((Date) o1).compareTo((Date) o2);
				} else if (o1 instanceof Boolean && o2 instanceof Boolean) { // 真假值
					return ((Boolean) o1).compareTo((Boolean) o2);
				} else if (o1 instanceof Number && o2 instanceof Number) { // 数值
					double d = (((Number) o1).doubleValue() - ((Number) o2)
							.doubleValue());
					if (d == 0) {
						return 0;
					} else if (d > 0) {
						return 1;
					} else {
						return -1;
					}
				} else {// 字符串
					return o1.toString().compareTo(o2.toString());
				}
			}
		};
		for (int i = 0; i < dataModel.getColumnCount(); i++) {
			sorter.setComparator(i, intComparator);
		}
	}

	/**
	 * 为整形单元格设置编辑器
	 *
	 * @param len
	 */
	public void setIntegerNumberEditor(int len) {
		// 为整形单元格设置编辑器
		JTextField tfInt = new JIntegerField(len, 0, -Integer.MAX_VALUE,
				Integer.MAX_VALUE, true);
		DefaultCellEditor editorInt = new DefaultCellEditor(tfInt) {
			@Override
			public Object getCellEditorValue() {
				Object value = super.getCellEditorValue();
				if (value == null || value.equals("")) {
					return null;
				}
				if (value instanceof String) {
					return Integer.valueOf((String) value);
				}
				return value;
			}
		};
		setDefaultEditor(Integer.class, editorInt);
	}

	private int scaleNumber = 2;

	/**
	 * 为数字单元格设置编辑器
	 */
	public void setNumberEditor(int len, int scale) {
		this.scaleNumber = scale;
		// 为数字单元格设置编辑器
		JTextField tf = new JNumberField(len, scale, -Double.MAX_VALUE,
				Double.MAX_VALUE, true);
		DefaultCellEditor editor = new DefaultCellEditor(tf) {
			@Override
			public Object getCellEditorValue() {
				Object value = super.getCellEditorValue();
				if (value == null || value.equals("")) {
					return null;
				}
				if (value instanceof String) {
					return BigDecimal.valueOf(Double.valueOf((String) value));
				}
				return value;
			}
		};
		setDefaultEditor(BigDecimal.class, editor);
	}

	public CommonTable() {
		this(null);
	}

	@Override
	public JTableHeader getTableHeader() {
		JTableHeader header = super.getTableHeader();
		if (header.getDefaultRenderer() instanceof DefaultTableCellRenderer) {
			((DefaultTableCellRenderer) header.getDefaultRenderer())
					.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return header;
	}

	public static String formate(BigDecimal b) {
		if (b == null) {
			return "0";
		}

		return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString()
				.replace(".00", "");
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		TableCellRenderer superRenderer = super.getCellRenderer(row, column);
		if (!(superRenderer instanceof DefaultTableCellRenderer)) {
			return superRenderer;
		}

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) superRenderer;
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer newRenderer = null;
		if (getModel().getValueAt(row, column) instanceof BigDecimal) {
			newRenderer = new DefaultTableCellRenderer() {
				public void setValue(Object value) {
					if (value != null
							&& BigDecimal.valueOf(99999999.99).compareTo(
							(BigDecimal) value) == 1) {
						BigDecimal v = (BigDecimal) value;
						String numStr = CommonFunc.subZeroAndDot(v.setScale(
								scaleNumber, BigDecimal.ROUND_HALF_UP)
								.toString());
						super.setValue(numStr);
					} else {
						super.setValue(null);
					}
				}
			};

		}
		if (getModel().getValueAt(row, column) instanceof Timestamp
				|| getModel().getValueAt(row, column) instanceof Time) {
			newRenderer = new DefaultTableCellRenderer() {
				public void setValue(Object value) {
					SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss");
					String s = sp.format(value);
					super.setValue(s);
				}
			};
		} else if (getModel().getValueAt(row, column) instanceof Date) {
			newRenderer = new DefaultTableCellRenderer() {
				public void setValue(Object value) {
					SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
					String s = sp.format(value);
					super.setValue(s);
				}
			};
		}

		if (newRenderer != null) {
			newRenderer.setBackground(renderer.getBackground());
			newRenderer.setForeground(renderer.getForeground());
			newRenderer.setFont(renderer.getFont());
			newRenderer.setBorder(renderer.getBorder());
			newRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			return newRenderer;
		}
		return renderer;
	}

	/**
	 * 隐藏某列
	 *
	 * @param col
	 */
	public void hideCol(int col) {
		TableColumn tc = getTableHeader().getColumnModel().getColumn(col);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMaxWidth(0);
		tc.setMinWidth(0);
		getTableHeader().getColumnModel().getColumn(col).setMaxWidth(0);
		getTableHeader().getColumnModel().getColumn(col).setMinWidth(0);
	}

	/**
	 * 设置某列的宽度
	 *
	 * @param col
	 * @param width
	 */
	public void setColWidth(int col, int width) {
		TableColumn tc = getTableHeader().getColumnModel().getColumn(col);
		tc.setPreferredWidth(width);
		tc.setMaxWidth(width);
		tc.setMinWidth(width);
		tc.setWidth(width);
	}

	/**
	 * 设置表格对方向键、回车键及tab键的相应事件
	 *
	 */
	public void setTableForwarOrder() {
		Map<Integer, AbstractAction> map = new HashMap<Integer, AbstractAction>();
		map.put(KeyEvent.VK_TAB, new TabMoveForward());
		map.put(KeyEvent.VK_ENTER, new EnterMoveForward());
		map.put(KeyEvent.VK_UP, new UpMoveForward());
		map.put(KeyEvent.VK_DOWN, new DownMoveForward());
		map.put(KeyEvent.VK_LEFT, new LeftMoveForward());
		map.put(KeyEvent.VK_RIGHT, new RightMoveForward());
		map.put(KeyEvent.VK_PAGE_UP, new PageUpMoveForward());
		map.put(KeyEvent.VK_PAGE_DOWN, new PageDownMoveForward());

		for (Integer key : map.keySet()) {
			AbstractAction value = map.get(key);
			getInputMap().put(KeyStroke.getKeyStroke(key, 0),
					value.getClass().getName());
			getActionMap().put(value.getClass().getName(), value);
		}

	}

	private class TabMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = getEditingRow();
			int selCol = getEditingColumn();
			if (selRow == -1) {
				selRow = getSelectedRow();
			}
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount * colCount) {
					return;
				}
				++selCol;
				if (selCol >= colCount) {
					selCol = 0;
					++selRow;
					if (selRow >= rowCount) {
						selRow = 0;
					}
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}
	}

	private class EnterMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			downMoveForward.actionPerformed(e);
		}

		DownMoveForward downMoveForward = new DownMoveForward();
	}

	private class UpMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = getEditingRow();
			int selCol = getEditingColumn();
			if (selRow == -1) {
				selRow = getSelectedRow();
			}
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount) {
					return;
				}
				--selRow;
				if (selRow < 0) {
					selRow = rowCount - 1;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}
	}

	private class DownMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = getEditingRow();
			int selCol = getEditingColumn();
			if (selRow == -1) {
				selRow = getSelectedRow();
			}
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount) {
					return;
				}
				++selRow;
				if (selRow >= rowCount) {
					selRow = 0;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}
	}

	private class LeftMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = getEditingRow();
			int selCol = getEditingColumn();
			if (selRow == -1) {
				selRow = getSelectedRow();
			}
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount * colCount) {
					return;
				}
				--selCol;
				if (selCol < 0) {
					selCol = colCount - 1;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}
	}

	private class RightMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = getEditingRow();
			int selCol = getEditingColumn();
			if (selRow == -1) {
				selRow = getSelectedRow();
			}
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount * colCount) {
					return;
				}
				++selCol;
				if (selCol >= colCount) {
					selCol = 0;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}
	}

	private class PageUpMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = -1;
			int selCol = getEditingColumn();
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount) {
					getSelectionModel().setSelectionInterval(0, selRow);
					changeSelection(0, selCol, false, false);
					rightMoveForward.actionPerformed(e);
					return;
				}
				++selRow;
				if (selRow >= rowCount) {
					selRow = 0;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}

		private RightMoveForward rightMoveForward = new RightMoveForward();
	}

	private class PageDownMoveForward extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();
			int selRow = rowCount;
			int selCol = getEditingColumn();
			if (selCol == -1) {
				selCol = getSelectedColumn();
			}
			int count = 0;
			do {
				if (count++ > rowCount) {
					getSelectionModel().setSelectionInterval(rowCount - 1,
							selRow);
					changeSelection(rowCount - 1, selCol, false, false);
					rightMoveForward.actionPerformed(e);
					return;
				}
				--selRow;
				if (selRow < 0) {
					selRow = rowCount - 1;
				}
			} while (!isCellEditable(selRow, selCol));
			editCellAt(selRow, selCol, null);
			getSelectionModel().setSelectionInterval(selRow, selRow);
			changeSelection(selRow, selCol, false, false);
		}

		private RightMoveForward rightMoveForward = new RightMoveForward();
	}

	protected Logger logger = LoggerFactory.getLogger(getClass());
}

