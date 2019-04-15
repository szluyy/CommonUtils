package com.luyy.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.luyy.lib.R;

public class ToastUtils {
    public static void show(Context context, String msg){
        Toast toast=  new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast .setGravity(Gravity.CENTER,0,0);
        View view= LayoutInflater.from(context).inflate(R.layout.view_custome_toast,null);
        TextView tv_msg= view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        toast.setView(view);
        toast.show();

    }
}
