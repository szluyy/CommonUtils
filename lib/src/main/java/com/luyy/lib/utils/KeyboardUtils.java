package com.luyy.lib.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/4/17 0017 上午 10:14
 * 功能描述：软键盘弹出与收回的工具类
 */
public class KeyboardUtils {
    public static void toggle(Context context){
        InputMethodManager manager= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
