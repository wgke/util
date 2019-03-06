package com.wgke.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public class JsonUtil {


    /**
     * obj->String
     */
    public static String toString(Object object) {
        String gsonString = null;
        if (object != null) {
            if (object instanceof String) {
                return object.toString();
            } else
                gsonString = JSON.toJSONString(object, true);
        }
        return gsonString;
    }

    /**
     * Map->String
     */
    public static String toString(Map<String, String> map) {
        return JSON.toJSONString(map, true);
    }

    /**
     * Map->String
     */
    public static Map<String, String> toMap(String json) {
        return (Map<String, String>) JSON.parse(json);
    }

    /**
     * String->T
     */
    public static <T> T toBean(String json, Class<T> cls) {
        T t = JSON.parseObject(json, cls);//传入json对象和对象类型,将json转成对象
        return t;
    }

    /**
     * String->List
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        List<T> list = JSON.parseArray(json, cls);//根据泛型返回解析指定的类型
        return list;
    }

    /**
     * String->JSONObject
     */
    public static JSONObject toJSONObj(String json) {
        return JSON.parseObject(json);
    }

    /**
     * key->value
     */
    public static String toJSONObj(String key, JSONObject object) {
        return object.getString(key);
    }

    /**
     * map->JSONObject
     */
    public static JSONObject mapToJsonobj(Map<String, Object> map) {
        return JSONObject.parseObject(JSON.toJSONString(map));
    }

    /**
     * json -> key to value
     */
    public static String parseJson(String key, String json) {
        if (TextUtils.isEmpty(json))
            return "";
        JSONObject jo = JSONObject.parseObject(json);
        return jo.getString(key);
    }

    /**
     * json -> key to int
     */
    public static int parseJsonToInt(String key, String json) {
        String result = parseJson(key, json);
        try {
            int num = Integer.parseInt(result);
            return num;
        } catch (Exception e) {
            return 0;
        }
    }

}
