package com.jlb.jinliangbao.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.jlb.jinliangbao.R;

/**
 * Created by Administrator on 2017/4/11.
 */
public class NavigationLayout extends LinearLayout{

    public NavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navigation,this);

    }
}
