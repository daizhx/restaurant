package com.smtech.swing.common;

import com.smtech.swing.common.dlgs.DlgBase;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DlgManager {

    private static DlgManager instance = new DlgManager();

    private Map<String, DlgBase> dlgs = new HashMap<String, DlgBase>();

    private DlgManager(){
    }

    public static DlgManager getInstance(){
        return instance;
    }

    public synchronized DlgBase getDlg(Class dlgClass) {
        String className = dlgClass.getName();
        if (dlgs.containsKey(className)) {
            return dlgs.get(className);
        }

        try {
            Constructor constructor = dlgClass
                    .getDeclaredConstructor(Window.class);
            DlgBase dlg = (DlgBase) constructor.newInstance(MainFrame.getInstance());
            dlgs.put(className, dlg);
            return dlg;
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return null;
        }

    }

}
