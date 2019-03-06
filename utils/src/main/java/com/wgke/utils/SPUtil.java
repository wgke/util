package com.wgke.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;


/**
 * 首选项工具类
 *
 * @author ZL
 */
public class SPUtil {

    private final static int MODE = Context.MODE_PRIVATE;
    private final static String CONFIG_NAME = "config";

    public static boolean contain(long userId, String key) {
        SharedPreferences sp = getSharedPreferences(userId);
        return sp.contains(key);
    }

    /**
     * 根据用户进行储存
     */
    public static void putInt(long userId, String key, int putValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        Editor editor = sp.edit();
        editor.putInt(key, putValue);
        editor.commit();
    }

    /**
     * 不需要用户判断
     */
    public static void putInt(String key, int putValue) {
        SharedPreferences sp = getSharedPreferences(0);
        Editor editor = sp.edit();
        editor.putInt(key, putValue);
        editor.commit();
    }

    public static void putFloat(long userId, String key, float putValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        Editor editor = sp.edit();
        editor.putFloat(key, putValue);
        editor.commit();
    }

    public static void putFloat(String key, float putValue) {
        SharedPreferences sp = getSharedPreferences(0);
        Editor editor = sp.edit();
        editor.putFloat(key, putValue);
        editor.commit();
    }

    public static void putLong(long userId, String key, long putValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        Editor editor = sp.edit();
        editor.putLong(key, putValue);
        editor.commit();
    }

    public static void putBoolean(long userId, String key, boolean putValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        Editor edit = sp.edit();
        edit.putBoolean(key, putValue);
        edit.commit();
    }

    public static void putString(String preferenceName, long userId, String key, String putValue) {
        SharedPreferences sp = getSharedPreferences(userId, preferenceName);
        Editor editor = sp.edit();
        editor.putString(key, putValue);
        editor.commit();
    }

    public static void putString(long userId, String key, String putValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        Editor editor = sp.edit();
        editor.putString(key, putValue);
        editor.commit();
    }

    public static void putString(String key, String putValue) {
        SharedPreferences sp = getSharedPreferences(0);
        Editor editor = sp.edit();
        editor.putString(key, putValue);
        editor.commit();
    }

    public static boolean getBoolean(long userId, String key, Boolean defValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key, Boolean defValue) {
        SharedPreferences sp = getSharedPreferences(0);
        return sp.getBoolean(key, defValue);
    }

    public static int getInt(long userId, String key, int defValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        return sp.getInt(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = getSharedPreferences(0);
        return sp.getInt(key, defValue);
    }

    public static String getString(String preferenceName, long userId, String key, String defValue) {
        SharedPreferences sp = getSharedPreferences(userId, preferenceName);
        return sp.getString(key, defValue);
    }

    public static String getString(long userId, String key, String defValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        return sp.getString(key, defValue);
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = getSharedPreferences(0);
        return sp.getString(key, defValue);
    }

    public static long getLong(long userId, String key, long defValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        try {
            return sp.getLong(key, defValue);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float getFloat(Context context, long userId, String key, float defValue) {
        SharedPreferences sp = getSharedPreferences(userId);
        return sp.getFloat(key, defValue);
    }

    public static void clearAll() {
        SharedPreferences sp = getSharedPreferences(0);
        sp.edit().clear().apply();
    }

    private static SharedPreferences getSharedPreferences(long userId) {
        return getSharedPreferences(0, null);
    }

    private static SharedPreferences getSharedPreferences(long userId, String preferenceName) {
        String name = CONFIG_NAME;
        if (!TextUtils.isEmpty(preferenceName)) {
            name = preferenceName;
        }
        if (userId > 0) {
            name += userId;
        }
        return AppUtil.getContext().getSharedPreferences(name, MODE);
    }


    public final static class Key {
        public static String KEY_USER = "user";
        public static String KEY_ACCOUNT = "account";
        public static String KEY_PWD = "password";
        public static String KEY_PHONE = "phone";
        public static String KEY_NAME = "username";
    }
}
