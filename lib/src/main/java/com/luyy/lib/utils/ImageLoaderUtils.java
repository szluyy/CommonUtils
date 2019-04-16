package com.luyy.lib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.luyy.lib.ImageLoader.GlideLoader;
import com.luyy.lib.ImageLoader.ILoader;

public class ImageLoaderUtils {
    private static volatile ILoader loader;
    public static void init(Application application){
        if(loader==null){
            loader=new GlideLoader();
            loader.init(application);
        }
    }

    public static void load(String url,ImageView image){
        loader.request(url,image);
    }
}
