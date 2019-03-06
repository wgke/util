package com.wgke.utils.net;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okserver.OkDownload;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by wangke on 2018/12/14.
 */

public class OkGoManager {
    private static final OkGoManager instance = new OkGoManager();
    private static Map<String, String> map;


    private OkGoManager() {
    }

    public static OkGoManager getInstance() {
        return instance;
    }

    public void init(Application context) {
        //okGo 初始化
        OkHttpClient okHttpClient = OkGo.getInstance().getOkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(new NetCheckInterceptor(context))
                .build();
        OkGo.getInstance().init(context).setOkHttpClient(okHttpClient).setRetryCount(1).setCacheTime(60 * 60 * 24 * 3);

        //下载初始化
        OkDownload okDownload = OkDownload.getInstance();
        okDownload.getThreadPool().setCorePoolSize(3);//设置同时下载数量
    }

    public void setRequest(String key, String value) {
        map.put(key, value);
    }

    public Map getHeaderMap() {
        return map;
    }

    class NetCheckInterceptor implements Interceptor {

        private Context context;

        NetCheckInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    }
}
