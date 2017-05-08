package com.jlb.jinliangbao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jlb.jinliangbao.fragment.AccountFragment;
import com.jlb.jinliangbao.fragment.HomeFragment;
import com.jlb.jinliangbao.fragment.MoreFragment;


/**
 * Created by Administrator on 2017/4/22.
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter{
    public final int COUNT = 4;
    private Context context;

    public ContentFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                //首页
                fragment = HomeFragment.newInstance(position);
                break;

            case 2:
                fragment = AccountFragment.newInstance();
                break;
            case 3:
                fragment = MoreFragment.newInstance();
                break;
            default:
                fragment = HomeFragment.newInstance(position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}
