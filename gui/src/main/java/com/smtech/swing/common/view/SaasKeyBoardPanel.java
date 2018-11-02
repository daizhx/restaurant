package com.smtech.swing.common.view;

import com.smtech.swing.common.Res;
import com.smtech.swing.common.btns.BtnByDraw;
import com.smtech.swing.common.btns.ButtonWrapper;
import com.smtech.swing.common.btns.ButtonWrapper.BgColor;
import com.smtech.swing.common.dlgs.DlgBase;
import com.smtech.swing.common.layout.GridBagLayoutEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;


/**
 * 带有退格键的数字键盘
 */
public class SaasKeyBoardPanel extends TransparentPanel {
	public SaasKeyBoardPanel(DlgBase dlg) {
		super();
		this.dlg = dlg;
		initComponents();
	}

	/**
	 * 设置面板中按钮的字体
	 * 
	 * @param font
	 */
	public void setBtnFont(Font font) {
		for (BtnByDraw btnWarpper : btns) {
			btnWarpper.getComponet().setFont(font);
		}
	}

	/**
	 * 设置键盘按钮修改的编辑框
	 * 
	 * @param curTf
	 */
	public void setCurTf(JTextField curTf) {
		this.curTf = curTf;
	}

	public void setOnCommit(AbstractAction onCommit) {
		this.onCommit = onCommit;
	}

	public void clear() {
		if (curTf != null) {
			curTf.setText("");
		}

	}

	public void init4Row3Col() {
		b0 = new BtnByDraw(new Key("0"));
		b1 = new BtnByDraw(new Key("1"));
		b2 = new BtnByDraw(new Key("2"));
		b3 = new BtnByDraw(new Key("3"));
		b4 = new BtnByDraw(new Key("4"));
		b5 = new BtnByDraw(new Key("5"));
		b6 = new BtnByDraw(new Key("6"));
		b7 = new BtnByDraw(new Key("7"));
		b8 = new BtnByDraw(new Key("8"));
		b9 = new BtnByDraw(new Key("9"));
		bPoint = new BtnByDraw(new Key("."));
		bClear = new BtnByDraw(new Clear());

		btns.add(b0);
		btns.add(b1);
		btns.add(b2);
		btns.add(b3);
		btns.add(b4);
		btns.add(b5);
		btns.add(b6);
		btns.add(b7);
		btns.add(b8);
		btns.add(b9);
		btns.add(bPoint);
		btns.add(bClear);

		Font font = new Font(Res.FONT, Font.PLAIN, 21);
		for (BtnByDraw btn : btns) {
			btn.setFont(font);
			btn.setTextColor(Res.TEXT_COLOR);
			btn.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
			btn.setBgColor(BgColor.GRAY);
			btn.setBorderColor(Color.GRAY);
		}
		bRollBack = new BtnByDraw(new RollBack());
		bRollBack.setBackground("SaasKeyBoardPanel/rollBack.png");
		// btns.add(bRollBack);

		GridBagLayoutEx gx = new GridBagLayoutEx(4, 3, insetsEx);
		gx.addComponent(b1, 0, 0, 1, 1);
		gx.addComponent(b2, 1, 0, 1, 1);
		gx.addComponent(b3, 2, 0, 1, 1);

		gx.addComponent(b4, 0, 1, 1, 1);
		gx.addComponent(b5, 1, 1, 1, 1);
		gx.addComponent(b6, 2, 1, 1, 1);

		gx.addComponent(b7, 0, 2, 1, 1);
		gx.addComponent(b8, 1, 2, 1, 1);
		gx.addComponent(b9, 2, 2, 1, 1);

		gx.addComponent(b0, 0, 3, 1, 1);
		gx.addComponent(bPoint, 1, 3, 1, 1);
		gx.addComponent(bClear, 2, 3, 1, 1);
		gx.doLayout(this);

	}

	public void init3Row4Col() {
		b0 = new BtnByDraw(new Key("0"));
		b1 = new BtnByDraw(new Key("1"));
		b2 = new BtnByDraw(new Key("2"));
		b3 = new BtnByDraw(new Key("3"));
		b4 = new BtnByDraw(new Key("4"));
		b5 = new BtnByDraw(new Key("5"));
		b6 = new BtnByDraw(new Key("6"));
		b7 = new BtnByDraw(new Key("7"));
		b8 = new BtnByDraw(new Key("8"));
		b9 = new BtnByDraw(new Key("9"));
		bPoint = new BtnByDraw(new Key("."));
		bClear = new BtnByDraw(new Clear());

		btns.add(b0);
		btns.add(b1);
		btns.add(b2);
		btns.add(b3);
		btns.add(b4);
		btns.add(b5);
		btns.add(b6);
		btns.add(b7);
		btns.add(b8);
		btns.add(b9);
		btns.add(bPoint);
		btns.add(bClear);

		Font font = new Font(Res.FONT, Font.PLAIN, Res.FONT_SIZE_BTN);
		for (BtnByDraw btn : btns) {
			btn.setFont(font);
			btn.setTextColor(Res.TEXT_COLOR);
			btn.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
			btn.setBgColor(BgColor.GRAY);
			btn.setBorderColor(Color.GRAY);
		}
		bRollBack = new BtnByDraw(new RollBack());
		bRollBack.setBackground("SaasKeyBoardPanel/rollBack.png");
		// btns.add(bRollBack);

		GridBagLayoutEx gx = new GridBagLayoutEx(3, 4, insetsEx);
		gx.addComponent(b1, 0, 0, 1, 1);
		gx.addComponent(b2, 0, 1, 1, 1);
		gx.addComponent(b3, 0, 2, 1, 1);

		gx.addComponent(b4, 1, 0, 1, 1);
		gx.addComponent(b5, 1, 1, 1, 1);
		gx.addComponent(b6, 1, 2, 1, 1);

		gx.addComponent(b7, 2, 0, 1, 1);
		gx.addComponent(b8, 2, 1, 1, 1);
		gx.addComponent(b9, 2, 2, 1, 1);

		gx.addComponent(b0, 3, 0, 1, 1);
		gx.addComponent(bPoint, 3, 1, 1, 1);
		gx.addComponent(bClear, 3, 2, 1, 1);
		gx.doLayout(this);

	}
	
	public void initForCommit(BtnByDraw bCommit) {
		b0 = new BtnByDraw(new Key("0"));
		b1 = new BtnByDraw(new Key("1"));
		b2 = new BtnByDraw(new Key("2"));
		b3 = new BtnByDraw(new Key("3"));
		b4 = new BtnByDraw(new Key("4"));
		b5 = new BtnByDraw(new Key("5"));
		b6 = new BtnByDraw(new Key("6"));
		b7 = new BtnByDraw(new Key("7"));
		b8 = new BtnByDraw(new Key("8"));
		b9 = new BtnByDraw(new Key("9"));
		bPoint = new BtnByDraw(new Key("."));
		bClear = new BtnByDraw(new Clear());
		btns.add(b0);
		btns.add(b1);
		btns.add(b2);
		btns.add(b3);
		btns.add(b4);
		btns.add(b5);
		btns.add(b6);
		btns.add(b7);
		btns.add(b8);
		btns.add(b9);
		btns.add(bPoint);
		btns.add(bClear);
		Font font = new Font(Res.FONT, Font.PLAIN, Res.FONT_SIZE_BTN);
		for (BtnByDraw btn : btns) {
			btn.setFont(font);
			// btn.setBackgroundImage(PopDlgBase
			// .getSaasCommonImg("SaasKeyBoardPanel/key.png"));
			btn.setTextColor(Res.TEXT_COLOR);
			btn.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
			btn.setBgColor(BgColor.GRAY);
			btn.setBorderColor(Color.GRAY);
		}
		bRollBack = new BtnByDraw(new RollBack());
		bRollBack.setBackground("SaasKeyBoardPanel/rollBack.png");
		// btns.add(bRollBack);

	}

	/**
	 * 面板初始化
	 */
	public void init() {

		if (hideCommitBtn) {
			// 不带确定按钮
			layoutNotCommit();
			return;
		}

		GridBagLayoutEx gx = new GridBagLayoutEx(3, 5, insetsEx);
		gx.addComponent(b1, 0, 0, 1, 1);
		gx.addComponent(b2, 1, 0, 1, 1);
		gx.addComponent(b3, 2, 0, 1, 1);
		gx.addComponent(b0, 3, 0, 1, 1);
		if (showDianBtn) {
			gx.addComponent(bPoint, 4, 0, 1, 1);
		} else {
			gx.addComponent(bClear, 4, 0, 1, 1);
		}

		gx.addComponent(b4, 0, 1, 1, 1);
		gx.addComponent(b5, 1, 1, 1, 1);
		gx.addComponent(b6, 2, 1, 1, 1);
		gx.addComponent(bCommit, 3, 1, 2, 2);

		gx.addComponent(b7, 0, 2, 1, 1);
		gx.addComponent(b8, 1, 2, 1, 1);
		gx.addComponent(b9, 2, 2, 1, 1);
		gx.doLayout(this);
	}

	private void initComponents() {
		b0 = new BtnByDraw(new Key("0"));
		b1 = new BtnByDraw(new Key("1"));
		b2 = new BtnByDraw(new Key("2"));
		b3 = new BtnByDraw(new Key("3"));
		b4 = new BtnByDraw(new Key("4"));
		b5 = new BtnByDraw(new Key("5"));
		b6 = new BtnByDraw(new Key("6"));
		b7 = new BtnByDraw(new Key("7"));
		b8 = new BtnByDraw(new Key("8"));
		b9 = new BtnByDraw(new Key("9"));
		bPoint = new BtnByDraw(new Key("."));
		bClear = new BtnByDraw(new Clear());

		btns.add(b0);
		btns.add(b1);
		btns.add(b2);
		btns.add(b3);
		btns.add(b4);
		btns.add(b5);
		btns.add(b6);
		btns.add(b7);
		btns.add(b8);
		btns.add(b9);
		btns.add(bPoint);
		btns.add(bClear);

		Font font = new Font(Res.FONT, Font.PLAIN, Res.FONT_SIZE_BTN);
		for (BtnByDraw btn : btns) {
			btn.setFont(font);
			// btn.setBackgroundImage(PopDlgBase
			// .getSaasCommonImg("SaasKeyBoardPanel/key.png"));
			btn.setTextColor(Res.TEXT_COLOR);
			btn.setPressedTextColor(Res.TEXT_COLOR_REVERSE);
			btn.setBgColor(BgColor.GRAY);
			btn.setBorderColor(Color.GRAY);
		}
		bRollBack = new ButtonWrapper(new RollBack());
		bRollBack.setBackground("SaasKeyBoardPanel/rollBack.png");
		// bRollBack.setBgColor(BgColor.GRAY);
		// btns.add(bRollBack);

		bCommit = new BtnByDraw(new Commit());
		bCommit.setTextColor(Res.TEXT_COLOR_REVERSE);
		bCommit.setFont(new Font(Res.FONT_BOLD, Font.PLAIN, Res.FONT_SIZE_BTN));
		// bCommit.setBackgroundImage(PopDlgBase
		// .getSaasCommonImg("SaasKeyBoardPanel/commit.png"));
		bCommit.setBgColor(BgColor.RED);
		bCommit.setTextColor(Res.TEXT_COLOR_REVERSE);
		// btns.add(bCommit);
	}

	public void layout4Row5col() {
		GridBagLayoutEx gx = new GridBagLayoutEx(4, 5, insetsEx);

		gx.addComponent(b1, 0, 0, 1, 1);
		gx.addComponent(b2, 1, 0, 1, 1);
		gx.addComponent(b3, 2, 0, 1, 1);

		gx.addComponent(bRollBack, 3, 0, 2, 2);

		gx.addComponent(b4, 0, 1, 1, 1);
		gx.addComponent(b5, 1, 1, 1, 1);
		gx.addComponent(b6, 2, 1, 1, 1);

		gx.addComponent(b7, 0, 2, 1, 1);
		gx.addComponent(b8, 1, 2, 1, 1);
		gx.addComponent(b9, 2, 2, 1, 1);

		gx.addComponent(bCommit, 3, 2, 2, 2);

		gx.addComponent(bPoint, 0, 3, 1, 1);
		gx.addComponent(b0, 1, 3, 2, 1);

		gx.doLayout(this);
	}

	/**
	 * 不带确定按钮
	 */
	public void layoutNotCommit() {
		GridBagLayoutEx gx = new GridBagLayoutEx(4, 3, insetsEx);
		gx.addComponent(b1, 0, 0, 1, 1);
		gx.addComponent(b2, 1, 0, 1, 1);
		gx.addComponent(b3, 2, 0, 1, 1);

		gx.addComponent(b4, 0, 1, 1, 1);
		gx.addComponent(b5, 1, 1, 1, 1);
		gx.addComponent(b6, 2, 1, 1, 1);

		gx.addComponent(b7, 0, 2, 1, 1);
		gx.addComponent(b8, 1, 2, 1, 1);
		gx.addComponent(b9, 2, 2, 1, 1);

		b0.setBackground("SaasKeyBoardPanel/keyWidth.png");
		gx.addComponent(b0, 0, 3, 2, 1);
		gx.addComponent(bPoint, 2, 3, 1, 1);
		gx.doLayout(this);
	}

	public void setShowDianBtn(boolean showDianBtn) {
		this.showDianBtn = showDianBtn;
	}

	public boolean isShowDianBtn() {
		return showDianBtn;
	}

	public void setHideCommitBtn(boolean hideCommitBtn) {
		this.hideCommitBtn = hideCommitBtn;
	}

	public boolean isHideCommitBtn() {
		return hideCommitBtn;
	}

	public void setExAction(AbstractAction exAction) {
		this.exAction = exAction;
	}

	public AbstractAction getExAction() {
		return exAction;
	}

	public void setInsetsEx(Insets insetsEx) {
		this.insetsEx = insetsEx;
	}

	public Insets getInsetsEx() {
		return insetsEx;
	}

	public void setbRollBack(ButtonWrapper bRollBack) {
		this.bRollBack = bRollBack;
	}

	public ButtonWrapper getbRollBack() {
		return bRollBack;
	}

	/**
	 * 数字按钮，KEY为NULL时，用于清空
	 */
	private class Key extends AbstractActionAdp {
		Key(String key) {
			super(key);
			this.key = key;
		}

		@Override
		public void actionPerformedInner(ActionEvent e) {
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

	/**
	 * 清空写字板写入的内容
	 */
	private class Clear extends AbstractActionAdp {
		public Clear() {
			super("C");
		}

		@Override
		public void actionPerformedInner(ActionEvent arg0) {
			if (curTf == null) {
				return;
			}
			curTf.setText("");
		}
	}

	/**
	 * 退格键
	 */
	private class RollBack extends AbstractActionAdp {
		@Override
		public void actionPerformedInner(ActionEvent arg0) {
			if (curTf == null) {
				return;
			}
			String txt = curTf.getText();
			if (txt.length() == 0) {
				return;
			}

			curTf.setText(txt.substring(0, txt.length() - 1));
		}
	}

	private class Commit extends AbstractActionAdp {
		public Commit() {
			super("确定");
		}

		@Override
		public void actionPerformedInner(ActionEvent e) {
			if (onCommit == null) {
				return;
			}
			onCommit.actionPerformed(e);
		}
	}

	private abstract class AbstractActionAdp extends AbstractAction {
		public AbstractActionAdp() {
		}

		public AbstractActionAdp(String key) {
			super(key);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (getExAction() != null) {
				getExAction().actionPerformed(e);
			}
			actionPerformedInner(e);
		}

		public abstract void actionPerformedInner(ActionEvent e);
	}

	private DlgBase dlg;// 父类对话框
	private boolean showDianBtn = false;// 设置是否显示小数点按钮
	private boolean hideCommitBtn = false;// 设置是否隐藏确定按钮
	private AbstractAction onCommit;// 确定按钮的响应事件
	private AbstractAction exAction;// 额外的action，在按下每个按键前触发
	private Insets insetsEx = new Insets(0, 0, 0, 0);

	private Vector<BtnByDraw> btns = new Vector<BtnByDraw>();
	private JTextField curTf;

	private BtnByDraw b0;
	private BtnByDraw b1;
	private BtnByDraw b2;
	private BtnByDraw b3;
	private BtnByDraw b4;
	private BtnByDraw b5;
	private BtnByDraw b6;
	private BtnByDraw b7;
	private BtnByDraw b8;
	private BtnByDraw b9;
	private BtnByDraw bPoint;
	private BtnByDraw bClear;
	private ButtonWrapper bRollBack;
	private BtnByDraw bCommit;

	public void setbCommit(BtnByDraw bCommit) {
		this.bCommit = bCommit;
	}

	public BtnByDraw getbCommit() {
		return bCommit;
	}

	public void setbClear(BtnByDraw bClear) {
		this.bClear = bClear;
	}

	public BtnByDraw getbClear() {
		return bClear;
	}

}