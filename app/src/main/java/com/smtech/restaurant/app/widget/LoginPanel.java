package com.smtech.restaurant.app.widget;

import com.smtech.swing.common.btns.XImageButton;
import com.smtech.swing.common.layout.GridBagLayoutEx;
import com.smtech.swing.common.util.PanelBuilder;
import com.smtech.swing.common.view.TransparentView;
import com.smtech.swing.common.view.ViewGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登陆面板，提供用户的登陆进入系统
 */
public class LoginPanel extends ViewGroup{

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    private ActionListener actionListener;

    public interface ActionListener{
        void login(String account,String pwd);
    }

	public LoginPanel() {
		setOpaque(false);
		init();
	}



	public void init() {
		JPanel centerPane = new TransparentView(new BorderLayout(0, 0));
		centerPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		centerPane.add(createCenterPanel(), BorderLayout.CENTER);
		centerPane.add(createKeyBoardPanel(), BorderLayout.EAST);

		setLayout(new BorderLayout(0, 0));
		add(centerPane, BorderLayout.CENTER);
		setBorder(BorderFactory.createEtchedBorder());


	}

	public void reflash() {
		userTf.setText("");
		pwdPf.setText("");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				userTf.requestFocusInWindow();
			}
		});
	}

	/**
	 * 创建键盘按钮
	 *
	 * @return
	 */
	private JPanel createKeyBoardPanel() {
		List<JButton> btns = new Vector<JButton>();
		for (int c = 0; c <= 9; c++) {
			String str = String.valueOf(c);
			str = String.format("%s", str);
			JButton btn = new JButton(new KeyAction(str));

			UIUtil.fixSize(btn, new Dimension(50, 50));
			btns.add(btn);
		}
		JButton qkBtn = new JButton(new KeyAction());
		btns.add(qkBtn);

		JPanel panel = new TransparentView(new GridBagLayout());
		int[] gx = { 3, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3 };
		int[] gy = { 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 1 };
		int[] gw = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		int[] gh = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 };
		GridBagConstraints gc = new GridBagConstraints();
		for (int i = 0; i < btns.size(); i++) {
			gc.gridx = gx[i];
			gc.gridy = gy[i];
			gc.gridwidth = gw[i];
			gc.gridheight = gh[i];
			gc.weightx = 1.0;
			gc.weighty = 1.0;
			gc.fill = GridBagConstraints.BOTH;
			gc.insets = new Insets(3, 3, 4, 3);

			JButton btn = btns.get(i);
			btn.setFont(new Font(UIUtil.getComFontName(), Font.BOLD, 18));
			panel.add(btns.get(i), gc);
		}
        UIUtil.fixSize(panel, new Dimension(330, 220));
		return panel;
	}

	/**
	 * 创建中间面板
	 *
	 * @return
	 */
	private JPanel createCenterPanel() {
		// 创建控件
		JLabel userLabel = new JLabel("账号：");
		// userLabel.setForeground(Color.GRAY);
		userLabel.setHorizontalAlignment(JLabel.RIGHT);
		userTf = new JTextField(20);
		userTf.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		userTf.setOpaque(false);
		// userTf.setForeground(Color.GRAY);
		iCcardInput(); // 设置IC卡输入登陆
		userTf.requestFocus();
		userTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					pwdPf.selectAll();
					pwdPf.requestFocusInWindow();
					return;
				}
				super.keyTyped(e);
			}
		});
		userTf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				curTf = userTf;
			}
		});

		JLabel pwdLabel = new JLabel("密码：");
		pwdLabel.setHorizontalAlignment(JLabel.RIGHT);
		// pwdLabel.setForeground(Color.GRAY);
		pwdPf = new JPasswordField();
		pwdPf.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pwdPf.setOpaque(false);
		// pwdPf.setForeground(Color.GRAY);
		pwdPf.setDocument(new PlainDocument() {
			public void insertString(int offset, String s, AttributeSet a)
					throws BadLocationException {
				String str = getText(0, getLength());
				String strNew = str.substring(0, offset) + s
						+ str.substring(offset, getLength());
				Pattern pt = Pattern.compile("^\\w*\\^*$");
				Matcher m = pt.matcher(strNew);
				if (m.find()) {
					super.insertString(offset, s, a);
				}
			}
		});
		pwdPf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					btnLogin.doClick();
					return;
				}
			}
		});
		pwdPf.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				curTf = pwdPf;
			}
		});

		btnLogin = new XImageButton(new LoginAction());
		// btnLogin.setForeground(Color.white);
		btnLogin.setImgPath("app/login.png");
		XImageButton btnQuit = new XImageButton(new QuitAction());
		// btnQuit.setForeground(Color.white);
		btnQuit.setImgPath("app/cancel.png");
		UIUtil.addHotKeyForBtn(btnQuit, KeyEvent.VK_ESCAPE);

		ViewGroup userNameTxtPane = new ViewGroup( inputFieldBg, ViewGroup.SCALED);
		userNameTxtPane.setOpaque(false);
		userNameTxtPane.setLayout(new BorderLayout());
		userNameTxtPane.add(userTf, BorderLayout.CENTER);

		ViewGroup userPwdTxtPane = new ViewGroup(inputFieldBg, ViewGroup.SCALED);
		userPwdTxtPane.setOpaque(false);
		userPwdTxtPane.setLayout(new BorderLayout());
		userPwdTxtPane.add(pwdPf, BorderLayout.CENTER);

		PanelBuilder pb = new PanelBuilder();
		pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
				0, 3, 0, 0), 0, 0));
		pb.add(userLabel);
		pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
				0, 3, 0, 0), 0, 0));
		pb.addLn(userNameTxtPane);
		pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
				0, 3, 0, 0), 0, 0));
		pb.add(pwdLabel);
		pb.setGridBagConstraints(new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
				0, 3, 0, 0), 0, 0));
		pb.addLn(userPwdTxtPane);
		JPanel usrPanel = new TransparentView();
		pb.doLayout(usrPanel);

		// 布局控件
		GridBagLayoutEx gbEx = new GridBagLayoutEx(4, 8, new Insets(0, 0, 0, 0));
		gbEx.addComponent(usrPanel, 0, 0, 8, 2);
		gbEx.addComponent(btnQuit, 0, 3, 3, 1);
		gbEx.addComponent(btnLogin, 5, 3, 3, 1);
		JPanel centerPanel = new TransparentView();
		gbEx.doLayout(centerPanel);
		UIUtil.setComponentsFont(centerPanel,
				new Font(UIUtil.getComFontName(), Font.BOLD, 18));
		UIUtil.fixSize(centerPanel, new Dimension(450, 0));

		return centerPanel;
	}

	/**
	 * IC卡输入登陆
	 */
	private void iCcardInput() {
	    //TODO 暂时不弄
    }


	/**
	 * 确定登陆
	 */
	private void commit() {

		String userName = userTf.getText();
		if (userName == null || userName.equals("")) {
			JOptionPane.showMessageDialog(LoginPanel.this, "账号不能，请输入账号！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			userTf.requestFocus();
			return;
		}

		String pwd = pwdPf.getText();

		if(actionListener != null){
		    actionListener.login(userName,pwd);
        }


	}

	/**
	 * 登录
	 *
	 */
	private class LoginAction extends AbstractAction {
		LoginAction() {
			super("登录");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			commit();
		}
	}

	/**
	 * 退出
	 *
	 */
	private class QuitAction extends AbstractAction {
		QuitAction() {
			super("退出");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * 数字按钮，KEY为NULL时，用于清空
	 */
	private class KeyAction extends AbstractAction {
		KeyAction() {
			super("清除");
		}

		KeyAction(String key) {
			super(key);
			this.key = key;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.trace("enter");
			if (curTf == null) {
				return;
			}
			if (key == null) {
				curTf.setText("");
			} else {
				curTf.replaceSelection(key);
			}
			curTf.requestFocusInWindow();
		}

		String key;
	}

	private XImageButton btnLogin;
	private JTextField userTf;
	private JPasswordField pwdPf;
	private JTextField curTf;// 当前选中的编辑框

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static final String loginPanelBg = "frame/loginPanelBg.png"; // 登陆面板背景
	public static final String loginBtnBg = "frame/login.png";
	public static final String cancelBtnBg = "frame/cancel.png";
	public static final String inputFieldBg = "app/field_Bg1.png"; // 输入背景

}

