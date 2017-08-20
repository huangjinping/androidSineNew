package com.sineverything.news;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.comm.WXConstants;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * author Created by harrishuang on 2017/8/17.
 * email : huangjinping@hdfex.com
 */

public class WXPayEntryActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initPresenter() {
        final IWXAPI wxapi = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID, true);
        boolean b = wxapi.registerApp(WXConstants.APP_ID);
//        if (!wxapi.isWXAppInstalled()) {
//            getmMvpView().showToast("手机未安装微信");
//            return;
//        }
//        if (!wxapi.isWXAppSupportAPI()) {
//            getmMvpView().showToast("微信版本不支持");
//            return;
//        }
//        if (req != null) {
//            wxapi.sendReq(req);
//            return;
//        }
    }

    @Override
    public void initView() {

    }
}
