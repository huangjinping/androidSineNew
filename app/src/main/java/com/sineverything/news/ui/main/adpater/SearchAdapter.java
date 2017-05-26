package com.sineverything.news.ui.main.adpater;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.ui.main.activity.NewsDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<NewsItem> dataList;

    public SearchAdapter(List<NewsItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View     view = View.inflate(parent.getContext(), R.layout.layout_newitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final NewsItem newsItem = dataList.get(position);
        Glide.with(viewHolder.imgNewIcon.getContext()).load(newsItem.getPic()).into(viewHolder.imgNewIcon);
        if (newsItem.getTitle() != null && !TextUtils.isEmpty(newsItem.getTitle().getRendered())) {
            viewHolder.txtNewTitle.setText(Html.fromHtml(newsItem.getTitle().getRendered()));
        }
        viewHolder.layoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.startAction(v.getContext(), newsItem.getId() + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_new_icon)
        ImageView imgNewIcon;
        @Bind(R.id.txt_new_title)
        TextView txtNewTitle;
        @Bind(R.id.txt_new_content)
        TextView txtNewContent;
        @Bind(R.id.layout_icon)
        LinearLayout layoutIcon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
