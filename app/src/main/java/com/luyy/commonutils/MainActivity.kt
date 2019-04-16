package com.luyy.commonutils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.luyy.lib.utils.DialogUtils
import com.luyy.lib.utils.ImageLoaderUtils
import com.luyy.lib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun show(v:View){
        ImageLoaderUtils.load(null,image)
    }
}
