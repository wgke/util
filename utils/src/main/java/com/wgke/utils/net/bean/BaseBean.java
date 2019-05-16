package com.wgke.utils.net.bean;

public class BaseBean {
    public String success;//错误码
    public String obj;//主要数据
    public String message;//报错信息
    //public String execptionTrace;//服务器错误信息，用于服务器内部错误信息提示，APP不使用

    public BaseBean(String msg, String success) {
        this.success = success;
        this.message = msg;
    }

    public BaseBean() {

    }
}
