package com.smtech.swing.common.view;

import javax.swing.*;
import java.awt.*;

/**
 * 透明视图
 */
public class TransparentView extends ViewGroup {

	public TransparentView() {
		setOpaque(false);
	}

	public TransparentView(LayoutManager l) {
		setOpaque(false);
	}
	
	public void setPadding(int top,int left,int bottom,int right){
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
}
