package com.wgke.utils.net.callback;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.wgke.utils.net.bean.BaseBean;

public abstract class JsonCallback implements IHttpCallBack<BaseBean> {
    public Context context;
    public boolean isShow;//是否展示loading
    private java.lang.String msg;

    public JsonCallback() {

    }

    public JsonCallback(Context context) {
        this.context = context;
    }

    public JsonCallback(Context context, boolean isShow) {
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
        setMsg(bean != null ? bean.message : "未获取信息");
        java.lang.String code = getCode(bean);
        if (TextUtils.equals("true", code)) {
            String str = TextUtils.isEmpty(bean.obj) ? "" : bean.obj;
            success(str);
        } else {
            failed(bean == null ? getBean(code, "服务君没有给消息") : bean);
        }
    }

    /**
     * 0表示正确返回
     */
    public java.lang.String getCode(BaseBean bean) {
        if (bean != null && TextUtils.equals("true", bean.success)) {
            return "true";
        } else if (bean != null)
            return bean.success;
        return "false";
    }

    @Override
    public void onError(BaseBean ex) {
        dismissProgressDialog();
        failed(ex);
    }

    /**
     * 成功返回
     */
    public abstract void success(String t);

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
    public BaseBean getBean(java.lang.String code, java.lang.String msg) {
        BaseBean bean = new BaseBean();
        bean.success = code;
        bean.message = msg;
        setMsg(msg);
        return bean;
    }

    public java.lang.String getMsg() {
        return msg;
    }

    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }
}
