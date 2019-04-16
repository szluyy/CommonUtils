package com.luyy.commonutils;

import android.app.Application;
import com.luyy.lib.utils.ImageLoaderUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtils.init(this);
    }
}
