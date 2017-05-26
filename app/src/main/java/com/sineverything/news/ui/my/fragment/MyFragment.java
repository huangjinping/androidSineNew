package com.sineverything.news.ui.my.fragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.activity.PhoneLoginActivity;

import butterknife.Bind;
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
    public void  phoneLogin(){
        PhoneLoginActivity.startAction(getActivity());
    }

}
