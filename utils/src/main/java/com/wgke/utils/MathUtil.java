package com.wgke.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MathUtil {


    public static String formatDecimal(long decimals) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(decimals);
    }

    public static String formatDecimal(double decimals) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(decimals);
    }

    public static String formatDecimal(float decimals) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(decimals);
    }

    /**
     * 取小数点后两位
     *
     * @param decimals
     * @return
     */
    public static String formatDecimal2(float decimals) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimals);
    }

    public static String formatDecimal2(double decimals) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimals);
    }

    public static String formatDecimal2(long decimals) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimals);

    }

    public static String formatDecimal2(int decimals) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimals);
    }

    /**
     * 。00取整数
     */
    public static String formatInt(String price) {
        if (price == null || price.length() <= 3)
            return price;
        if (price.contains(".00"))
            return price.replace(".00", "");
        if (price.contains(".0"))
            return price.replace(".0", "");
        return price;
    }

    /**
     * 将小数格式化成百分数,并保留3位小数
     *
     * @param decimals
     * @return
     */
    public static String formatDecimalPercent3(float decimals) {
        DecimalFormat df = new DecimalFormat("0.000%");
        return df.format(decimals / 100);

    }

    /**
     * 将小数格式化成百分数,并保留2位小数
     *
     * @param decimals
     * @return
     */
    public static String formatDecimalPercent2(float decimals) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(decimals);

    }

    /**
     * 保留小数点后4位
     *
     * @param decimals
     * @return
     */
    public static String formatDecimal4(float decimals) {
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(decimals);

    }

    /**
     * 四舍五入
     */
    public static int round(float decimal) {
        return (int) (decimal + 0.5f);
    }


    /**
     * 处理过长的数
     *
     * @param digit
     * @return
     */
    public static String processLongDigit(double digit) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        return format.format(digit);
    }


    /**
     * 计算百分比
     *
     * @param balance
     * @param totalBalance
     * @return
     */
    public static String calculatePercent(float balance, float totalBalance) {
        DecimalFormat format = new DecimalFormat("##0.00%");
        return format.format(balance / totalBalance);
    }

}
