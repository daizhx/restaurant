package com.smtech.restaurant.app;


import javax.swing.*;

public class RestaurantSystem {

    public static void main(String[] args) {

        //1,start server


        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });


    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setUndecorated(true);//去掉了窗口的系统装饰边框
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * 设置界面风格
     */
    private static void setLookAndFeel() {
        // 解决JAVA输入文本框出现提示框的问题
        System.setProperty("java.awt.im.style", "on-the-spot");
        // 防止激活输入法时白屏
        System.setProperty("sun.java2d.noddraw", "true");

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
//            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
//            logger.error(StackTraceToString.getExceptionTrace(e));
        }

//        logger.error("Set the style of Graphical User Interface success");
    }
}
