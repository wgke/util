package com.wgke.utils.net;

import android.provider.SyncStateContract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.wgke.utils.LogUtils;
import com.wgke.utils.net.bean.BaseBean;
import com.wgke.utils.net.callback.IHttpCallBack;
import com.wgke.utils.net.callback.IUploadCallBack;

import java.io.File;
import java.util.Map;

import okhttp3.Response;

public class HttpEngine {
    private static HttpEngine httpEngine;

    public HttpEngine() {
    }

    public static HttpEngine init() {
        if (httpEngine == null) {
            httpEngine = new HttpEngine();
        }
        return httpEngine;
    }

    protected <T> void post(final String url, Map<String, String> map, Map<String, String> headMap, final Class<T> clazz, final IHttpCallBack<T> callBack) {
        HttpHeaders headers = new HttpHeaders();
        if (headMap != null)
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        HttpParams params = new HttpParams();
        if (map != null)
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        OkGo.getInstance().<T>post(url).params(params).tag(this).headers(headers).execute(new AbsCallback<T>() {
            @Override
            public T convertResponse(Response response) throws Throwable {
                int code = response.code();//请求成功
                if (code == 200) {
                    try {
                        String json = response.body().string();
                        LogUtils.response(json);
                        JSONObject jsonObject = JSON.parseObject(json);
                        int code1 = jsonObject.getIntValue("code");
                        if (code1 == 0) {
                            LogUtils.response("code=" + code1);
                            return JSONObject.parseObject(json, clazz);
                        } else {
                            String msg = jsonObject.getString("msg");
                            BaseBean baseRes = new BaseBean(msg, code1 + "");
                            return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                        }
                    } catch (Exception e) {
                        BaseBean baseRes = new BaseBean("网络连接失败！错误代码：-1", "-1");
                        return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                    }
                } else {
                    BaseBean baseRes = new BaseBean("网络连接失败！错误代码：-1", "-1");
                    return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                }
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<T> response) {
                if (callBack != null)
                    try {
                        callBack.onSuccess(response.body());
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(response.message(),-1));
                    }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<T> response) {
                if (callBack != null) {
                    callBack.onError(new BaseBean(response.message(),-1));
                }

            }

            @Override
            public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
                super.onCacheSuccess(response);
                onSuccess(response);
            }
        });
    }

    protected <T> void get(final String url, Map<String, String> map, final Class<T> clazz, final IHttpCallBack<T> callBack) {
        HttpParams params = new HttpParams();
        if (map != null)
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        Map<String, String> header = OkGoManager.getInstance().getHeaderMap();
        HttpHeaders headers = new HttpHeaders();
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }
        OkGo.getInstance().<T>get(url).tag(this).params(params).headers(headers).cacheKey(url).cacheTime(1 * 60 * 60 * 1000 * 24 * 7).execute(new AbsCallback<T>() {
            @Override
            public T convertResponse(Response response) throws Throwable {
                int code = response.code();//请求成功
                if (code == 200) {
                    try {
                        String json = response.body().string();
                        LogUtils.response(json);
                        JSONObject jsonObject = JSON.parseObject(json);
                        int code1 = jsonObject.getIntValue("code");
                        if (code1 == 0) {
                            return JSONObject.parseObject(json, clazz);
                        } else {
                            String msg = jsonObject.getString("msg");
                            BaseBean baseRes = new BaseBean(msg, code1);
                            return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                        }
                    } catch (Exception e) {
                        BaseBean baseRes = new BaseBean("网络连接失败！错误代码：-1", 0);
                        return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                    }
                } else {
                    BaseBean baseRes = new BaseBean("网络连接失败！错误代码：-1", 0);
                    return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                }
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<T> response) {
                if (callBack != null)
                    try {
                        callBack.onSuccess(response.body());
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(response.message(),-1));
                    }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<T> response) {
                if (callBack != null) {
                    callBack.onError(new BaseBean(response.message(),-1));
                }
            }

            @Override
            public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
                super.onCacheSuccess(response);
                onSuccess(response);
            }
        });
    }

    protected <T> void uploadFiles(String url, File files, Map<String, String> map, final Class<T> clazz, final IUploadCallBack<T> callBack) {
        HttpParams params = new HttpParams();
        if (map != null)
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        Map<String, String> header = OkGoManager.getInstance().getHeaderMap();
        HttpHeaders headers = new HttpHeaders();
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }
        OkGo.getInstance().<T>post(url).params("file", files).params(params).headers(headers).tag(this).execute(new AbsCallback<T>() {
            @Override
            public T convertResponse(Response response) throws Throwable {

                int code = response.code();//请求成功
                if (code == 200) {
                    String json = response.body().string();
                    try {
                        LogUtils.response(json);
                        return JSONObject.parseObject(json, clazz);
                    } catch (Exception e) {
                        JSONObject jsonObject = JSON.parseObject(json);
                        String msg = jsonObject.getString("msg");
                        int code1 = jsonObject.getIntValue("code");
                        BaseBean baseRes = new BaseBean(msg, code1);
                        return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                    }
                } else {
                    BaseBean baseRes = new BaseBean("网络连接失败！错误代码：-1", 0);
                    return JSONObject.parseObject(JSON.toJSONString(baseRes), clazz);
                }
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<T> response) {
                if (callBack != null)
                    try {
                        callBack.onSuccess(response.body());
                    } catch (Exception e) {
                        callBack.onError(new BaseBean(response.message(), -1));
                    }
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                if (callBack != null)
                    callBack.onProgress((int) (progress.fraction * 100));
            }
        });
    }

    protected void downloadFile(String url, final String destFileDir, final String destFileName, final IUploadCallBack<String> callBack) {
        GetRequest<File> request = OkGo.<File>get(url);
        OkDownload.request(url, request).priority(1).fileName(destFileName).folder(destFileDir).save().register(new DownloadListener(url) {
            @Override
            public void onStart(Progress progress) {
                LogUtils.e("下载开始");
                if (callBack != null)
                    callBack.onProgress(0);
            }

            @Override
            public void onProgress(Progress progress) {
                LogUtils.e("下载中...");
                if (callBack != null)
                    callBack.onProgress((int) (progress.fraction * 100));
            }

            @Override
            public void onError(Progress progress) {
                LogUtils.e("下载中错误");
                if (callBack != null)
                    callBack.onError(new BaseBean("下载中错误", -1));
            }

            @Override
            public void onFinish(File file, Progress progress) {
                LogUtils.e("完成");
                if (callBack != null) {
                    try {
                        callBack.onSuccess(file.getAbsolutePath());
                    } catch (Exception e) {
                        callBack.onError(new BaseBean("完成", -1));
                    }
                }
            }

            @Override
            public void onRemove(Progress progress) {
                LogUtils.e("取消");
                if (callBack != null)
                    callBack.onProgress(-1);
            }
        }).start();
    }
}
