package com.smtech.restaurant.casher;

import com.smtech.restaurant.casher.dlg.DlgWelcome;
import com.smtech.swing.common.DlgManager;
import com.smtech.swing.common.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * Created by daizhx on 2018/3/25.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));//系统的classpaht路径
        //启动服务进程
//        try {
//            Process process = Runtime.getRuntime().exec("java -jar server-1.0-SNAPSHOT.jar");
//            // 打印程序输出
//            readProcessOutput(process);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                createAndShowGUI();
                DlgWelcome dlg = (DlgWelcome) DlgManager.getInstance().getDlg(DlgWelcome.class);
                dlg.setVisible(true);
            }
        });
    }

    private static void test(){
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static void createAndShowGUI() {
        //打开窗口
        MainFrame frame = MainFrame.getInstance();
        frame.setContentPane(crtContent());
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static Container crtContent() {

        JPanel p = new JPanel(new BorderLayout());

        JLabel label = new JLabel("welcome!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("-------------->");
                DlgWelcome dlg = (DlgWelcome) DlgManager.getInstance().getDlg(DlgWelcome.class);
                dlg.setVisible(true);
//                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Eggs are not supposed to be green.");
            }
        });
        p.add(label,BorderLayout.CENTER);
        return p;
    }


    /**
     * 打印进程输出
     *
     * @param process 进程
     */
    private static void readProcessOutput(final Process process) {
        // 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印
        read(process.getInputStream(), System.out);
//        read(process.getErrorStream(), System.err);
    }

    // 读取输入流
    private static void read(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
