package com.sineverything.news.ui.my.fragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.my.activity.AboutUsActivity;
import com.sineverything.news.ui.my.activity.CollectionActivity;
import com.sineverything.news.ui.my.activity.FeedbackActivity;
import com.sineverything.news.ui.my.activity.HelpCenterActivity;
import com.sineverything.news.ui.my.activity.LoginActivity;
import com.sineverything.news.ui.my.activity.SettingActivity;
import com.sineverything.news.ui.my.activity.ShopCarActivity;
import com.sineverything.news.ui.order.activity.OrderListActivity;

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
    @Bind(R.id.rl_shop_car)
    RelativeLayout rlShopCar;
    @Bind(R.id.rl_my_conllection)
    RelativeLayout rlMyConllection;
    @Bind(R.id.rl_help_center)
    RelativeLayout rlHelpCenter;
    @Bind(R.id.rl_order_list)
    RelativeLayout rlOrderList;
    @Bind(R.id.txt_nickname)
    TextView txtNickname;
    @Bind(R.id.rl_setting)
    RelativeLayout rlSetting;

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
        updateName();

    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        updateName();

    }

    private void updateName() {
        if (txtNickname != null) {
            txtNickname.setText("");
            if (UserManager.isLogin(getActivity())) {
                User user = UserManager.getUser(getActivity());
                txtNickname.setText(user.getNickname());
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        updateName();

    }

    @OnClick(R.id.img_login_phone)
    public void phoneLogin() {

        if (!UserManager.isLogin(getActivity())) {
            LoginActivity.startAction(getActivity());
        }
//        PhoneLoginActivity.startAction(getActivity());
    }

    @OnClick(R.id.rl_shop_car)
    public void openShopCar() {
        if (UserManager.isLogin(getContext())){
            ShopCarActivity.startAction(getActivity());
        }else {
            LoginActivity.startAction(getContext());
        }

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



    @OnClick(R.id.rl_about)
    public void openAboutUs() {
        AboutUsActivity.startAction(getActivity());
    }


    @OnClick(R.id.rl_order_list)
    public void onOrderList() {

        if (UserManager.isLogin(getContext())){
            OrderListActivity.startAction(getActivity());
        }else {
            LoginActivity.startAction(getContext());
        }
    }


    @OnClick(R.id.rl_setting)
    public void onSetting() {
        SettingActivity.startAction(getActivity());
    }


}
