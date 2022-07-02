package com.wxj.springboot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DateTransform.java
 * @Description TODO
 * @createTime 2022年07月02日 22:53:00
 */
public class DateTransform {

    public static LocalDateTime strToDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse("2022-07-02 22:58:47",dateTimeFormatter);
    }
}
