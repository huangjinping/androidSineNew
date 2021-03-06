package com.sineverything.news.ui.main.adpater;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.view.MListView;
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

    private Context context;
    private List<Comments> dataList;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commtents, null);
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
        if (!TextUtils.isEmpty(comments.getTime())) {
            holder.txtTime.setText(comments.getTime());
        }
        if (comments.getReplys() != null) {
            CommentChildAdapter commentChildAdapter = new CommentChildAdapter(context, comments.getReplys());
            holder.lisvChild.setAdapter(commentChildAdapter);
        }

        holder.layout_rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size()>position){
                    RxBus.getInstance().post(Comments.class.getSimpleName(),dataList.get(position));
                }
            }
        });


        return convertView;
    }




    static class ViewHolder {
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
        @Bind(R.id.layout_rootView)
        LinearLayout layout_rootView;
        @Bind(R.id.lisv_child)
        MListView lisvChild;



        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
