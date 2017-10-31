package com.sineverything.news.ui.commodity.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.view.MListView;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.Comments;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/9/27.
 * email : huangjinping@hdfex.com
 */

public class CommodityCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Comments> dataList;

    public CommodityCommentAdapter(List<Comments> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commtents, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;

        Comments comments = dataList.get(position);
        if (!TextUtils.isEmpty(comments.getUserNickName())) {
            holder.txtUserNickName.setText(comments.getUserNickName());
        }
        if (!TextUtils.isEmpty(comments.getContent())) {
            holder.txtContent.setText(comments.getContent());
        }
        if (!TextUtils.isEmpty(comments.getTime())) {
            holder.txtTime.setText(comments.getTime());
        }
//        if (comments.getReplys() != null) {
//            CommentChildAdapter commentChildAdapter = new CommentChildAdapter(context, comments.getReplys());
//            holder.lisvChild.setAdapter(commentChildAdapter);
//        }

//        holder.layout_rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dataList.size()>position){
//                    RxBus.getInstance().post(Comments.class.getSimpleName(),dataList.get(position));
//                }
//            }
//        });

    }






    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_userPhoto)
        ImageView imgUserPhoto;
        @Bind(R.id.txt_userNickName)
        TextView txtUserNickName;
        @Bind(R.id.txt_content)
        TextView txtContent;
        @Bind(R.id.txt_time)
        TextView txtTime;
        @Bind(R.id.txt_Reply)
        TextView txtReply;
        @Bind(R.id.lisv_child)
        MListView lisvChild;
        @Bind(R.id.layout_rootView)
        LinearLayout layoutRootView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
