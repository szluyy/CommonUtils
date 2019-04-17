package com.luyy.commonutils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.luyy.lib.utils.DrawableUtils
import com.luyy.lib.utils.ImageLoaderUtils
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
    }

    fun show(v:View){
        ImageLoaderUtils.load(null,image)
    }
}
