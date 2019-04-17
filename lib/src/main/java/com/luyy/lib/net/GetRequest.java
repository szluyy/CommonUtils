package com.luyy.lib.net;

import okhttp3.Request;
import okhttp3.RequestBody;

public class GetRequest extends OkHttpRequest {

    protected GetRequest(String url, Object tag) {
        super(url, tag);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody body) {
        return builder.get().build() ;
    }
}
