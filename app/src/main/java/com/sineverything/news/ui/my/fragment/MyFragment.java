package com.sineverything.news.ui.my.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.commodity.ClassifyActivity;
import com.sineverything.news.ui.my.activity.CollectionActivity;
import com.sineverything.news.ui.my.activity.FeedbackActivity;
import com.sineverything.news.ui.my.activity.HelpCenterActivity;
import com.sineverything.news.ui.my.activity.PhoneLoginActivity;
import com.sineverything.news.ui.my.activity.ShopCarActivity;
import com.sineverything.news.ui.order.activity.OrderListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class MyFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.img_login_phone)
    ImageView imgLoginPhone;
    @Bind(R.id.img_login_wechat)
    ImageView imgLoginWechat;
    @Bind(R.id.img_login_qq)
    ImageView imgLoginQq;
    @Bind(R.id.ll_unlogin)
    LinearLayout llUnlogin;
    @Bind(R.id.img_user_icon)
    ImageView imgUserIcon;
    @Bind(R.id.ll_login)
    LinearLayout llLogin;
    @Bind(R.id.rl_curriculum_vitae)
    RelativeLayout rlCurriculumVitae;
    @Bind(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @Bind(R.id.rl_browse_history)
    RelativeLayout rlBrowseHistory;
    @Bind(R.id.rl_feed_back)
    RelativeLayout rlFeedBack;
    @Bind(R.id.rl_about)
    RelativeLayout rlAbout;
    @Bind(R.id.rl_shop_car)
    RelativeLayout rlShopCar;
    @Bind(R.id.rl_my_conllection)
    RelativeLayout rlMyConllection;
    @Bind(R.id.rl_help_center)
    RelativeLayout rlHelpCenter;
    @Bind(R.id.rl_order_list)
    RelativeLayout rlOrderList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ntb.setTitleText(R.string.my);
        ntb.setBackVisibility(false);
    }

    @OnClick(R.id.img_login_phone)
    public void phoneLogin() {
        PhoneLoginActivity.startAction(getActivity());
    }


    @OnClick(R.id.rl_shop_car)
    public void openShopCar() {
        ShopCarActivity.startAction(getActivity());
    }


    @OnClick(R.id.rl_my_conllection)
    public void openMyConllection() {
        CollectionActivity.startAction(getActivity());
    }

    @OnClick(R.id.rl_feed_back)
    public void openFeedBack() {
        FeedbackActivity.startAction(getActivity());
    }

    @OnClick(R.id.rl_help_center)
    public void openHelpCenter() {
        HelpCenterActivity.startAction(getActivity());
    }


    @OnClick(R.id.rl_order_list)
   public void  onOrderList(){
        ClassifyActivity.startAction(getActivity());
   }
}
