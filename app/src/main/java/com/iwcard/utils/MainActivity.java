package com.iwcard.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.wgke.utils.AppUtil;
import com.wgke.utils.GlideApp;
import com.wgke.utils.ToastUtil;
import com.wgke.utils.net.HttpUtils;
import com.wgke.utils.net.bean.ReqParam;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        setContentView(imageView);
        GlideApp.with(this).load(R.drawable.ic_launcher_background).into(imageView);
        AppUtil.setContext(getApplication(), true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.init().postbyHandler("https://learn-quantum.com/doLogin.json",
                ReqParam.init().add("email", "wangke0802@qq.com").add("password", "e10adc3949ba59abbe56e057f20f883e").end(),
                new BeanCallBack<LoginBean>(this, true) {
                    @Override
                    public void success(LoginBean loginBean) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast("登录成功");
                            }
                        }, 2000);

                    }
                });
    }
}
