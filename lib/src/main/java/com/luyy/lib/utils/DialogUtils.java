package com.luyy.lib.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.luyy.lib.R;

public class DialogUtils {
    /**
     * 显示圆形加载对话框
     * @param context
     * @param msg
     */
    public static void showLoading(Context context,String msg){
        Dialog dialog=new Dialog(context);
        dialog.setTitle("进度框");
        dialog.setCancelable(true);
        View view= LayoutInflater.from(context).inflate(R.layout.view_dialog_loading,null);
        TextView tv_msg= view.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.width=ScreenUtils.getScreenWidth(context)/2;
        dialog.setContentView(view,lp);
        dialog.show();
    }
}
