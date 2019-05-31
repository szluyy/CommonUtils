package com.luyy.lib.utils;

import android.app.Application;
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
