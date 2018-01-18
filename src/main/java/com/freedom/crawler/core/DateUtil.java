package com.freedom.crawler.core;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Thor on 2018/1/18.
 */
public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date类型转字符串类型，使用默认格式
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_FORMAT);
    }

    /**
     * Date类型转字符串类型，使用指定格式
     *
     * @param date   date
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String dateToString(Date date, String format) {
        if (null == date) {
            throw new NullPointerException();
        }

        if (StringUtils.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        DateTime dateTime = new DateTime(date);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);

        return dateTime.toString(formatter);
    }

    /**
     * 时间字符串转date，使用默认格式
     *
     * @param date 时间字符串
     * @return date
     */
    public static Date stringToDate(String date) {
        return stringToDate(date, DEFAULT_FORMAT);
    }

    /**
     * 时间字符串转date，使用指定格式
     *
     * @param date   时间字符串
     * @param format 指定格式
     * @return date
     */
    public static Date stringToDate(String date, String format) {
        if (null == date) {
            throw new NullPointerException();
        }

        if (StringUtils.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = DateTime.parse(date, formatter);

        return dateTime.toDate();
    }

    public static Date timestampToDate(Long timestamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return format.parse(format.format(timestamp));
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(timestampToDate(1196352000000L));
    }
}
