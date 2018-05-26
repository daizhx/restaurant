package com.smtech.swing.common.layout;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;

public class GridLayoutEx extends GridLayout {
	
	
	
	public GridLayoutEx() {
		super();
	}

	public GridLayoutEx(int rows, int cols, int hgap, int vgap) {
		super(rows, cols, hgap, vgap);
	}

	public GridLayoutEx(int rows, int cols) {
		super(rows, cols);
	}

	public void layoutContainer(Container parent) {

		int rows = getRows();
		int cols = getColumns();
		int hgap = getHgap();
		int vgap = getVgap();

		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = rows;
			int ncols = cols;
			boolean ltr = parent.getComponentOrientation().isLeftToRight();

			if (ncomponents == 0) {
				return;
			}
			// if (nrows > 0) {
			// ncols = (ncomponents + nrows - 1) / nrows;
			// } else {
			// nrows = (ncomponents + ncols - 1) / ncols;
			// }
			// 4370316. To position components in the center we should:
			// 1. get an amount of extra space within Container
			// 2. incorporate half of that value to the left/top position
			// Note that we use trancating division for widthOnComponent
			// The reminder goes to extraWidthAvailable
			int totalGapsWidth = (ncols - 1) * hgap;
			int widthWOInsets = parent.getWidth()
					- (insets.left + insets.right);
			int widthOnComponent = (widthWOInsets - totalGapsWidth) / ncols;
			int extraWidthAvailable = (widthWOInsets - (widthOnComponent
					* ncols + totalGapsWidth)) / 2;

			int totalGapsHeight = (nrows - 1) * vgap;
			int heightWOInsets = parent.getHeight()
					- (insets.top + insets.bottom);
			int heightOnComponent = (heightWOInsets - totalGapsHeight) / nrows;
			int extraHeightAvailable = (heightWOInsets - (heightOnComponent
					* nrows + totalGapsHeight)) / 2;
			if (ltr) {

				for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows; r++, y += heightOnComponent + vgap) {
					for (int c = 0, x = insets.left + extraWidthAvailable; c < ncols; c++, x += widthOnComponent + hgap) {
						int i = r * ncols + c;
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y,
									widthOnComponent, heightOnComponent);
						}
					}
				}
			} else {
				for (int c = 0, x = (parent.getWidth() - insets.right - widthOnComponent)
						- extraWidthAvailable; c < ncols; c++, x -= widthOnComponent
						+ hgap) {
					for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows; r++, y += heightOnComponent
							+ vgap) {
						int i = r * ncols + c;
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y,
									widthOnComponent, heightOnComponent);
						}
					}
				}
			}
		}
	}

}