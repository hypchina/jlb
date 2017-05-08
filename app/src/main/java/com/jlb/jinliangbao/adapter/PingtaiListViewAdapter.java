package com.jlb.jinliangbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlb.jinliangbao.R;
import com.jlb.jinliangbao.entity.MorePingTaiItem;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public class PingtaiListViewAdapter extends ArrayAdapter<MorePingTaiItem>{

    private List<MorePingTaiItem> datas;
    private int itemLayout;
    public PingtaiListViewAdapter(Context context, int resource, List<MorePingTaiItem> objects) {
        super(context, resource, objects);
        datas = objects;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MorePingTaiItem morePingTaiItem = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
            viewHolder = new ViewHolder();
            ImageView icon = (ImageView)view.findViewById(R.id.item_logo);
            TextView itemText = (TextView)view.findViewById(R.id.item_text);
            TextView itemTextNum = (TextView)view.findViewById(R.id.item_text_num);
            viewHolder.icon = icon;
            viewHolder.itemText = itemText;
            viewHolder.itemTextNum = itemTextNum;
            view.setTag(viewHolder);
            view.setBackgroundResource(R.drawable.text);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.icon.setBackground(morePingTaiItem.getIcon());
        viewHolder.itemText.setText(morePingTaiItem.getTitle());
        viewHolder.itemTextNum.setText(morePingTaiItem.getAccount());
        return view;
    }

    class ViewHolder{
        ImageView icon;
        TextView itemText;
        TextView itemTextNum;
    }
}
