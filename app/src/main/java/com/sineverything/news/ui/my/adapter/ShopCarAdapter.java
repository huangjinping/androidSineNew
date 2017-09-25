package com.sineverything.news.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sineverything.news.R;
import com.sineverything.news.bean.my.Goodscart;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/9.
 * email : huangjinping@hdfex.com
 */

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Goodscart> dataList;


    private ShopCartCallBack cartCallBack;

    public void setCartCallBack(ShopCartCallBack cartCallBack) {
        this.cartCallBack = cartCallBack;
    }

    public ShopCarAdapter(List<Goodscart> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        Context context = viewHolder.txt_goodsName.getContext();
        final Goodscart goodscart = dataList.get(position);
        if (!TextUtils.isEmpty(goodscart.getGoodsName())) {
            viewHolder.txt_goodsName.setText(goodscart.getGoodsName());
        }
        if (!TextUtils.isEmpty(goodscart.getSpecInfo())) {
            viewHolder.txt_specInfo.setText(Html.fromHtml(goodscart.getSpecInfo()));
        }
        if (!TextUtils.isEmpty(goodscart.getCount() + "")) {
            viewHolder.edt_account.setText(goodscart.getCount() + "");
        }
        if (!TextUtils.isEmpty(goodscart.getPrice())) {
            viewHolder.txt_price.setText("$" + goodscart.getPrice());
        }
        if (goodscart.isSelected()) {
            viewHolder.img_selected.setImageResource(R.mipmap.ic_selected);
        } else {
            viewHolder.img_selected.setImageResource(R.mipmap.ic_unselected);
        }
        viewHolder.img_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartCallBack != null) {
                    cartCallBack.selected(position, !goodscart.isSelected());
                }
            }
        });
        viewHolder.txt_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartCallBack != null) {
                    if (goodscart.getCount() <= 1) {
                        return;
                    }
                    cartCallBack.reduce(position, goodscart.getCount() - 1);
                }
            }
        });
        viewHolder.txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartCallBack != null) {
                    if (cartCallBack != null) {
                        cartCallBack.reduce(position, goodscart.getCount() + 1);
                    }
                }
            }
        });

        Glide.with(context).load(goodscart.getMainPhoto()).placeholder(R.mipmap.ic_default).error(R.mipmap.ic_default).into(viewHolder.img_mainPhoto);
        addTextChangeListener(viewHolder.edt_account, position);
    }

    private void addTextChangeListener(EditText editText, int position) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Toast.makeText(v.getContext(), "hasFocus", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public interface ShopCartCallBack {
        void reduce(int position, int count);

        void add(int position, int count);

        void selected(int position, boolean selected);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_selected;
        public ImageView img_mainPhoto;
        public TextView txt_goodsName;
        public TextView txt_specInfo;
        public TextView txt_price;
        public TextView txt_reduce;
        public EditText edt_account;
        public TextView txt_add;

        public ViewHolder(View rootView) {
            super(rootView);

            this.rootView = rootView;
            this.img_selected = (ImageView) rootView.findViewById(R.id.img_selected);
            this.img_mainPhoto = (ImageView) rootView.findViewById(R.id.img_mainPhoto);
            this.txt_goodsName = (TextView) rootView.findViewById(R.id.txt_goodsName);
            this.txt_specInfo = (TextView) rootView.findViewById(R.id.txt_specInfo);
            this.txt_price = (TextView) rootView.findViewById(R.id.txt_price);
            this.txt_reduce = (TextView) rootView.findViewById(R.id.txt_reduce);
            this.edt_account = (EditText) rootView.findViewById(R.id.edt_account);
            this.txt_add = (TextView) rootView.findViewById(R.id.txt_add);
        }

    }
}
