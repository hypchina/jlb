package com.jlb.jinliangbao.diyview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jlb.jinliangbao.R;

/**
 * Created by Administrator on 2017/4/10.
 */
public class TitleLayout extends FrameLayout {

    private TextView titleRightText;
    private Button back;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TitleLayout);

        LayoutInflater.from(context).inflate(R.layout.title,this);
        back = (Button) findViewById(R.id.title_back_btn);

        TextView titleLeftText = (TextView)findViewById(R.id.title_left_text);
        TextView titleMainText = (TextView)findViewById(R.id.title_main_text);
        titleRightText = (TextView)findViewById(R.id.title_right_text);
        //设置自定义属性
        titleLeftText.setText(typedArray.getString(R.styleable.TitleLayout_LeftText));
        titleMainText.setText(typedArray.getString(R.styleable.TitleLayout_CenterText));
        titleRightText.setText(typedArray.getString(R.styleable.TitleLayout_RightText));
        boolean isHideLeftBtn =  typedArray.getBoolean(R.styleable.TitleLayout_IsHideLeftBtn,false);
        if(isHideLeftBtn){
            back.setVisibility(View.GONE);
        }else{
            back.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
        typedArray.recycle();
    }

    public void setRightTextOnClickListener(OnClickListener l){
        titleRightText.setOnClickListener(l);
    }

    public void setBackBtnOnClickListener(OnClickListener l){
        back.setOnClickListener(l);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
