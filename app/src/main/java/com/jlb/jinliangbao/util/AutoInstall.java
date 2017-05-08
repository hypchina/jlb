package com.jlb.jinliangbao.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/4/25.
 */
public class AutoInstall {
    public static void install(Context context, String apkPath){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(apkPath)),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    //打开APK程序代码-就是安装
    private void openFile(File file, Activity activity) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    void downApk(String url){

    }
}
