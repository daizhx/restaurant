package com.smtech.swing.common;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.dlgs.DlgBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

//
public class DlgManager {
    private static Logger logger = LoggerFactory.getLogger(DlgManager.class);

    private static DlgManager instance = new DlgManager();

    private Map<String, DlgBase> dlgs = new HashMap<String, DlgBase>();

    private DlgManager(){
    }

    public static DlgManager getInstance(){
        return instance;
    }

    //调用该方法的，线程编程UI线程
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
            logger.error(StackTraceToString.getExceptionTrace(e));
            return null;
        }

    }

}
