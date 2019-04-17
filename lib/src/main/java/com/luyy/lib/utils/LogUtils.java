package com.luyy.lib.utils;

import android.util.Log;
import com.luyy.lib.BuildConfig;

public class LogUtils {
    public static void d(String msg){
        if(BuildConfig.DEBUG){
            String tag=generateTag();
            Log.d(tag,msg);
        }
    }

    public static void v(String msg){
        if(BuildConfig.DEBUG){
            String tag=generateTag();
            Log.v(tag,msg);
        }
    }

    public static void e(String msg){
        if(BuildConfig.DEBUG){
            String tag=generateTag();
            Log.e(tag,msg);
        }
    }
    public static void i(String msg){
        if(BuildConfig.DEBUG){
            String tag=generateTag();
            Log.i(tag,msg);
        }
    }
    public static void w(String msg){
        if(BuildConfig.DEBUG){
            String tag=generateTag();
            Log.w(tag,msg);
        }

    }

    /**
     * 得到标签,log标签+类名+方法名+第几行
     *
     * @return
     */
    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }
}
