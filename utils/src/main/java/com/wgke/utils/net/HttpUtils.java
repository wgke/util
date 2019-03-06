package com.wgke.utils.net;

import com.wgke.utils.net.bean.BaseBean;
import com.wgke.utils.net.callback.IHttpCallBack;
import com.wgke.utils.net.callback.IUploadCallBack;
import com.wgke.utils.net.callback.NestCallback;
import com.wgke.utils.net.callback.NestListCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.internal.Utils;

/**
 * Created by wangke on 2018/12/14.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;

    public HttpUtils() {
    }

    public static synchronized HttpUtils init() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }


    /**
     * post氢气调用
     */
    public <T> void postbyHandler(final String url, Map<String, String> data, final Class<T> clazz, final IHttpCallBack<T> callBack) {
        HttpEngine.init().post(url, data, OkGoManager.getInstance().getHeaderMap(), clazz, new IHttpCallBack<T>() {
            @Override
            public void onSuccess(T t) throws Exception {
                if (callBack != null) {
                    try {
                        callBack.onSuccess(t);
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(e.getMessage(), 0));
                    }
                } else {
                    callBack.onSuccess(t);
                }

            }


            @Override
            public void onError(BaseBean ex) {
                if (callBack != null)
                    callBack.onError(ex);
            }

        });
    }

    public <T> void postbyHandler(final String url, Map<String, String> data, final IHttpCallBack<T> callBack) {
        HttpEngine.init().post(url, data, OkGoManager.getInstance().getHeaderMap(), BaseBean.class, new IHttpCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseResponseBean) throws Exception {
                if (callBack == null)
                    return;
                if (callBack instanceof NestCallback) {
                    ((NestCallback) callBack).onSuccess(baseResponseBean);
                } else if (callBack instanceof NestListCallback) {
                    ((NestListCallback) callBack).onSuccess(baseResponseBean);
                }
            }

            @Override
            public void onError(BaseBean ex) {
                if (callBack != null)
                    callBack.onError(ex);
            }

        });
    }

    /**
     * get
     */
    public <T> void getbyHandler(final String url, Map<String, String> data, final Class<T> clazz, final IHttpCallBack<T> callBack) {
        HttpEngine.init().get(url, data, clazz, new IHttpCallBack<T>() {
            @Override
            public void onSuccess(T t) {
                if (callBack != null) {
                    try {
                        callBack.onSuccess(t);
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(e.getMessage(), 0));
                    }
                }

            }

            @Override
            public void onError(BaseBean ex) {
                if (callBack != null)
                    callBack.onError(ex);
            }

        });
    }

    /**
     * 上传文件
     */
    public <T> void upbyHandler(final String url, File file, Map<String, String> map, final Class<T> clazz, final IUploadCallBack<T> callBack) {
        HttpEngine.init().uploadFiles(url, file, map, clazz, new IUploadCallBack<T>() {
            @Override
            public void onProgress(int progress) {
                if (callBack != null)
                    callBack.onProgress(progress);
            }

            @Override
            public void onSuccess(T t) {
                if (callBack != null) {
                    try {
                        callBack.onSuccess(t);
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(e.getMessage(), 0));
                    }
                }
            }

            @Override
            public void onError(BaseBean ex) {
                if (callBack != null)
                    callBack.onError(ex);
            }
        });
    }

    public String downloadFile(String url, String pathFile, final IUploadCallBack<String> callBack) {
        //创建文件夹
        String name = url.substring(url.lastIndexOf("/", url.length()));
        HttpEngine.init().downloadFile(url, pathFile, name, new IUploadCallBack<String>() {
            @Override
            public void onProgress(int progress) {
                if (callBack != null) callBack.onProgress(progress);
            }

            @Override
            public void onSuccess(String fileBean) throws Exception {
                if (callBack != null) callBack.onSuccess(fileBean);
            }

            @Override
            public void onError(BaseBean e) {
                if (callBack != null) callBack.onError(e);
            }
        });
        return "";
    }
}