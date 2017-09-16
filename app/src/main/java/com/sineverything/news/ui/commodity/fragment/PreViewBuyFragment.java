package com.sineverything.news.ui.commodity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.GoodsDetails;
import com.sineverything.news.ui.order.activity.ConfirmOrderActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/9/12.
 * email : huangjinping@hdfex.com
 */

public class PreViewBuyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.layout_back)
    LinearLayout layoutBack;
    @Bind(R.id.img_product_icon)
    ImageView imgProductIcon;
    @Bind(R.id.txt_price)
    TextView txtPrice;
    @Bind(R.id.txt_kucun)
    TextView txtKucun;
    @Bind(R.id.txt_reduce)
    TextView txtReduce;
    @Bind(R.id.id_product_num)
    EditText idProductNum;
    @Bind(R.id.txt_add)
    TextView txtAdd;
    @Bind(R.id.btn_enter)
    Button btnEnter;
    @Bind(R.id.txt_cancel)
    TextView txtCancel;
    @Bind(R.id.layout_content)
    RelativeLayout layoutContent;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_previewbuy;
    }

    @Override
    public void initPresenter() {

    }

    private Goods goods;
    private GoodsDetails goodDetails;

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        goods = (Goods) bundle.getSerializable("goods");
        goodDetails = (GoodsDetails) bundle.getSerializable("goodDetails");
        ImageLoaderUtils.display(getActivity(), imgProductIcon, goods.getGoodsMainPhoto());
        txtPrice.setText("S$" + goods.getGoodsPrice());
        txtKucun.setText("库存" + goodDetails.getGoodsInventory() + "件");
    }


    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.btn_enter)
    public void submit() {
        ConfirmOrderActivity.startAction(getActivity(), goodDetails, goods, Integer.parseInt(idProductNum.getText().toString().trim()));

    }

    @OnClick(R.id.txt_cancel)
    public void onBack() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.txt_add)
    public void add() {
        String trim = idProductNum.getText().toString().trim();
        int i = Integer.parseInt(trim);
        idProductNum.setText((i + 1) + "");
    }

    @OnClick(R.id.txt_reduce)
    public void reduce() {
        String trim = idProductNum.getText().toString().trim();
        int i = Integer.parseInt(trim);
        if (i == 1) {
            return;
        }
        idProductNum.setText((i - 1) + "");
    }


}
