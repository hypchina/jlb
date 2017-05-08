package com.jlb.jinliangbao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jlb.jinliangbao.util.BaseActivity;

public class MainActivity extends BaseActivity {

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        boolean flag = true;
        while (flag){
            if(relativeLayout.getBackground() != null){//图片加载完毕
                flag = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = ((BitmapDrawable)relativeLayout.getBackground()).getBitmap();
                        if(!bitmap.isRecycled()){
                            bitmap.recycle();
                        }
                        MainActivity.this.finish();
                    }
                }, 2300);
            }
        }
    }
}
