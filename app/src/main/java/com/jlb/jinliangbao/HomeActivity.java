package com.jlb.jinliangbao;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jlb.jinliangbao.adapter.ContentFragmentAdapter;
import com.jlb.jinliangbao.util.ActivityCollector;
import com.jlb.jinliangbao.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
    private List<ImageView> data = new ArrayList<>();
    private LinearLayout dotsLayout;
    //退出时的时间
    private long mExitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TabLayout tab = (TabLayout)findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.content_view_pager);
        ContentFragmentAdapter contentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(contentFragmentAdapter);

        //初始化TabLayout的item
        createCustomTabItem(tab);
        //设置选中第一个
        //tab.setupWithViewPager(viewPager);//关联 ViewPager和TabLayout

        //当ViewPager页面改变了 就设置TabLayout对应的item
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        //当TabLayout变化了 就设置ViewPager显示对应的item
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }



    private void createCustomTabItem(TabLayout tabLayout){
        for(int i=0;i<4;i++){
            TabLayout.Tab tab = tabLayout.newTab();
            View tabItem = LayoutInflater.from(this).inflate(R.layout.custom_tab_item,tabLayout,false);
            TextView textView = (TextView)tabItem.findViewById(R.id.nav_text);;
            switch (i){
                case 0:
                    tabItem.findViewById(R.id.nav_image).setBackgroundResource(R.drawable.nav_home_img);
                    textView.setText("首页");
                    tab.select();
                    break;
                case 1:
                    tabItem.findViewById(R.id.nav_image).setBackgroundResource(R.drawable.nav_investment_img);
                    textView.setText("投资");
                    break;
                case 2:
                    tabItem.findViewById(R.id.nav_image).setBackgroundResource(R.drawable.nav_account_img);
                    textView.setText("账户");
                    break;
                case 3:
                    tabItem.findViewById(R.id.nav_image).setBackgroundResource(R.drawable.nav_more_img);
                    textView.setText("更多");
                    break;
            };
            textView.setTextColor(getResources().getColorStateList(R.color.nav_home_text));
            tab.setCustomView(tabItem);
            tabLayout.addTab(tab);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(HomeActivity.this, "再按一次退出金粮宝", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }
}
