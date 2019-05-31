package com.luyy.lib.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;

import java.io.File;

/**
 * 作者：     陆阳洋
 * 创建日期： 2019/5/9 0009 下午 3:03
 * 功能描述：  官方提供的下载器，部分品牌手机不兼容
 */
public class DownloadUtil {
    private DownloadManager manager;
    private long fd;
    private DownloadCallback callback;
    private Context context;
    private String filePath;
    private  BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
//                Intent i = new Intent();
//                i.setAction(Intent.ACTION_VIEW);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                }
//                Uri uri=  FileProvider.getUriForFile(context,"com.luyy.commonutils.fileprovider",new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile()+"/apk/myapp.apk"));
//                i.setDataAndType(uri,"application/vnd.android.package-archive");
//                context.startActivity(i);
                if(callback!=null){
                    callback.onComplete(filePath);
                }
            }
        }
    };

    public DownloadUtil(Context context) {
        this.context=context;
        IntentFilter filter=new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(receiver,filter);
        manager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Bundle bundle=  msg.getData();
                long total=  bundle.getLong("total");
                long d=  bundle.getLong("download");
                if(callback!=null){
                    callback.progress(d,total);
                }
                if(total==-1||d<total)
                    handler.postDelayed(queryRunable,100);
            }
        }
    };

    public void setCallback(DownloadCallback callback) {
        this.callback = callback;
    }

    public void download(String url,String dirType,String subPath){
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setTitle("文件下载中");
        request.setDescription("稍后");
        //设置网络类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationInExternalFilesDir(context, dirType,subPath);
        filePath=context.getExternalFilesDir(dirType).getAbsolutePath()+File.pathSeparator+subPath;
        fd= manager.enqueue(request);
        if(fd>0){
           handler.postDelayed(queryRunable,100);
        }
    }

     Runnable queryRunable= new Runnable(){
        @Override
        public void run() {
            startQuery();
        }
    };

    private  void startQuery(){
        DownloadManager.Query query=new DownloadManager.Query();
        query.setFilterById(fd);
        Cursor cursor= manager.query(query);
        if(cursor.moveToFirst()){
            long totalsize= cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            long dowloadsize= cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            Message msg=  handler.obtainMessage();
            msg.what=0;
            Bundle b=new Bundle();
            b.putLong("total",totalsize);
            b.putLong("download",dowloadsize);
            msg.setData(b);
            handler.sendMessage(msg);
        }
    }

    public interface DownloadCallback{
        void progress(long current,long total);
        void onComplete(String filepath);
    }

    public void destroy(){
       handler.removeCallbacks(queryRunable);
       context.unregisterReceiver(receiver);
    }

}
