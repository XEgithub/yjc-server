package com.dk.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Date2 {
    public static void main(String[] args) {
        LocalDateTime  today = LocalDateTime.now();
        LocalDateTime  tomorrow = today.plusDays(1);
        // 今天是几号
        int dayofMonth = today.getDayOfMonth();
        // 今天是周几（返回的是个枚举类型，需要再getValue()）
        int dayofWeek = today.getDayOfWeek().getValue();
        // 今年是哪一年
        int dayofYear = today.getDayOfYear();

        // 根据字符串取：
        LocalDate endOfFeb = LocalDate.parse("2018-02-28");
        System.out.println(today.plusDays(1));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(today.toString(), formatter);
        System.out.println("dateTime:" +tomorrow.format(formatter));

        System.out.println(new Date().getTime());
        System.out.println(today.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
