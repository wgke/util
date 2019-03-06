package com.iwcard.utils;

import android.app.Activity;
import android.os.Bundle;

import com.wgke.utils.AppUtil;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtil.setContext(getApplication());
    }
}
