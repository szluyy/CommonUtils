package com.luyy.lib.ImageLoader;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.luyy.lib.R;
import com.luyy.lib.utils.LogUtils;
import com.luyy.lib.utils.ScreenUtils;

public class GlideLoader implements ILoader {

    private RequestManager manager;
    private ImageView target;
    private Context context;

    @Override
    public void init(Context context) {
        if(manager==null){
            this.context=context;
            manager= Glide.with(context);
        }else{
            LogUtils.e("GlideLoader 只能初始化一次");
        }
    }

    @Override
    public void request(String url, ImageView imageView) {
         target=imageView;
         manager.load(url).fallback(R.mipmap.ic_placeholder).override(ScreenUtils.dp2px(context,100)).into(imageView);
    }

    @Override
    public void pause() {
        manager.pauseRequests();
    }

    @Override
    public void resume() {
        manager.resumeRequests();
    }

    @Override
    public void clear() {
        if(target!=null)
            manager.clear(target);
    }
}
