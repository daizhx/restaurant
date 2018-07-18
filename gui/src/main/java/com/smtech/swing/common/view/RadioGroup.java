package com.smtech.swing.common.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RadioGroup extends TransparentView {
	
	private int selectedIndex = -1;
	
	private OnSelectedListener listener;
	
	public interface OnSelectedListener{
		void onSelected(int index);
	}
	
	private MouseAdapter adapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Component c = e.getComponent();
			if(c == null){
				return;
			}
			SelectedAble sa;
			if(c instanceof SelectedAble){
				sa = (SelectedAble) c;
			}else if(c.getParent() instanceof SelectedAble){
				sa = (SelectedAble) c.getParent();
			}else{
				return;
			}
			SelectedAble preSel = (SelectedAble) getComponent(selectedIndex);
			if(preSel == sa){
				return;
			}
			preSel.setSelected(false);
			sa.setSelected(true);
			
			selectedIndex = getComponentZOrder((Component)sa);
			if(listener != null){
				listener.onSelected(selectedIndex);
			}
		}
	};

	public RadioGroup() {
		super();
	}

	public RadioGroup(LayoutManager l) {
		super(l);
	}

	
	@Override
	protected void addImpl(Component comp, Object constraints, int index) {
		super.addImpl(comp, constraints, index);
		if(getComponentCount() == 1 && comp instanceof SelectedAble){
			((SelectedAble)comp).setSelected(true);
			selectedIndex = 0;
		}
		comp.addMouseListener(adapter);
	}

	public OnSelectedListener getOnSelectedListener() {
		return listener;
	}

	public void setOnSelectedListener(OnSelectedListener listener) {
		this.listener = listener;
	}

	//获取当前选中的的控件index
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
}
