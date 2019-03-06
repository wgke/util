package com.wgke.utils;

import android.app.Application;
import android.content.Context;

public class AppUtil {
    private static Context context;

    public static void setContext(Application application) {
        context = application;
    }

    public static Context getContext() {
        return context;
    }
}
