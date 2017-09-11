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
        if (order.getGoodsInfo()!=null){
            viewHolder.txt_goodsCount.setText("共"+order.getGoodsInfo().size()+"件商品");
        }
        viewHolder.txt_orderStatus.setText(order.getOrderStatus()+"");
        viewHolder.txt_totalPrice.setText("S$"+order.getTotalPrice());
        viewHolder.rootView.setOnClickListener(  new View.OnClickListener() {
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

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_goodsCount = (TextView) rootView.findViewById(R.id.txt_goodsCount);
            this.txt_orderStatus = (TextView) rootView.findViewById(R.id.txt_orderStatus);
            this.rec_order_child = (RecyclerView) rootView.findViewById(R.id.rec_order_child);
            this.txt_totalPrice = (TextView) rootView.findViewById(R.id.txt_totalPrice);
        }

    }
}
