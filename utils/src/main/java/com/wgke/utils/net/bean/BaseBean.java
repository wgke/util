package com.wgke.utils.net.bean;

public class BaseBean {
    public String code;//错误码
    public String status;//状态码
    public String data;//主要数据
    public String msg;//基本信息

    public BaseBean(String msg, String code) {
        this.code = code;
        this.msg = msg;
    }

    public BaseBean(String msg, int code) {
        this.code = code + "";
        this.msg = msg;
    }

    public BaseBean() {

    }
}
