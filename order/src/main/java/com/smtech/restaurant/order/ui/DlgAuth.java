package com.smtech.restaurant.order.ui;

import com.smtech.restaurant.order.Constants;
import com.smtech.restaurant.util.StringUtil;
import com.smtech.swing.common.Res;
import com.smtech.swing.common.btns.BtnByDraw;
import com.smtech.swing.common.btns.ButtonWrapper;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.AbsoluteLayoutEx;
import com.smtech.swing.common.layout.BorderLayoutEx;
import com.smtech.swing.common.layout.GridBagLayoutAdp;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.view.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//登录窗口
@Component
public class DlgAuth extends DlgBase implements InitializingBean,Constants {

    @Autowired
    public StatusBarMonitor monitor;
    private static final int comHigth = 60;
    //for user id input
    private JTextField tfYhzh;
    //for
    private JPasswordField tfYhmm;

    protected SaasKeyBoardPanel keyBoardPanel;

    @Autowired
    public DlgAuth() {
        super(new JFrame());
    }


    @Override
    protected void onCrtContntView(ViewGroup content) {
        content.setBackgroundImagePath("bg.png");
        content.setLayout(new BorderLayoutEx());
        content.add(createLogoPanel(), BorderLayout.NORTH);
        content.add(crtCenterView(),BorderLayout.CENTER);
    }

    //创建状态栏
    private JPanel createLogoPanel() {
        // 时间标签
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        JLabel time = new JLabel(format.format(new Date()));
        time.setFont(new Font(Res.FONT, Font.PLAIN, 18));
        time.setForeground(Color.white);
        time.setHorizontalAlignment(JLabel.RIGHT);
//        monitor.addTimeLable(new Object[] { time, format });
        // 版本标签
        JLabel verLab = new JLabel("");
        verLab.setFont(new Font(Res.FONT, Font.PLAIN, 18));
        verLab.setForeground(Color.white);
        verLab.setHorizontalAlignment(JLabel.RIGHT);

        // wifi
        ButtonWrapper wifiBtn = new ButtonWrapper();
        wifiBtn.setIcon("wifi.png", 20, 20);

        // 最小化
        ButtonWrapper minBtn = new ButtonWrapper();
        minBtn.setIcon("min.png", 20, 20);
        // 关闭（不是真正的关闭，而是让程序托盘）
        // ButtonWrapper closeBtn = new ButtonWrapper(new TuoPanAction());
        // closeBtn.setIcon(PopDlgBase.getSaasImgPath("main/close.png"), 20,
        // 20);

        TransparentView btnPanel = new TransparentView();
        TransparentView eastPanel = new TransparentView();

        GridBagLayoutAdp layout = new GridBagLayoutAdp();
        layout.setOrientation(SwingConstants.HORIZONTAL);
        layout.addGlue(213);
        layout.add(wifiBtn, 40);
        layout.addGlue(10);
        layout.add(minBtn, 40);
        layout.doLayout(btnPanel);

        layout.setOrientation(SwingConstants.VERTICAL);
        layout.addGlue(15);
        layout.add(btnPanel, 37);
        layout.addGlue(23);
        layout.add(time, 27);
        layout.addGlue(12);
        layout.add(verLab, 27);
        layout.doLayout(eastPanel);
        eastPanel.updateUI();

        // 餐厅名称
        JLabel company = new JLabel(format.format(System.currentTimeMillis()));

        company.setFont(fontInTitle);
        company.setForeground(Color.white);
        company.setHorizontalAlignment(JLabel.CENTER);
//        company.setText(QJCSInMemManager.getInstance().getCompanyName());

        // 柠檬树logo
        JImagePane logoImg = new JImagePane("logo.png");
        logoImg.setImageDisplayMode(JImagePane.CENTER_INSIDE);
        TransparentPanel logoPanel = new TransparentPanel(new BorderLayout());
        logoPanel.add(logoImg, BorderLayout.CENTER);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 30));

        logoPanel.setPreferredSize(new Dimension(250, 0));
        eastPanel.setPreferredSize(new Dimension(250, 0));

        ViewGroup panelTmp = new ViewGroup();
        panelTmp.setBackgroundColor(COLOR_STATUS_BG);
        panelTmp.setLayout(new BorderLayout());
        panelTmp.add(eastPanel, BorderLayout.EAST);
        panelTmp.add(company, BorderLayout.CENTER);
        panelTmp.add(logoPanel, BorderLayout.WEST);
        panelTmp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        panelTmp.setPreferredSize(new Dimension(0, 78));
        return panelTmp;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        JLabel time = new JLabel(format.format(new Date()));
        time.setFont(new Font(Res.FONT, Font.PLAIN, 18));
        time.setForeground(Color.white);
        time.setHorizontalAlignment(JLabel.RIGHT);
        monitor.addTimeLable(new Object[]{time, format});
    }

    /**
     * 系统通知
     */
    private class SysMsg extends AbstractAction {
        public SysMsg() {
            super("系统通知");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
//            String tips = "系统当前没有通知";
//            getDlgManager().getAuthorizeDlg().showMessageDialog(tips);
        }

    }

    /**
     * 创建放置“系统通知”,"版本升级","使用说明"按钮的面板
     *
     * @return
     */
    private JPanel crtUpgBtnView() {
        Dimension btnSize = new Dimension(181, 52);
        // ButtonWrapper btnSysMsg = new ButtonWrapper(new SysMsg());
        BtnByDraw btnSysMsg = new BtnByDraw();
        btnSysMsg.setAction(new SysMsg());
        // btnSysMsg.setBackgroundImage("saas/main/sys_btn_bg.png");
        btnSysMsg.setBgColor(ButtonWrapper.BgColor.RED);
        btnSysMsg.setTextColor(Res.TEXT_COLOR_REVERSE);
        btnSysMsg.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
        btnSysMsg.setFont(fontInTextBox);
        btnSysMsg.setPreferredSize(btnSize);
        btnSysMsg.setMinimumSize(btnSize);
        btnSysMsg.setMaximumSize(btnSize);

        // ButtonWrapper btnVerUpg = new ButtonWrapper(new VerUpg());
        BtnByDraw btnVerUpg = new BtnByDraw();
        btnVerUpg.setAction(new VerUpg());
        // btnVerUpg.setBackgroundImage("saas/main/sys_btn_bg.png");
        btnVerUpg.setBgColor(ButtonWrapper.BgColor.RED);
        btnVerUpg.setTextColor(Res.TEXT_COLOR_REVERSE);
        btnVerUpg.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
        btnVerUpg.setFont(fontInTextBox);
        btnVerUpg.setPreferredSize(btnSize);
        btnVerUpg.setMinimumSize(btnSize);
        btnVerUpg.setMaximumSize(btnSize);

        // ButtonWrapper btnDocument = new ButtonWrapper(new XiTongSheZhi());
        BtnByDraw btnDocument = new BtnByDraw();
        btnDocument.setAction(new XiTongSheZhi());
        // btnDocument.setBackgroundImage("saas/main/sys_btn_bg.png");
        btnDocument.setBgColor(ButtonWrapper.BgColor.RED);
        btnDocument.setTextColor(Res.TEXT_COLOR_REVERSE);
        btnDocument.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
        btnDocument.setFont(fontInTextBox);
        btnDocument.setPreferredSize(btnSize);
        btnDocument.setMinimumSize(btnSize);
        btnDocument.setMaximumSize(btnSize);

        PanelBuilder pb = new PanelBuilder();
        pb.addHorizontalGlue();
        pb.add(btnSysMsg);
        pb.addHorizontalGlue();
        pb.add(btnVerUpg);
        pb.addHorizontalGlue();
        pb.add(btnDocument);
        pb.addHorizontalGlue();
        TransparentPanel p = new TransparentPanel();
        pb.doLayout(p);
        return p;
    }

    /**
     * 版本升级
     */
    private class VerUpg extends AbstractAction {
        public VerUpg() {
            super("版本升级");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODOS
//            SysUpgDlg dlg = getDlgManager().getSysUpgDlg();
//            dlg.reflash();
//            dlg.setVisible(true);
        }

    }

    private class XiTongSheZhi extends AbstractAction {
        public XiTongSheZhi() {
            super("系统设置");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
//            showMenuDlg();
        }

    }

    /**
     * 创建版权所有信息框
     *
     * @return
     */
    private JComponent crtCopRigthView() {
        JLabel copyRightLab = new JLabel();
        copyRightLab.setHorizontalAlignment(SwingConstants.CENTER);
//        copyRightLab.setFont(PopDlgBase.fontInTextBox);
        copyRightLab.setForeground(Res.TEXT_COLOR_REVERSE);
        copyRightLab.setText("广东开饭啦互联网科技有限公司版权所有 www.lmnsaas.com 400-8622-820");
        return copyRightLab;
    }

    /**
     * 创建标题面板（包含关闭按钮和一条横线）
     *
     * @return
     */
    protected JPanel crtNorthPanel() {
        // 创建标题面板
        JLabel labTitle = new JLabel("系统登录");
        labTitle.setFont(fontInTitle);
        labTitle.setHorizontalAlignment(SwingConstants.CENTER);
        ButtonWrapper btnClose = new ButtonWrapper();
        btnClose.setIcon("KaiTaiDlg/btnClose.png");
        btnClose.setPreferredSize(new Dimension(60, 0));

        ButtonWrapper btnHelp = new ButtonWrapper();
        btnHelp.setIcon("KaiTaiDlg/help_icon.png");
        btnHelp.setPreferredSize(new Dimension(30, 0));

        TransparentPanel ePanel = new TransparentPanel();
        ePanel.setLayout(new GridLayout(1, 2));
        ePanel.add(btnHelp);
        ePanel.add(btnClose);

        TransparentPanel pTop = new TransparentPanel(new BorderLayout());
        pTop.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
        pTop.add(labTitle, BorderLayout.CENTER);
        pTop.add(ePanel, BorderLayout.EAST);

        // 组合上面两个面板
        TransparentPanel pTmp = new TransparentPanel(new BorderLayout());
        pTmp.add(pTop, BorderLayout.CENTER);

        // 创建分割线面板
        JPanel labLine = new JPanel() {
            @Override
            public void paintComponent(Graphics arg0) {
                Graphics2D g2 = (Graphics2D) arg0;
                g2.setColor(new Color(174, 0, 33)); // 对话框背景色
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 3, 3); // 填充圆角矩形，颜色为状态栏色调
            }
        };
        labLine.setPreferredSize(new Dimension(0, 5));

        TransparentPanel pBottom = new TransparentPanel(new BorderLayout());
        pBottom.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        pBottom.add(labLine, BorderLayout.CENTER);
        pBottom.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
        pTmp.add(pBottom, BorderLayout.SOUTH);

        pTmp.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        return pTmp;
    }
    /**
     * 创建放置登录按钮的面板
     */
    private JPanel crtLoginView() {
        // 先将内容面板在水平方向上进行切割，留边距20的边距
        TransparentPanel pH = new TransparentPanel(new BorderLayout());
        pH.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        pH.add(crtCenterPanel(), BorderLayout.CENTER);
        pH.add(Box.createHorizontalStrut(20), BorderLayout.EAST);

        // 在垂直方向上进行切割
        JPanel northPanel = crtNorthPanel();
        northPanel.setPreferredSize(new Dimension(0, 70));
        TransparentPanel pV = new TransparentPanel(new BorderLayout());
        pV.add(northPanel, BorderLayout.NORTH);
        pV.add(pH, BorderLayout.CENTER);
        pV.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        JPanel pTmp = new JPanel() {
            @Override
            public void paintComponent(Graphics arg0) {
                Graphics2D g2 = (Graphics2D) arg0;
                g2.setColor(new Color(230, 230, 230)); // 对话框背景色
                g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 12, 12); // 填充圆角矩形，颜色为状态栏色调
            }
        };
        pTmp.setLayout(new BorderLayout());
        pTmp.add(pV, BorderLayout.CENTER);
        Dimension sizeOfLoginView = new Dimension(455, 440);
        pTmp.setPreferredSize(sizeOfLoginView);
        pTmp.setMinimumSize(sizeOfLoginView);
        pTmp.setMaximumSize(sizeOfLoginView);
        return pTmp;
    }


    /**
     * 创建放置广告页面的面板
     *
     * @return
     */
    private JPanel crtAdView() {
        JImagePane adImg = new JImagePane();
        adImg.setImageDisplayMode(JImagePane.SCALED);
        adImg.setBackgroundImage("example.png");

        JImagePane panel = new JImagePane("main/ad_img_bg.png");
        panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        panel.setImageDisplayMode(JImagePane.SCALED);
        panel.setLayout(new BorderLayout());
        panel.add(adImg, BorderLayout.CENTER);
        Dimension sizeOfLoginView = new Dimension(455, 440);
        panel.setPreferredSize(sizeOfLoginView);
        panel.setMinimumSize(sizeOfLoginView);
        panel.setMaximumSize(sizeOfLoginView);
        return panel;
    }

    /**
     * 创建中间面板（包含广告面板、登录按钮面板）
     *
     * @return
     */
    private JPanel crtCenterView() {
        PanelBuilder pb = new PanelBuilder();
        pb.addHorizontalGlue();
        pb.add(crtAdView());
        pb.add(crtLoginView());
        pb.addHorizontalGlue();
        TransparentPanel panel = new TransparentPanel();
        pb.doLayout(panel);
        return panel;
    }


    protected JComponent crtCenterPanel() {
        // 创建输入框控件
        createFieldPanel();

        // 账号
        ButtonWrapper btnZh = new ButtonWrapper();
        btnZh.setBackgroundImage("backspace_btn.png");
        AbsoluteLayoutEx al = new AbsoluteLayoutEx();
        al.setBeginIndex(1430);
        al.add(UIUtil.createTFPanel("密码：", tfYhzh), 2100);
        al.addGlue(2110);
        al.add(btnZh, 2260);
        JPanel zhView = al.doLayout();

        // 密码
        ButtonWrapper btnMm = new ButtonWrapper("backspace_btn.png");
//        btnMm.setBackgroundImagePath("backspace_btn.png");
        al.setBeginIndex(1430);
        al.add(UIUtil.createTFPanel("账号：", tfYhmm), 2100);
        al.addGlue(2110);
        al.add(btnMm, 2260);
        JPanel mmView = al.doLayout();

        // 创建键盘面板
        keyBoardPanel = new SaasKeyBoardPanel(this);
//        keyBoardPanel.setOnCommit(new ActionQd());
        keyBoardPanel.init();

        // 垂直方向放置整体
        al.setOrientation(SwingConstants.VERTICAL);
        al.setBeginIndex(460);
        al.addGlue(490);
        al.add(zhView, 590);
        al.addGlue(600);
        al.add(mmView, 700);
        al.addGlue(710);
        al.add(keyBoardPanel, 1160);
        return al.doLayout();
    }

    /**
     * 选中用户账号
     *
     */
    class ActionXzyhzh extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            tfYhzh.requestFocusInWindow();
            tfYhzh.selectAll();
        }
    }

    /**
     * 选中用户密码
     *
     */
    class ActionXzyhmm extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            tfYhmm.requestFocusInWindow();
            tfYhmm.selectAll();
        }
    }

    /**
     * 创建输入框
     */
    protected void createFieldPanel() {
        JButton btnSelete = new JButton(new ActionXzyhzh());
        JButton btnPWSelete = new JButton(new ActionXzyhmm());
        btnSelete.setFont(fontInBtn);
        btnSelete.setPreferredSize(new Dimension(110, comHigth));
        btnSelete.setBorder(null);

        btnPWSelete.setFont(fontInBtn);
        btnPWSelete.setPreferredSize(new Dimension(110, comHigth));
        btnPWSelete.setBorder(null);

        tfYhzh = new JTextField();// 用户账号
        tfYhzh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    onCancel();
                    return;
                }
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    return;
                }

                if (StringUtil.isNull(tfYhzh.getText())) {
                    return;
                }

                tfYhmm.selectAll();
                tfYhmm.requestFocusInWindow();
            }
        });
        tfYhzh.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }

            @Override
            public void focusGained(FocusEvent e) {
                keyBoardPanel.setCurTf(tfYhzh);
            }
        });

        tfYhzh.setBorder(BorderFactory.createEmptyBorder(0, gapInBorder, 0, gapInBorder));
        tfYhzh.setFont(fontInTextBox);

        tfYhmm = new JPasswordField();// 用户密码
        tfYhmm.setEchoChar('*');
        tfYhmm.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
//                    commit();
                    return;
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    onCancel();
                }
            }

        });
        tfYhmm.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }

            @Override
            public void focusGained(FocusEvent e) {
                keyBoardPanel.setCurTf(tfYhmm);
            }
        });

        tfYhmm.setBorder(BorderFactory.createEmptyBorder(0, gapInBorder, 0, gapInBorder));
        tfYhmm.setFont(fontInTextBox);
    }

    private void onCancel() {
        //TODO 退出
    }

}
