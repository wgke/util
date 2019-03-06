package com.wgke.utils.net.callback;

import com.wgke.utils.net.bean.BaseBean;

/**
 * Created by wangke on 2018/12/14.
 */

public interface IHttpCallBack<T> {
    void onSuccess(T t) throws Exception;

    void onError(BaseBean error);
}
