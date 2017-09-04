package com.sineverything.news.app;

import com.jaydenxiao.common.BuildConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {


    {
        PlatformConfig.setWeixin("wxa1b4bee5eb16a358", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("1106136249", "GJltJWCVN7T9VspF");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        Config.DEBUG = true;
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        UMShareAPI.get(this);


    }
}
