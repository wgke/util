package com.wgke.utils.net.callback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.wgke.utils.net.bean.BaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class NestListCallback<T> extends NestCallback<BaseBean> {
    Context context;
    boolean isShow;//是否展示loading
    private String msg;

    public NestListCallback() {

    }

    public NestListCallback(Context context) {
        this.context = context;
    }

    public NestListCallback(Context context, boolean isShow) {
        this.context = context;
        this.isShow = isShow;
        if (isShow && context != null) {
            show();
        }
    }

    @Override
    public void onSuccess(BaseBean bean) throws Exception {
        dismissProgressDialog();
        setMsg(bean != null ? bean.message : "未获取");
        String code = getCode(bean);
        if (TextUtils.equals("true", code)) {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            Class<T> clazz = (Class) params[0];
            List<T> list = JSONObject.parseArray(bean.obj, clazz);
            success(list);
        } else {
            failed(bean == null ? getBean("false", "服务君没有给消息") : bean);
        }
    }

    @Override
    public void success(@NonNull BaseBean baseResponseBean) {

    }

    public String getCode(BaseBean bean) {
        if (bean != null && TextUtils.equals("true", bean.success)) {
            return "true";
        } else if (bean != null)
            return bean.success;
        return "false";
    }

    @Override
    public void onError(BaseBean ex) {
        dismissProgressDialog();
        failed(getBean("false", ex.message));
    }

    public abstract void success(List<T> list);

    /**
     * 失败返回
     */
    public void failed(BaseBean bean) {
        if (context != null) {
            Toast.makeText(context, bean.message, Toast.LENGTH_SHORT).show();
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
        bean.success = code;
        bean.message = msg;
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
