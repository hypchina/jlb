package com.jlb.jinliangbao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.jlb.jinliangbao.R;
import com.jlb.jinliangbao.adapter.PingtaiListViewAdapter;
import com.jlb.jinliangbao.entity.MorePingTaiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public class MoreFragment extends Fragment{
    private MoreFragment(){}

    private List<MorePingTaiItem> ptData = new ArrayList<>();

    public static MoreFragment newInstance(){
        MoreFragment moreFragment = new MoreFragment();
        Bundle bundle = new Bundle();
        bundle.putString("test","test");
        moreFragment.setArguments(bundle);
        return moreFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_layout,container,false);

        ListView ptListView = (ListView)view.findViewById(R.id.pingtai_list);
        ScrollView ScrollView = (ScrollView)view.findViewById(R.id.scroll_view);

        PingtaiListViewAdapter ptAdapter = new PingtaiListViewAdapter(getActivity(),R.layout.more_pingtai_list_item,ptData);
        ptListView.setAdapter(ptAdapter);
        //setListViewHeight(ptListView);
//        ptListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                View firstItemView=ptListView.getAdapter().getView(0, null, ptListView);//得到第一个Item
//                firstItemView.measure(0, 0);
//                Log.e("TTTTTTTTTTT","第一个Item的高度:"+firstItemView.getMeasuredHeight());
//                Log.e("TTTTTTTTTTT","mListView的高度："+ptListView.getHeight());
//            }
//        });

        return view;
    }

    private void init(){
        ptData.clear();
        MorePingTaiItem itemForWX = new MorePingTaiItem();
        itemForWX.setAccount("68540098");
        itemForWX.setTitle("微信平台");
        itemForWX.setIcon(getResources().getDrawable(R.drawable.more_wx));

        MorePingTaiItem itemWB = new MorePingTaiItem();
        itemWB.setTitle("微博平台");
        itemWB.setAccount("68547837");
        itemWB.setIcon(getResources().getDrawable(R.drawable.more_wb));

        ptData.add(itemForWX);
        ptData.add(itemWB);
    }

    private void setListViewHeight(ListView listView){

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        Log.e("TTTTTT",totalHeight+"");
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }
}
