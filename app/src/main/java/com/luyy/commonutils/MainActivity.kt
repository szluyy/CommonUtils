package com.luyy.commonutils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.MessageQueue
import android.view.View
import com.luyy.lib.net.GenericCallback
import com.luyy.lib.net.OkHttpUtils
import com.luyy.lib.utils.DrawableUtils
import com.luyy.lib.utils.ImageLoaderUtils
import com.luyy.lib.utils.LogUtils
import com.luyy.lib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        codeView.setListener {
            ToastUtils.show(this,"data=${it}")
        }

        tv.setBackgroundDrawable(
            DrawableUtils.DrawableBuilder()
                .radius(10f).gradient(Color.GREEN,Color.BLUE,Color.CYAN,GradientDrawable.Orientation.BL_TR)
                .stroke(1,Color.GRAY)
                .build()
        )
        Looper.myQueue().addIdleHandler(object:MessageQueue.IdleHandler{
            override fun queueIdle(): Boolean {
                LogUtils.d("i am waiting for you")
                return false
            }

        })
    }

    fun show(v:View){
       OkHttpUtils.getInstance().get("http://www.baidu.com",object:GenericCallback<String>(){
           override fun onError(msg: String?) {
           }

           override fun onSuccess(t: String?) {
                LogUtils.d(t)
           }
       })
    }
}
