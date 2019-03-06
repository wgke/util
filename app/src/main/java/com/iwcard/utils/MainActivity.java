package com.iwcard.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.wgke.utils.AppUtil;
import com.wgke.utils.ToastUtil;
import com.wgke.utils.net.HttpUtils;
import com.wgke.utils.net.bean.ReqParam;
import com.wgke.utils.net.callback.NestCallback;

import java.util.Map;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtil.setContext(getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.init().postbyHandler("http://115.238.251.230:8082/toim/im/doLogin",
                ReqParam.init().add("username", "15215608650").add("password", "123").add("logtype", "2").end(),
                new BeanCallBack<LoginBean>(this, true) {
                    @Override
                    public void success(LoginBean loginBean) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast("登录成功");
                            }
                        },2000);

                    }
                });
    }
}
