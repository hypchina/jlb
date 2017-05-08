package com.jlb.jinliangbao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlb.jinliangbao.R;
import com.jlb.jinliangbao.adapter.HomePagerAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */
public class HomeFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private List<ImageView> data = new ArrayList();

    public static HomeFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page+1);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        initList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        ViewPager imgViewPager = (ViewPager)view.findViewById(R.id.home_pic_scroll);
        TextView nllText = (TextView)view.findViewById(R.id.nll_text);
        TextView qxTextView = (TextView)view.findViewById(R.id.qx_text);
        TextView qgTextView = (TextView)view.findViewById(R.id.qg_text);
        TextView syTextView = (TextView)view.findViewById(R.id.sy_text);

        ForegroundColorSpan black = new ForegroundColorSpan(Color.parseColor("#656364"));
        ForegroundColorSpan blue = new ForegroundColorSpan(Color.parseColor("#17ada4"));
        ForegroundColorSpan red = new ForegroundColorSpan(Color.parseColor("#f15f5f"));


        SpannableStringBuilder nllTextString = new SpannableStringBuilder("年利率:10%");
        nllTextString.setSpan(black,0,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nllTextString.setSpan(blue,4,nllTextString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        nllText.setText(nllTextString);

        //期限字体颜色设置
        SpannableStringBuilder qxString = new SpannableStringBuilder("期限10天");
        qxString.setSpan(red,2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qxString.setSpan(black,0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qxString.setSpan(black,4,qxString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qxTextView.setText(qxString);

        //起购字体颜色设置
        SpannableStringBuilder qgString = new SpannableStringBuilder("100元起购");
        qgString.setSpan(red,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qgString.setSpan(black,3,qgString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        qgTextView.setText(qgString);

        //剩余字体颜色设置
        SpannableStringBuilder syString = new SpannableStringBuilder("剩余125000元");
        syString.setSpan(red,2,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        syString.setSpan(black,0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        syString.setSpan(black,9,syString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        syTextView.setText(syString);

        imgViewPager.setAdapter(new HomePagerAdapter(data));
        return view;
    }

    /**
     * 初始化数据 ViewPager的数据
     */
    private void initList(){
        data.clear();
        ImageView image01 = new ImageView(getActivity());
        image01.setImageResource(R.drawable.home_bananer_01);
        image01.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image02 = new ImageView(getActivity());
        image02.setImageResource(R.drawable.home_bananer_02);
        image02.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image03 = new ImageView(getActivity());
        image03.setImageResource(R.drawable.home_bananer_03);
        image03.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView image04 = new ImageView(getActivity());
        image04.setImageResource(R.drawable.home_bananer_04);
        image04.setScaleType(ImageView.ScaleType.CENTER_CROP);

        data.add(image01);
        data.add(image02);
        data.add(image03);
        data.add(image04);

    }
}
