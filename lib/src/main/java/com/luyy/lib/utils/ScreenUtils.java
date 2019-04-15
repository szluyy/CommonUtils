package com.luyy.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
    //获取屏幕宽度 单位为像素
    public static int getScreenWidth(Context context){
        DisplayMetrics metrics= context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int dp2px(Context context, int dpValue){
        DisplayMetrics metrics= context.getResources().getDisplayMetrics();
        float density= metrics.density;
        return  (int)(dpValue*density+0.5f);
    }

    public static int px2dp(Context context, int pxValue){
        DisplayMetrics metrics= context.getResources().getDisplayMetrics();
        float density= metrics.density;
        return  (int)(pxValue/density+0.5f);
    }
}
