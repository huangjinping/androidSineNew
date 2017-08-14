package com.sineverything.news.ui.service.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.ui.commodity.adapter.HomeTopMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int MENU_ONE = 1;

    private static final int MENU_TWO = 2;

    private static final int MENU_Three = 3;

    private List<String> dataList;
    final List<Classify> mClassify = new ArrayList<>();

    public ServiceAdapter(List<String> dataList) {
        this.dataList = dataList;
        mClassify.add(new Classify("超市", R.mipmap.menu_1));
        mClassify.add(new Classify("化妆品", R.mipmap.menu_2));
        mClassify.add(new Classify("服装", R.mipmap.menu_3));
        mClassify.add(new Classify("手机数码", R.mipmap.menu_4));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (MENU_ONE == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_one, parent, false);
            return new ViewHolderOne(view);
        } else if (MENU_TWO == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_two, parent, false);
            return new ViewHolderTwo(view);

        } else if (MENU_Three == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_three, parent, false);
            return new ViewHolderThree(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderOne) {
            ViewHolderOne viewHolderOne= (ViewHolderOne) holder;
            Context context = viewHolderOne.rec_service_menu.getContext();
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 4);
            viewHolderOne.rec_service_menu.setLayoutManager(mLayoutManager);
            HomeTopMenuAdapter mAdapter = new HomeTopMenuAdapter(context, mClassify);
            viewHolderOne.rec_service_menu.setAdapter(mAdapter);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MENU_ONE;
        } else if (position == dataList.size() - 1) {
            return MENU_Three;
        } else {
            return MENU_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView rec_service_menu;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rec_service_menu = (RecyclerView) rootView.findViewById(R.id.rec_service_menu);
        }

    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_icon;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
        }

    }

    public static class ViewHolderThree extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_bannner;

        public ViewHolderThree(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_bannner = (ImageView) rootView.findViewById(R.id.img_bannner);
        }

    }
}
