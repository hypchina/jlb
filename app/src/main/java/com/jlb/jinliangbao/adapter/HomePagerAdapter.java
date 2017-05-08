package com.jlb.jinliangbao.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class  HomePagerAdapter extends PagerAdapter{
    private List<ImageView> datas;


    public HomePagerAdapter(List<ImageView> list){
        datas = list;
    }

    @Override
    public int getCount() {
        if(datas != null){
            return datas.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {
        container.addView(datas.get(position));
        //这里返回的view，在isViewFromObject中会进行对比
        return datas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }
}