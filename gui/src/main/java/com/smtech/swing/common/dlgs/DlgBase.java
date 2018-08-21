package com.smtech.swing.common.dlgs;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.Res;
import com.smtech.swing.common.btns.BtnByDraw;
import com.smtech.swing.common.btns.ButtonWrapper;
import com.smtech.swing.common.view.TransparentView;
import com.smtech.swing.common.util.PanelWraper;
import com.sun.awt.AWTUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 全屏对话框
 */
public class DlgBase extends JDialog{
    private final Logger logger = LoggerFactory.getLogger(DlgBase.class);

    // 得到显示器屏幕的宽高
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public DlgBase(Window owner) {
        super(owner);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel content =  new TransparentView();
        content.setBackground(Color.WHITE);
        onCrtContntView(content);
        setContentPane(content);

        // 设置对话框属性
//        setUndecorated(true);
        setModal(true);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        pack();
    }


    //子类重写构建界面内容,默认黑板
    protected void onCrtContntView(JPanel content){

    }

    //默认是全屏显示，设置大小，居中显示
    public void setSize(int width,int height){
        setBounds((screenWidth - width) / 2,
                (screenHeight - height) / 2, width, height);
    }

    // 关闭对话框窗口
    public void close(){
        dispose();
        setVisible(false);
    }


    private void showMessageDialogInner(final Window parentDialog, final Object message, boolean notNeedHotkey) {
        JLabel msgLab = new JLabel((String) message);
        msgLab.setOpaque(false);
        msgLab.setFont(Res.FONT_TEXT);
        msgLab.setHorizontalAlignment(SwingConstants.CENTER);
        msgLab.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));

        // 创建对话框的面板，添加内容
        JDialog dlg = new JDialog(parentDialog);// 创建显示对话框
        dlg.setUndecorated(true);
        // JButton button = new JButton(new ActionQD(dlg));
        // dlg.getRootPane().setDefaultButton(button);
        // button.setText(getStr("PopDlgBase.smDlg"));
        // button.setFont(getFontInTextBox());
        // final ButtonWrapper oKBtn = new ButtonWrapper(button,
        // getSaasCommonImg("msg_ok_btn.png"));
        // oKBtn.setPreferredSize(new Dimension(176, 50));
        // oKBtn.setTextColor(Res.TEXT_COLOR_REVERSE);
        final BtnByDraw oKBtn = new BtnByDraw();
        oKBtn.setAction(new ActionQD(dlg));
        oKBtn.setFont(Res.FONT_TEXT);
        oKBtn.setTextColor(Res.TEXT_COLOR_REVERSE);
        oKBtn.setPreferredSize(new Dimension(176, 50));
        oKBtn.setBgColor(ButtonWrapper.BgColor.RED);

        JPanel btnPanel = new TransparentView();
        btnPanel.add(oKBtn);

        TransparentView contentP = new TransparentView();
        contentP.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        contentP.setLayout(new BorderLayout(10, 10));
        contentP.add(msgLab, BorderLayout.CENTER);
        contentP.add(btnPanel, BorderLayout.SOUTH);
        if (!notNeedHotkey) {
            dlg.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                        oKBtn.doClick();
                    }
                }
            });
        }
        dlg.setContentPane(PanelWraper.wrapForFullScr(contentP));
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setLocation(0, 0);
        AWTUtilities.setWindowOpaque(dlg, false);
        dlg.setVisible(true);
    }


    /**
     * 弹出消息框
     *
     * @param parentDialog
     * @param message
     * @param title
     * @param messageType
     * @param icon
     */
    public void showMessageDialog(final JDialog parentDialog, final Object message, String title, int messageType, Icon icon) {
        if (SwingUtilities.isEventDispatchThread()) {
            showMessageDialogInner(parentDialog, message);
            return;
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    showMessageDialogInner(parentDialog, message);
                }
            });
        } catch (Exception e) {
            logger.error(StackTraceToString.getExceptionTrace(e));
        }
    }

    private void showMessageDialogInner(final Window parentDialog, final Object message) {
        showMessageDialogInner(parentDialog, message, false);
    }


    public void showMessageDialog(JDialog parentDialog, Object message, String title, int messageType) {
        showMessageDialog(parentDialog, message, title, messageType, null);
    }

    /**
     * 弹出提示框
     * @param message
     */
    public void showMessageDialog(Object message) {
        showMessageDialog(DlgBase.this, message, "", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * showMessageDialog消息信息按钮事件
     *
     */
    private class ActionQD extends AbstractAction {
        ActionQD(Dialog dlg) {
            super("确定");
            this.dlg = dlg;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dlg.setVisible(false);
            dlg.dispose();
        }

        Dialog dlg;
    }

    //显示对话框窗口
    public void display(){
        setVisible(true);
        dispose();
    }

}
