package com.smtech.swing.common.panel;

import com.smtech.swing.common.Res;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PagerView extends TransparentPanel {

    private JComponent header;

    private JTable contentTable;

    private JPanel sumPanel;

    private JPanel pagerPanel;

    private MyTableModel dataModel;

    private JTable table;

    // 一页显示的行数
    private int rowNum;

    private int colNum;

    public PagerView(int rowNum,int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        setLayout(new BorderLayout());
        dataModel = new MyTableModel(colNum);
        table = new JTable(dataModel);

        // table.getTableHeader().setVisible(false);
//        table.setTableHeader(null);
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
//        table.setRowHeight(70);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table
                .getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font(Res.FONT, Font.PLAIN, 15);
        table.setFont(font);
        for (MouseListener ml : table.getMouseListeners()) {
            table.removeMouseListener(ml);
        }
        for (MouseMotionListener ml : table.getMouseMotionListeners()) {
            table.removeMouseMotionListener(ml);
        }


        add(table.getTableHeader(),BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);


        pagerPanel = new JPanel();
        pagerPanel.setBackground(Color.red);
        pagerPanel.setPreferredSize(new Dimension(0,40));


        add(pagerPanel,BorderLayout.SOUTH);
    }

    public void setData(List<Object[]> l) {
        dataModel.setData(l);
        dataModel.fireTableDataChanged();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        int th = table.getHeight();
        int t = th%rowNum;
        int rh = th/rowNum;
        if(t > (rowNum - 5)){
            //只差5个像素值，满足它
            rh += 1;
        }
        table.setRowHeight(rh);
    }


    public static class MyTableModel extends AbstractTableModel {

        List<Object[]> data;

        int colNum;

        public MyTableModel(int colNum) {
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
            String[] title = new String[]{"菜名", "数量", "金额"};
            return title[column];
        }
    }

}
