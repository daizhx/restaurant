package com.smtech.swing.common.panel;

import com.smtech.swing.common.Res;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.List;



// 用swing组件模拟android的listview控件
public class ListView {


    JPanel view;

    JTable table;

    JScrollPane scrollPane;

    JPanel listContent;

    ListViewModel dataModel;

    JPanel sum;

    public ListView(int colNum) {
        view = new TransparentPanel(new BorderLayout());
        view.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        dataModel = new ListViewModel(colNum);
        table = new JTable(dataModel);
        scrollPane = new JScrollPane(table);
        // scrollPane.getViewport().setOpaque(false);
        // scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // table.getTableHeader().setVisible(false);
//        table.setTableHeader(null);
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);

        table.setRowHeight(50);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table
                .getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font(Res.FONT, Font.PLAIN, 15);
        table.setFont(font);
//        for (MouseListener ml : table.getMouseListeners()) {
//            table.removeMouseListener(ml);
//        }
        for (MouseMotionListener ml : table.getMouseMotionListeners()) {
            table.removeMouseMotionListener(ml);
        }

        listContent = new TransparentPanel(new BorderLayout());
        listContent.add(scrollPane, BorderLayout.CENTER);

        view.add(table.getTableHeader(),BorderLayout.NORTH);
        view.add(table, BorderLayout.CENTER);
    }

    public void setData(List<Object[]> l) {
        dataModel.setData(l);
        dataModel.fireTableDataChanged();
    }


    // 更新内容
    public void update() {
        dataModel.fireTableDataChanged();
    }

    public JPanel getView() {
        return view;
    }

    // 添加列表合计，添加到列表头部




    public static class ListViewModel extends AbstractTableModel {

        List<Object[]> data;

        int colNum;

        public ListViewModel(int colNum) {
            this.colNum = colNum;
        }

        @Override
        public int getRowCount() {
            if (data != null) {
                return data.size();
            }
            return 0;
        }

        @Override
        public int getColumnCount() {
            return colNum;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex)[columnIndex];
        }

        public List<Object[]> getData() {
            return data;
        }

        public void setData(List<Object[]> data) {
            this.data = data;
        }


        @Override
        public String getColumnName(int column) {
            String[] title = new String[]{"菜名","数量","金额"};
            return title[column];
        }
    }
}