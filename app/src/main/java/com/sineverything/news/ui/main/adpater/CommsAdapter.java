package com.sineverything.news.ui.main.adpater;

import android.content.Context;
import android.text.Html;
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
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class CommsAdapter extends BaseAdapter {

    Context context;
    List<Comments> dataList;

    public CommsAdapter(Context context, List<Comments> dataList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commtents, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comments comments = dataList.get(position);
        if (!TextUtils.isEmpty(comments.getAuthor_name())) {
            holder.txtAuthorName.setText(comments.getAuthor_name());
        }
        if (!TextUtils.isEmpty(comments.getDate())) {
            holder.txtDate.setText(comments.getDate());
        }
        if (!TextUtils.isEmpty(comments.getContent().getRendered())) {
            holder.txtRendered.setText(Html.fromHtml(comments.getContent().getRendered()));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.txt_author_name)
        TextView txtAuthorName;
        @Bind(R.id.txt_rendered)
        TextView txtRendered;
        @Bind(R.id.txt_date)
        TextView txtDate;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
