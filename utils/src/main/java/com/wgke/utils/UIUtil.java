package com.wgke.utils;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;

public class UIUtil {
    /**
     * 设置view的大小高度
     */
    public static void setLayoutWH(View view, int w, int h) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (w != 0)
            params.width = w;
        if (h != 0)
            params.height = (int) h;
        view.setLayoutParams(params);
    }

    public static void setLayoutWH(View view, int w, float h) {
        setLayoutWH(view, w, (int) h);
    }

    //dip--->px   1dp = 1px   1dp = 2px
    public static int dip2px(Context context, int dip) {
        //dp和px的转换关系比例
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    //px---->dp
    public static int px2dip(Context context, int px) {
        //dp和px的转换关系比例
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    /**
     * 设置边距
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 计算高度
     */
    public static int getViewHeight(Context context, int w, int h) {
        float width = getDisplayWidth(context);
        return (int) (width * h / w);
    }

    public static int getViewHeight(Context context, int marginLR, int w, int h) {
        float width = getDisplayWidth(context) - dip2px(context, marginLR);
        return (int) (width * h / w);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wManager.getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStateBarHeight(Context context) {
        Class clazz;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            Field field = clazz.getField("status_bar_height");
            Object object = clazz.newInstance();
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelOffset(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 划线
     */
    public static void setTextStrike(TextView textView) {
        textView.getPaint().setAntiAlias(true);//抗锯齿
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }


    /**
     * 设置控件大小
     */
    public static void setLayoutParams(View v, int w, int h) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (w != 0)
            layoutParams.width = w;
        if (h != 0)
            layoutParams.height = h;
        v.setLayoutParams(layoutParams);
    }
}
