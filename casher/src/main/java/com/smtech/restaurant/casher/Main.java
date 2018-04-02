package com.smtech.restaurant.casher;

import com.smtech.swing.common.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by daizhx on 2018/3/25.
 */
public class Main {
    public static void main(String[] args){

        System.out.println(System.getProperty("user.dir"));//系统的classpaht路径
        //启动服务进程
        try {
            Process process = Runtime.getRuntime().exec("java -jar server-1.0-SNAPSHOT.jar");
            // 打印程序输出
            readProcessOutput(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //启动主界面
        MainFrame frame = MainFrame.getInstance();
        frame.setContentPane(new JPanel());
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);
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
