package com.smtech.restaurant.util;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * 文件操作相关工具类
 */
public class FileUtil {

    /**
     * 创建文件夹
     *
     * @param dirName
     */
    public static void makeDir(String dirName) {
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
    }

    //删除文件
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }


    /**
     * 文件过滤器
     *
     * @param msg
     * @return
     */
    public static FileFilter crtImgFileFilter(String msg) {
        msg += "(*.png, *.gif, *.jpg, *.jpeg, *.bmp)";
        return new FileNameExtensionFilter(msg, "png", "gif", "jpg", "jpeg",
                "bmp");
    }
}
