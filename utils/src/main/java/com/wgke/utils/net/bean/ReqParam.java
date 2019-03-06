package com.wgke.utils.net.bean;

import java.util.HashMap;
import java.util.Map;

public class ReqParam {
    Map<String, String> map = new HashMap<>();

    public static ReqParam init() {
        return new ReqParam();
    }

    public ReqParam add(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Map<String, String> end() {
        return map;
    }
}
