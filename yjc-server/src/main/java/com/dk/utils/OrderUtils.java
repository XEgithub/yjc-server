package com.dk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName OrderUtils
 * @Description
 * @Author zyf
 * @Date 2018/12/17 17:39
 **/
public class OrderUtils {

    public static String getOrderNo() {
        StringBuffer sb = new StringBuffer();
        int no1 = (int)((Math.random() * 9 + 1) * 10);
        sb.append(no1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = sdf.format(new Date());
        sb.append(string);
        int no2 = (int)((Math.random() * 9 + 1) * 10);
        sb.append(no2);
        return sb.toString();
    }


}
