package com.sineverything.news.ui.my.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.bean.main.Nations;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/9.
 * email : huangjinping@hdfex.com
 */

public class AreaAdapter extends BaseAdapter {
    private Context context;
    private List<Nations> dataList;

    private ViewHolder viewHolder;
    private String pre = "";


    public AreaAdapter(Context context, List<Nations> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_area, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Nations nations = dataList.get(position);
        String nationShort = nations.getNationShort();
        if (!TextUtils.isEmpty(nationShort)) {
            if (!TextUtils.isEmpty(nations.getNationShort())) {
                String s = String.valueOf(nationShort.charAt(0));
                viewHolder.txt_title.setText(s);
                if (pre.equals(s)) {
                    viewHolder.txt_title.setVisibility(View.GONE);
                } else {
                    viewHolder.txt_title.setVisibility(View.VISIBLE);
                }
                pre = s;
            }
        }

        if (!TextUtils.isEmpty(nations.getNationCn())) {
            viewHolder.txt_nationCn.setText(nations.getNationCn());
        }
        if (!TextUtils.isEmpty(nations.getNationCode())) {
            viewHolder.txt_nationCode.setText(nations.getNationCode());
        }
        return convertView;
    }

    public class ViewHolder {
        public View rootView;
        public TextView txt_title;
        public TextView txt_nationCn;
        public TextView txt_nationCode;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.txt_title = (TextView) rootView.findViewById(R.id.txt_title);
            this.txt_nationCn = (TextView) rootView.findViewById(R.id.txt_nationCn);
            this.txt_nationCode = (TextView) rootView.findViewById(R.id.txt_nationCode);
        }

    }
}
