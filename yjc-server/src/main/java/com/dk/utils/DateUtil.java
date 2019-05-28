package com.dk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     *
     * 功能描述: 日期字符串转成时间戳字符串
     *
     * @auther: my
     * @Date: 2018/7/30
     * @Param:
     * @return:
     */
    public static String string2Stamp(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s);
        return date.getTime() + "";
    }

}
