package com.smtech.restaurant.util;

public class StringUtil {

    public static boolean isNull(String s){
        if(s == null || s.equals("")){
            return true;
        }
        return false;
    }
}
