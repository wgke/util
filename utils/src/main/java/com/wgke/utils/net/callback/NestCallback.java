package com.wgke.utils.net.callback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.wgke.utils.JsonUtil;
import com.wgke.utils.net.bean.BaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 解析封装基类
 */
public abstract class NestCallback<T> implements IHttpCallBack<BaseBean> {
    public Context context;
    public boolean isShow;//是否展示loading
    private String msg;

    public NestCallback() {

    }

    public NestCallback(Context context) {
        this.context = context;
    }

    public NestCallback(Context context, boolean isShow) {
        this.context = context;
        this.isShow = isShow;
        if (isShow && context != null) {
            show();
        }
    }

    public void show() {

    }

    public void diss() {

    }

    @Override
    public void onSuccess(BaseBean bean) throws Exception {
        dismissProgressDialog();
        setMsg(bean != null ? bean.msg : "未得道信息");
        String code = getCode(bean);
        if (TextUtils.equals("0", code)) {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            Class<T> clazz = (Class) params[0];
            T t = JsonUtil.toBean(bean.data, clazz);
            if (t != null) {
                success(t);
            } else success(null);
        } else {
            failed(bean == null ? getBean(code, "服务君没有给消息") : bean);
        }
    }

    public String getCode(BaseBean bean) {
        if (bean != null && TextUtils.equals("0", bean.code)) {
            return "0";
        } else if (bean != null)
            return bean.code;
        return "-1";
    }

    @Override
    public void onError(BaseBean ex) {
        dismissProgressDialog();
        failed(ex);
    }

    /**
     * 成功返回
     */
    public abstract void success(@NonNull T t);

    /**
     * 失败返回
     */
    public void failed(BaseBean bean) {
        if (context != null) {
            Toast.makeText(context, bean.msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭对话框
     */
    public void dismissProgressDialog() {
        if (isShow && context != null) {
            diss();
        }
    }

    /**
     * 获取数据信息
     */
    public BaseBean getBean(String code, String msg) {
        BaseBean bean = new BaseBean();
        bean.code = code;
        bean.msg = msg;
        setMsg(msg);
        return bean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
