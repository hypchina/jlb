package com.jlb.jinliangbao;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jlb.jinliangbao.util.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText userPass = (EditText)findViewById(R.id.user_pass);

        userPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() != MotionEvent.ACTION_UP){
                    return false;
                }
                EditText edit = (EditText)view;
                //存放drawableLeft，Right，Top，Bottom四个图片资源对象
                Drawable[] drawables = edit.getCompoundDrawables();
                //判断是否点击了drawableRight,index=2就是right 左 上 右 下
                int imgWidth = drawables[2].getIntrinsicWidth();//获取这个图片的宽度
                float xWidth = motionEvent.getX();//横坐标（点击点在屏幕上的横坐标）
                int vWidth = edit.getWidth();//控件的宽度
                float poor = xWidth-(vWidth-imgWidth);
                //满足条件，则证明点中了图片,显示或隐藏密码，切换对应图标
                if(poor>=0){
                    //如果当前是隐藏密码
                    if(edit.getInputType() == 129){
                        edit.setInputType(InputType.TYPE_CLASS_TEXT);
                        if(Build.VERSION.SDK_INT>=21){
                            edit.setCompoundDrawablesWithIntrinsicBounds(drawables[0],null,getResources().getDrawable(R.drawable.login_eyes2,getTheme()),null);
                        }else{
                            edit.setCompoundDrawablesWithIntrinsicBounds(drawables[0],null,getResources().getDrawable(R.drawable.login_eyes2),null);
                        }
                    }else{
                        edit.setInputType(129);
                        if(Build.VERSION.SDK_INT>=21){
                            edit.setCompoundDrawablesWithIntrinsicBounds(drawables[0],null,getResources().getDrawable(R.drawable.login_eyes1,getTheme()),null);
                        }else{
                            edit.setCompoundDrawablesWithIntrinsicBounds(drawables[0],null,getResources().getDrawable(R.drawable.login_eyes1),null);
                        }
                    }
                }
                return false;
            }
        });
    }
}
