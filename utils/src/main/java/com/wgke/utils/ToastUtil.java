package com.wgke.utils;

import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {
    /**
     * toast
     */
    public static void showToast(String msg) {
        if (!TextUtils.isEmpty(msg) && AppUtil.getContext() != null) {
            Toast.makeText(AppUtil.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
