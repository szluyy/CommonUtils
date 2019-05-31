package com.luyy.lib.utils;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/5/31 0031 下午 3:03
 * 功能描述： 应用配置
 */
public class AppConfig {
    //判断是否是debug状态
    public static boolean isDebug;
    public static void setIsDebug(boolean isDebug) {
        AppConfig.isDebug = isDebug;
    }
}
