package com.iwcard.utils;

import android.content.Context;

import com.wgke.utils.ToastUtil;
import com.wgke.utils.net.callback.NestCallback;

public abstract class BeanCallBack<T> extends NestCallback<T> {

    public BeanCallBack(Context context, boolean isShow) {
        this.context = context;
        this.isShow = isShow;
        if (isShow && context != null) {
            show();
        }
    }

    @Override
    public void show() {
        ToastUtil.showToast("请求开始");
    }

    @Override
    public void diss() {
        ToastUtil.showToast("请求结束");
    }
}
