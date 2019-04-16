package com.luyy.lib.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载接口
 */
public interface ILoader {
    void init(Context context);
    void request(String url,ImageView imageView);
    void pause();
    void resume();
    void clear();
}
