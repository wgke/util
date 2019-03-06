package com.iwcard.utils;

public class LoginBean {
    public String id;
    public String avatarUrl;
    public String gender;
    public String mobile;
    public String code;
    public String userName;
    public String passWord;
    public String nickName;
    public String realName;
    public String online;
    public String enableStatus;
    public String createTime;
    public String sign;

    public String getGender() {
        return "1".equals(gender) ? "男" : "2".equals(gender) ? "女" : "0".equals(gender) ? "" : gender;
    }
}
