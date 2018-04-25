package com.smtech.restaurant.order;

import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.dlgs.DlgBase;
import org.junit.Test;

public class DlgOrderTest {

    @Test
    public void testRun(){
        DlgBase dlg = DlgManager.getInstance().getDlg(DlgOrder.class);
        dlg.setVisible(true);
    }
}