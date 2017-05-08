package com.jlb.jinliangbao.diyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jlb.jinliangbao.R;

/**
 * Created by Administrator on 2017/4/17.
 */
public class RingView extends View{
    private Paint mPaint;
    private String startColor;
    private String overlayColor;
    private int percent;
    private int percentTextSize;
    private String symbol;
    private int numberOfPeople;
    private int numberOfPeopleTextSize;
    private String textColor = "#F12F2F";
    private int startAngle;//起始的角度 ，
    private int sweepAngle = 270;//圆弧扫过的角度，顺时针方向，单位为度。
    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RingView);
        startAngle = typedArray.getInt(R.styleable.RingView_startAngle,135);
        sweepAngle = typedArray.getInt(R.styleable.RingView_sweepAngle,270);
        startColor = typedArray.getString(R.styleable.RingView_startColor);
        overlayColor = typedArray.getString(R.styleable.RingView_overlayColor);
        percent = typedArray.getInt(R.styleable.RingView_percent,0);//百分比-数字
        percentTextSize = typedArray.getInt(R.styleable.RingView_percentTextSize,20);//百分比-数字的大小
        symbol = typedArray.getString(R.styleable.RingView_symbol);//百分比-符号
        numberOfPeople = typedArray.getInt(R.styleable.RingView_numberOfPeople,0);//人数
        numberOfPeopleTextSize = typedArray.getInt(R.styleable.RingView_numberOfPeopleTextSize,0);//人数字符串的大小

        //圆弧 -原始的颜色
        if("".equals(startColor) || startColor == null){
            startColor = "#848889";
        }

        //圆弧 -覆盖的颜色
        if("".equals(overlayColor) || overlayColor==null){
            overlayColor = "#FC4C4E";
        }

        //数字后面的符号
        if("".equals(symbol) || symbol==null){
            symbol = "%";
        }



        typedArray.recycle();
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        RectF rectF;
        int radius = Math.min(width,height);
        //确定区域，为了保证弧线是圆形，把rectF确定为正方形
        if(width>height){
            //宽比高大，按照高来画区域,在原view上水平居中画一个
            int diffWidth = (width-height)/2;
            rectF = new RectF(
                    getLeft()+diffWidth+getPaddingLeft(),
                    getTop()+getPaddingTop(),
                    getRight()-diffWidth-getPaddingRight(),
                    getBottom()-getPaddingBottom()
            );
        }else{
            //高比宽大 按照宽的大小，在原view上垂直居中画一个
            int diffHeight = (height-width)/2;
            rectF = new RectF(
                    getLeft()+getPaddingLeft(),
                    getTop()+diffHeight+getPaddingTop()                                                                                ,
                    getRight()-getPaddingRight(),
                    getBottom()-diffHeight-getPaddingBottom()
            );
        }


        //画弧线-总量弧线
        canvas.drawArc(rectF,startAngle,sweepAngle,false,mPaint);
        //百分比覆盖弧线
        mPaint.setColor(Color.parseColor(overlayColor));
        canvas.drawArc(rectF,startAngle,sweepAngle*percent/100,false,mPaint);


        //在圆弧中间添加字体
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(percentTextSize);
        textPaint.setColor(Color.parseColor(textColor));
        String percentString = String.valueOf(percent);
        float textWidth = textPaint.measureText(percentString);


        //绘制百分号
        textPaint.setTextSize(percentTextSize/2);
        float flag = textPaint.measureText(symbol);

        float offsetX = (textWidth+flag)/2;
        textPaint.setTextSize(percentTextSize);
        canvas.drawText(percentString,rectF.centerX()-offsetX,rectF.centerY()-20,textPaint);
        textPaint.setTextSize(percentTextSize/2);
        textPaint.setColor(Color.parseColor("#5e5e5e"));
        canvas.drawText(symbol,rectF.centerX()-offsetX+textWidth,rectF.centerY()-20,textPaint);

        //购买人数
        textPaint.setTextSize(numberOfPeopleTextSize);
        float pnumsWidth = textPaint.measureText(String.valueOf(numberOfPeople));
        float pnumsTextWidth = textPaint.measureText(numberOfPeople+"人已经购买");

        canvas.drawText(String.valueOf(numberOfPeople),rectF.centerX()-pnumsTextWidth/2,rectF.centerY()+50,textPaint);
        textPaint.setColor(Color.parseColor("#908C8B"));
        canvas.drawText("人已经购买",rectF.centerX()-pnumsTextWidth/2+pnumsWidth,rectF.centerY()+50,textPaint);


    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.parseColor(startColor));
        mPaint.setStyle(Paint.Style.STROKE);//颜色不填充图形
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置线两端圆帽
    }
}
