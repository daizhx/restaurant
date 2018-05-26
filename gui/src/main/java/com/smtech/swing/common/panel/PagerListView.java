package com.smtech.swing.common.panel;

import com.smtech.restaurant.common.Pager;
import com.smtech.swing.common.Res;
import com.smtech.swing.common.btns.XButton;
import com.smtech.swing.common.layout.LinearLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PagerListView extends JPanel {

    private JComponent header;

    private LinearLayout sumPanel;

    private LinearLayout pagerPanel;

    private MyTableModel dataModel;

    private JTable table;

    // 一页显示的行数
    private int rowNum = 1;

    private Pager pager;

    private TextView tvPageInd;

    public PagerListView() {
        setLayout(new BorderLayout());


        dataModel = new MyTableModel();
        table = new JTable(dataModel);

        // table.getTableHeader().setVisible(false);
//        table.setTableHeader(null);
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
//        table.setRowHeight(30);
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
        add(table,BorderLayout.CENTER);
        crtPagerOprView();
//        add(crtPagerOprView(),BorderLayout.SOUTH);
    }

    public void setPager(Pager pager) {
        this.pager = pager;
        rowNum = pager.getPageSize();
        dataModel.setData(pager.getCurPageData());
        tvPageInd.setText(pager.toString());
    }

    private JComponent crtPagerOprView() {
        pagerPanel = new LinearLayout();
        pagerPanel.setOrientation(ViewGroup.HORIZONTAL);

        pagerPanel.addHorizontalGlue();

        XButton preBtn = new XButton();
        tvPageInd = new TextView("第 0/0 页");
        tvPageInd.setPadding(10,0,10,0);
        XButton nextBtn = new XButton();

        preBtn.setAction(new AbstractAction("上一页") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pager != null){
                    pager.prePage();
                    dataModel.setData(pager.getCurPageData());
                    dataModel.fireTableDataChanged();
                    tvPageInd.setText(pager.toString());
                }
            }
        });

        nextBtn.setAction(new AbstractAction("下一页") {
            @Override
            public void actionPerformed(ActionEvent e) {
                pager.nextPage();
                dataModel.setData(pager.getCurPageData());
                dataModel.fireTableDataChanged();
                tvPageInd.setText(pager.toString());
            }
        });

        pagerPanel.add(preBtn);
        pagerPanel.add(tvPageInd);
        pagerPanel.add(nextBtn);
        pagerPanel.addHorizontalGlue();
        pagerPanel.setPadding(10,10,10,10);
        return pagerPanel;
    }

    private JComponent crtSumView() {
        return null;
    }

    // 获取翻页按钮操作视图
    public ViewGroup getPagerBtnsView(){
        return pagerPanel;
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

        int colNum = 3;

        public MyTableModel() {
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
