package com.luyy.lib.net;

import com.luyy.lib.utils.LogUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/4/16 0016 下午 2:47
 * 功能描述：
 * okhttp 工具类
 */
public class OkHttpUtils {
    private static volatile OkHttpUtils mInstance;
    private static volatile OkHttpClient mClient;
    private static final long DEFAULT_MILLISECONDS=15*1000L;
    /**
     * 防止外部初始化
     * @param client
     */
    private OkHttpUtils(OkHttpClient client){
        if(client==null){
            mClient=new OkHttpClient.Builder()
                    .readTimeout(DEFAULT_MILLISECONDS,TimeUnit.MILLISECONDS)
                    .writeTimeout(DEFAULT_MILLISECONDS,TimeUnit.MILLISECONDS)
                    .connectTimeout(DEFAULT_MILLISECONDS,TimeUnit.MILLISECONDS)
                    .build();
        }else{
            mClient=client;
        }
    }

    /**
     * 传入自定义OkHttpClient
     * @param client
     * @return
     */
    private static OkHttpUtils init(OkHttpClient client) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance=new OkHttpUtils(client);
                }
            }
        }
        return mInstance;

    }
    public static OkHttpUtils getInstance(){
        return init(null);
    }

    public void get(String url,GenericCallback callback) {
        buildRequest(url,callback);
    }

    private static void buildRequest(String url,GenericCallback callback) {
        Request request=new GetRequest(url,"").generateRequest();
        mClient.newCall(request).enqueue(callback);
    }
}
