package com.smtech.restaurant.util;

import java.io.File;

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
}
