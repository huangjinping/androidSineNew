package com.sineverything.news.ui.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.order.GoodsInfo;
import com.sineverything.news.bean.order.OrderDetails;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER = 1;
    private final int ITEM = 2;
    private final int FOOTER = 3;
    private List<GoodsInfo> dataList;
    private OrderDetails details;

    public OrderDetailsAdapter(List<GoodsInfo> dataList, OrderDetails details) {
        this.dataList = dataList;
        this.details = details;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (HEADER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_header, parent, false);
            return new ViewHeaderHolder(view);
        } else if (FOOTER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_footer, parent, false);
            return new ViewFooterHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_confirmorder_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

            if (dataList.size() != 0) {
                GoodsInfo goodsInfo = dataList.get(position - 1);
                ImageLoaderUtils.display(viewHolder.img_icon.getContext(), viewHolder.img_icon, goodsInfo.getGoodsMainphotoPath());

                if (!TextUtils.isEmpty(goodsInfo.getGoodsPrice())) {
                    viewHolder.txt_storePrice.setText("$s" + goodsInfo.getGoodsPrice());
                }
                if (!TextUtils.isEmpty(goodsInfo.getGoodsCount())) {
                    viewHolder.txt_goodsCount.setText("x" + goodsInfo.getGoodsCount());
                }

                if (!TextUtils.isEmpty(goodsInfo.getGoodsName())) {
                    viewHolder.txt_goodsName.setText(goodsInfo.getGoodsName());
                }

            }

        } else if (holder instanceof ViewFooterHolder) {
            ViewFooterHolder footerHolder = (ViewFooterHolder) holder;
            if (!TextUtils.isEmpty(details.getAddTime())) {
                footerHolder.txt_addTime.setText(details.getAddTime());
            }
            if (!TextUtils.isEmpty(details.getOrderId())) {
                footerHolder.txt_orderId.setText(details.getOrderId());
            }

            if (!TextUtils.isEmpty(details.getTotalPrice())) {
                footerHolder.txt_totalPrice.setText("$" + details.getTotalPrice());
            }
        } else {
            ViewHeaderHolder headerHolder = (ViewHeaderHolder) holder;
            String status = details.getOrderStatus();
//            private String[] title = {"待付款", "代发货", "待收货", "待评价", "已完成", "已取消"};
//            private String[] status = {"10", "20", "30", "40", "50", "0"};

            if ("10".equals(status)) {
                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daifukuan);
            } else if ("20".equals(status)) {
                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daifahuo);

            } else if ("30".equals(status)) {

                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daishouhuo);


            } else if ("40".equals(status)) {
                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daimai_wancheng);


            } else if ("50".equals(status)) {
                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daimai_wancheng);


            } else if ("0".equals(status)) {
                headerHolder.img_time_axis.setImageResource(R.mipmap.ic_daiquxiao);

            }

            headerHolder.rootView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == (dataList.size() + 1)) {
            return FOOTER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 2;
    }

    public static class ViewHeaderHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_phone;
        public ImageView img_time_axis;

        public ViewHeaderHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_phone = (TextView) rootView.findViewById(R.id.txt_phone);
            this.img_time_axis = (ImageView) rootView.findViewById(R.id.img_time_axis);
        }

    }

    public static class ViewFooterHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_orderId;
        public TextView txt_jiaoyi;
        public LinearLayout layout_jiaoyi;
        public TextView txt_addTime;
        public TextView txt_totalPrice;


        public ViewFooterHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_orderId = (TextView) rootView.findViewById(R.id.txt_orderId);
            this.txt_jiaoyi = (TextView) rootView.findViewById(R.id.txt_jiaoyi);
            this.layout_jiaoyi = (LinearLayout) rootView.findViewById(R.id.layout_jiaoyi);
            this.txt_addTime = (TextView) rootView.findViewById(R.id.txt_addTime);
            this.txt_totalPrice = (TextView) rootView.findViewById(R.id.txt_totalPrice);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_icon;
        public TextView txt_goodsName;
        public TextView txt_param;
        public TextView txt_storePrice;
        public TextView txt_goodsCount;
        public Button txt_return;


        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
            this.txt_goodsName = (TextView) rootView.findViewById(R.id.txt_goodsName);
            this.txt_param = (TextView) rootView.findViewById(R.id.txt_param);
            this.txt_storePrice = (TextView) rootView.findViewById(R.id.txt_storePrice);
            this.txt_goodsCount = (TextView) rootView.findViewById(R.id.txt_goodsCount);
            this.txt_return = (Button) rootView.findViewById(R.id.txt_return);
        }

    }


}
