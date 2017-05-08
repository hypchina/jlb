package com.jlb.jinliangbao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlb.jinliangbao.adapter.HomePagerAdapter;
import com.jlb.jinliangbao.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity_copy extends BaseActivity implements View.OnClickListener{
    private List<ImageView> data = new ArrayList<>();
    private LinearLayout dotsLayout;
    private int currentDot = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_copy);
        ViewPager v = (ViewPager)findViewById(R.id.home_pic_scroll);
        dotsLayout = (LinearLayout)findViewById(R.id.bots_list_layout);
        TextView nlvText = (TextView)((LinearLayout) findViewById(R.id.nll_lin)).getChildAt(0);

        //弧线下面的年利率
        SpannableStringBuilder nllString = new SpannableStringBuilder("年利率:10%");
        //设置颜色
        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#656364"));
        nllString.setSpan(span,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan span2 = new ForegroundColorSpan(Color.parseColor("#17ada4"));
        nllString.setSpan(span2,4,nllString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nlvText.setText(nllString);

        //期限 ，起购，剩余
        TextView qixianTextView = (TextView)findViewById(R.id.qixian_text);
        TextView qigouTextView = (TextView)findViewById(R.id.qigou_text);
        TextView shengyuTextView = (TextView)findViewById(R.id.shengyu_text);

        ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.parseColor("#605c5b"));
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#f15f5f"));


        SpannableStringBuilder qxString = new SpannableStringBuilder("期限10天");
        SpannableStringBuilder qgString = new SpannableStringBuilder("100元起购");
        SpannableStringBuilder syString = new SpannableStringBuilder("剩余105200元");

        //期限
        qxString.setSpan(blackSpan,0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qxString.setSpan(blackSpan,qxString.length()-1,qxString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qxString.setSpan(redSpan,2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qixianTextView.setText(qxString);
        //起购
        qgString.setSpan(redSpan,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qgString.setSpan(blackSpan,3,qgString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qigouTextView.setText(qgString);
        //剩余
        syString.setSpan(blackSpan,0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        syString.setSpan(redSpan,2,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        syString.setSpan(blackSpan,6,syString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        shengyuTextView.setText(syString);

        initList();
        v.setAdapter(new HomePagerAdapter(data));

        //初始化小圆点
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams)dotsLayout.getLayoutParams();
        p.setMargins(10,0,10,0);
        for (int i =0 ;i<data.size();i++){
            ImageView dot = new ImageView(this);
            dot.setBackgroundResource(R.drawable.dot);
            dot.setLayoutParams(p);
            dotsLayout.addView(dot);
        }
        dotsLayout.getChildAt(0).setBackgroundResource(R.drawable.dot_selected);

        //设置滚动触发事件
        v.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsLayout.getChildAt(position).setBackgroundResource(R.drawable.dot_selected);
                dotsLayout.getChildAt(currentDot).setBackgroundResource(R.drawable.dot);
                currentDot = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 初始化数据 ViewPager的数据
     */
    private void initList(){
        data.clear();
        ImageView image01 = new ImageView(this);
        image01.setImageResource(R.drawable.home_bananer_01);
        image01.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image02 = new ImageView(this);
        image02.setImageResource(R.drawable.home_bananer_02);
        image02.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image03 = new ImageView(this);
        image03.setImageResource(R.drawable.home_bananer_03);
        image03.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image04 = new ImageView(this);
        image04.setImageResource(R.drawable.home_bananer_04);
        image04.setScaleType(ImageView.ScaleType.CENTER_CROP);

        data.add(image01);
        data.add(image02);
        data.add(image03);
        data.add(image04);

    }

    @Override
    public void onClick(View v) {

    }
}
