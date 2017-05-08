package com.jlb.jinliangbao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jlb.jinliangbao.util.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/25.
 */
public class QrcodeActivity extends BaseActivity implements View.OnClickListener{
    private ImageView qrcode;
    private AlertDialog alertDialog;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(qrcode != null){
                        Glide.with(getApplicationContext()).load(getString(R.string.qrcode_url)).into(qrcode);
                    }
                    break;
                case 2:
                    Toast.makeText(QrcodeActivity.this,msg.getData().getString("toast_text"),Toast.LENGTH_SHORT).show();

                    alertDialog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_layout);
        qrcode = (ImageView)findViewById(R.id.qrcode_img);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();

        qrcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(alertDialog == null){

                    AlertDialog.Builder builder = new AlertDialog.Builder(QrcodeActivity.this,R.style.buttom_dialog);

                    View view = LayoutInflater.from(QrcodeActivity.this).inflate(R.layout.alertdialog_buttom, null);

                    Button saveAlbum = (Button)view.findViewById(R.id.save_album);
                    Button cancel = (Button)view.findViewById(R.id.cancel);
                    saveAlbum.setOnClickListener(QrcodeActivity.this);
                    cancel.setOnClickListener(QrcodeActivity.this);

                    builder.setView(view);
                    builder.setCancelable(true);
                    alertDialog = builder.create();
                }
                Window window = alertDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                WindowManager.LayoutParams params = window.getAttributes();
                params.y = 50;
                window.setAttributes(params);

                alertDialog.show();

                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_album:{
                //保存图片到相册
                qrcode.setDrawingCacheEnabled(true);
                final Bitmap image = Bitmap.createBitmap(qrcode.getDrawingCache());
                qrcode.setDrawingCacheEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = saveFileToCache(image);
                        Message message = new Message();
                        message.what=2;
                        Bundle bundle = new Bundle();
                        bundle.putString("toast_text","保存成功");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        //通知相册更新
                        updateAlbums(file);
                    }
                }).start();
                break;
            }
            case R.id.cancel:{
                alertDialog.dismiss();
            }
        }
    }

    private File saveFileToCache(Bitmap image){
        File saveFile = getDiskCacheDir(getApplicationContext(),"test.png");
//        if(saveFile.exists()){
//            saveFile.delete();
//        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(saveFile));
            image.compress(Bitmap.CompressFormat.PNG,100,out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(QrcodeActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
        }finally {
            try {
                if(out!=null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return saveFile;
    }
}
