package com.luyy.commonutils

import android.Manifest
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.luyy.lib.utils.DownloadUtil
import com.luyy.lib.utils.LogUtils

import com.luyy.lib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var image=ImageView(this)
        image.setImageResource(R.mipmap.ic_launcher)
        image.layoutParams=RelativeLayout.LayoutParams(-2,-2)
        image.scaleType=ImageView.ScaleType.FIT_CENTER
        dragView.addTargetView(image)
//        btn_change.setOnClickListener{
//            LogUtils.e("x"+iv.left+"width="+iv.width)
//            iv.scaleX=2.0f
//            iv.scaleY=2.0f
//            LogUtils.e("x"+iv.left+"width="+iv.width)
//
//        }
    }

}
