package com.wgke.utils.net.callback;

public interface IUploadCallBack<T> extends IHttpCallBack<T> {
    void onProgress(int progress);
}
