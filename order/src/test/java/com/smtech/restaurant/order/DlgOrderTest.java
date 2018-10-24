package com.smtech.restaurant.order;

import com.smtech.restaurant.order.ui.DlgOrder;
import com.smtech.restaurant.order.ui.DlgStart;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgBase;
import org.junit.Test;

public class DlgOrderTest {

    @Test
    public void testRun(){
        DlgBase dlg = DlgManager.getInstance().getDlg(DlgOrder.class);
        dlg.setVisible(true);
    }

    @Test
    public void testStartOrder(){
        DlgStart dlg = (DlgStart) DlgManager.getInstance().getDlg(DlgStart.class);
        dlg.setSize(500,500);
        dlg.setVisible(true);
    }
}