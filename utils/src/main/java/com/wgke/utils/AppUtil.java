package com.wgke.utils;

import android.app.Application;
import android.content.Context;

import com.wgke.utils.net.OkGoManager;

public class AppUtil {
    private static Context context;

    public static void setContext(Application application) {
        context = application;
        OkGoManager.getInstance().init(application);
    }

    public static Context getContext() {
        return context;
    }
}
