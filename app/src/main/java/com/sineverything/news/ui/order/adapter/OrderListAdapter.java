package com.sineverything.news.ui.order.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.bean.order.Order;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] title = {"待付款", "代发货", "待收货", "待评价", "已完成", "已取消"};
    private String[] status = {"10", "20", "30", "40", "50", "0"};
    private List<Order> dataList;

    private MyItemClickListener itemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public OrderListAdapter(List<Order> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_parent_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Order order = dataList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        if (order.getGoodsInfo() != null) {
            OrderChildAdapter childAdapter = new OrderChildAdapter(order.getGoodsInfo());
            viewHolder.rec_order_child.setLayoutManager(new LinearLayoutManager(viewHolder.rec_order_child.getContext()));
            viewHolder.rec_order_child.setAdapter(childAdapter);
        }
        if (order.getGoodsInfo() != null) {
            viewHolder.txt_goodsCount.setText("共" + order.getGoodsInfo().size() + "件商品");
        }
        String status = order.getOrderStatus();
        for (int i = 0; i < this.status.length; i++) {
            if (this.status[i].equals(order.getOrderStatus())) {
                viewHolder.txt_orderStatus.setText(title[i] + "");
                break;
            }
        }

//        if ("10".equals(status)) {
//            viewHolder.txt_status_pay.setVisibility(View.VISIBLE);
//        } else if ("20".equals(status)) {
//            viewHolder.txt_status_remind.setVisibility(View.VISIBLE);
//
//        } else if ("30".equals(status)) {
//            viewHolder.txt_status_receipt.setVisibility(View.VISIBLE);
//
//
//        } else if ("40".equals(status)) {
//
//
//        } else if ("50".equals(status)) {
//
//
//        } else if ("0".equals(status)) {
//
//        }


        viewHolder.txt_totalPrice.setText("S$" + order.getTotalPrice());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_goodsCount;
        public TextView txt_orderStatus;
        public RecyclerView rec_order_child;
        public TextView txt_totalPrice;
        public TextView txt_status_pay;
        public TextView txt_status_delete;
        public TextView txt_status_remind;
        public TextView txt_status_receipt;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_goodsCount = (TextView) rootView.findViewById(R.id.txt_goodsCount);
            this.txt_orderStatus = (TextView) rootView.findViewById(R.id.txt_orderStatus);
            this.rec_order_child = (RecyclerView) rootView.findViewById(R.id.rec_order_child);
            this.txt_totalPrice = (TextView) rootView.findViewById(R.id.txt_totalPrice);
            this.txt_status_pay = (TextView) rootView.findViewById(R.id.txt_status_pay);
            this.txt_status_delete = (TextView) rootView.findViewById(R.id.txt_status_delete);
            this.txt_status_remind = (TextView) rootView.findViewById(R.id.txt_status_remind);
            this.txt_status_receipt = (TextView) rootView.findViewById(R.id.txt_status_receipt);
        }

    }
}
