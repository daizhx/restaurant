package com.smtech.restaurant.setting;

import com.smtech.restaurant.common.http.HttpClient;
import com.smtech.swing.common.MainFrame;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.view.CommonTable;
import com.smtech.swing.common.view.TextFieldEx;
import com.smtech.swing.common.view.ViewGroup;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看对象信息的对话框
 *
 */
public abstract class DspBeanBaseDlg<T> extends FunctionItemBase {

    protected JButton btnForAdd;// 增加按钮
    protected JButton btnForRmv;// 删除按钮
    protected JButton btnForMod;// 修改按钮
    protected JButton btnForDsp;// 查询按钮
    protected JButton btnForReflash;// 刷新按钮
    protected JButton btnForExport;// 导出按钮
    protected JPanel panelForSearch;//

    // 放置自身实例的表格
    protected CommonTable table;

    //对象数据
    protected List<T> data = new ArrayList<T>();

    @Override
    public void init() {
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(createBtnPanel(), BorderLayout.NORTH);
        contentPanel.add(createCenterPanel(), BorderLayout.CENTER);
    }

    //从server加载数据的api
    protected abstract String loadDataApi();

    //需要显示的字段
    protected abstract String[] getDspFields();
    //需要显示的字段名称
    protected abstract String[] getDspFieldTitles();

    private void loadData(){
        HttpClient httpClient = HttpClient.getInstance();
        httpClient.get(httpClient.getLocal(loadDataApi()), new HttpClient.HttpRequestResult() {
            @Override
            public void onSuccess(String res) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    //获取泛型类型对象
    public Class getTempalteType() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    @Override
    public void reflash() {



    }

    /**
     * 创建中央面板（包含左侧的父类对象面板，及自身实例的表格面板）
     */
    protected JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel tablePanel = createTablePanel();
        JPanel parentPanel = createParentPanel();
        if (null == parentPanel) {// 该类没有父类
            centerPanel.add(tablePanel);
        } else {
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    parentPanel, tablePanel);
            splitPane.setContinuousLayout(true);
            centerPanel.add(splitPane);
        }
        return centerPanel;
    }

    protected JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());

        table = new CommonTable();
        table.setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return getDspFields().length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                T t = data.get(rowIndex);
                String fn = getDspFields()[columnIndex];

                //获取属性值
                Class<T> beanClass = getTempalteType();
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(fn, beanClass);
                    Method method = pd.getReadMethod();
                    Object val = method.invoke(t);
                    return val;
                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            public String getColumnName(int column) {
                return getDspFieldTitles()[column];
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0// 左键
                        && e.getClickCount() > 1) {//
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            btnForMod.doClick();
                        }
                    });
                } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {// 右键
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        table.setRowSelectionInterval(row, row);
                    }

                }
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    btnForRmv.doClick();
                }
            }
        });

        table.getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        // 调整表格各列的长度
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableCellRenderer renderer = table.getTableHeader()
                .getDefaultRenderer();

        JScrollPane jsp = new JScrollPane(table);
        jsp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {// 右键

                }
            }
        });
        tablePanel.add(jsp);
        return tablePanel;
    }

    /**
     * 创建父类对象面板
     *
     * @return
     */
    protected JPanel createParentPanel() {
        // 一棵树，放置各个父对象实例
//        setParentBeans();
//        if (curRelatedObj == -1) {// 没有父类
//            return null;
//        }
//
//        popMenuInTree = new JPopupMenu();
//        popMenuInTree.add(new JMenuItem(new ActionForAddNode()));
//        popMenuInTree.add(new JMenuItem(new ActionForRmvNode()));
//        popMenuInTree.add(new JMenuItem(new ActionForModNode()));
//
//        // 创建父类对象树
//        JPanel parentPanel = new JPanel(new BorderLayout());
//        parentPanel.setPreferredSize(new Dimension(150, 0));
//        parentPanel.add(treePanel, BorderLayout.CENTER);
//        return parentPanel;
        return new ViewGroup();
    }

    /**
     * 创建按钮面板
     */
    protected JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEtchedBorder());

        btnForAdd = createBtn(new ActionForAdd(), KeyEvent.VK_F1);
        btnForAdd.setIcon(createImageIcon("16-16/add.png"));

        btnForRmv = createBtn(new ActionForRmv(), KeyEvent.VK_F2);
        btnForRmv.setIcon(createImageIcon("16-16/delete.png"));

        btnForMod = createBtn(new ActionForMod(), KeyEvent.VK_F3);
        btnForMod.setIcon(createImageIcon("16-16/document_edit.png"));

        btnForDsp = createBtn(new ActionForDsp(), KeyEvent.VK_F4);
        btnForDsp.setIcon(createImageIcon("16-16/zoom.png"));

        btnForReflash = createBtn(new ActionForReflash(), KeyEvent.VK_F5);
        btnForReflash.setIcon(createImageIcon("16-16/refresh.png"));

        btnForExport = createBtn(new ActionForExport(), KeyEvent.VK_F6);
        btnForExport.setIcon(createImageIcon("16-16/email.png"));

        TextFieldEx tfChaXunShuRu = new TextFieldEx();
//        setIdCardInput(tfChaXunShuRu);
//        panelForSearch = createSearchPane(tfChaXunShuRu);
        PanelBuilder pb = new PanelBuilder();
        pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                5, 0, 5, 3), 0, 0));
        pb.add(btnForAdd);
        pb.add(btnForRmv);
        pb.add(btnForMod);
        pb.add(btnForReflash);
        pb.add(btnForExport);
//        pb.add(panelForSearch);// 快速查找
        pb.addHorizontalGlue();
        pb.doLayout(btnPanel);
        return btnPanel;
    }

    protected class ActionForAdd extends AbstractAction {

        public ActionForAdd() {
            super("增加");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            DlgEditBean<T> dlg = (DlgEditBean) DlgManager.getInstance().getDlg(DlgEditBean.class);
            Class<T> cls = getTempalteType();
            T t  = null;
            try {
                t = cls.newInstance();

                Constructor<?>[] cons = DlgEditBean.class.getConstructors();
                Constructor<?> constructor = cons[0];
//                Constructor<DlgEditBean> constructor = (Constructor<DlgEditBean>) DlgEditBean.class.getDeclaredConstructor(Window.class,cls);
                DlgEditBean dlg = (DlgEditBean) constructor.newInstance(MainFrame.getInstance(),t);
                dlg.display();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }

        }
    }

    protected class ActionForRmv extends AbstractAction {

        public ActionForRmv() {
            super("删除");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class ActionForMod extends AbstractAction {
        public ActionForMod() {
            super("修改");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class ActionForDsp extends AbstractAction {

        public ActionForDsp() {
            super("查询");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class ActionForReflash extends AbstractAction {

        public ActionForReflash() {
            super("刷新");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class ActionForExport extends AbstractAction {
        public ActionForExport() {
            super("导出");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

