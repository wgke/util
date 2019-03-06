package com.wgke.utils;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static final long ONE_DAY_MICROSECONDS = 1000l * 60 * 60 * 24;

    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss2 = "yyyy.MM.dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_2 = "yyyy/MM/dd";
    public static final String yyyy_MM_dd_3 = "yyyy年MM月dd日";
    public static final String yyyy_MM_dd_4 = "yyyy.MM.dd";
    public static final String MM_dd = "MM月dd日";
    public static final String MM_dd_2 = "MM.dd";
    public static final String MM_dd_3 = "MM-dd";
    public static final String HH_mm = "HH:mm";
    public static final String HH_mm2 = "HH小时mm分";

    public static String formatTime(long time, String formatStr) {

        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date(time));
    }

    public static long parseTime(String time, String formatStr) {
        if (TextUtils.isEmpty(time) || TextUtils.isEmpty(formatStr)) {
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取现在的年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.YEAR);
    }

    /**
     * 获取现在的年份
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取现在的年份
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }
}
