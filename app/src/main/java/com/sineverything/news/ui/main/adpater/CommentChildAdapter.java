package com.sineverything.news.ui.main.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.bean.main.Comments;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/9/26.
 * email : huangjinping@hdfex.com
 */

public class CommentChildAdapter extends BaseAdapter {


    private Context context;
    private List<Comments> dataList;

    public CommentChildAdapter(Context context, List<Comments> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    ViewHolder holder;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_commtents, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comments comments = dataList.get(position);
        if (!TextUtils.isEmpty(comments.getUserNickName())) {
            holder.txtUserNickName.setText(comments.getUserNickName());
        }
        if (!TextUtils.isEmpty(comments.getContent())) {
            holder.txtContent.setText(comments.getContent());
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.txt_userNickName)
        TextView txtUserNickName;
        @Bind(R.id.txt_content)
        TextView txtContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
