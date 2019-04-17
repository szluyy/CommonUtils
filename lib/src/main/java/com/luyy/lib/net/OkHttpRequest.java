package com.luyy.lib.net;

import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class OkHttpRequest {
    private String url;
    private Object tag;
    protected Request.Builder builder=new Request.Builder();

    protected OkHttpRequest(String url,Object tag){
        this.url=url;
        this.tag=tag;
        initBuilder();

    }

    private void initBuilder() {
        builder.url(url).tag(tag);
    }

   public Request generateRequest(){
       RequestBody body=buildRequestBody();
        return  buildRequest(body);
   }

    protected abstract RequestBody buildRequestBody();
    protected  abstract Request buildRequest(RequestBody body);
}
