package com.luyy.commonutils

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.luyy.lib.utils.ToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun show(v:View){
        ToastUtils.show(this,"hello")

    }
}
