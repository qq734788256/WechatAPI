package com.wx.base.util;

/**
 * Created by wangyunlong on 2017/8/23.
 */
public class OrderUtil {

    public static String getOrderNumber(){
        String prefix = String.valueOf((int)(Math.random() * 100000));
        while (prefix.length() < 5){
            prefix = "0" + prefix;
        }
        return "H" + SysTimeUitl.getSystemTime() + prefix;
    }
}
