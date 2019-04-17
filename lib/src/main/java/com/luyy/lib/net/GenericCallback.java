package com.luyy.lib.net;

import com.google.gson.Gson;
import com.luyy.lib.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public abstract class GenericCallback<T> implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response){
        if(response.isSuccessful()){
            try {
            String data= null;
            data = new String(response.body().bytes());
            if(getClass().getGenericSuperclass() instanceof ParameterizedType){
                if(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName().equals("java.lang.String")){
                    onSuccess((T) data);
                }else{
                    Gson gson=new Gson();
                    T t= gson.fromJson(data,((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                    onSuccess(t);
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
                onError(e.getMessage());
            }
        }
    }

    public abstract void onSuccess(T t);
    public abstract  void onError(String msg);
}
