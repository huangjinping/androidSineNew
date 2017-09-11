package com.sineverything.news.comm;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.bean.main.User;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class UserManager {
    private static final String LOGIN_USER = "LOGIN_USER";


    /**
     * 保存信息
     *
     * @param user
     */
    public static void saveUser(Context context, User user) {
        if (user == null) {
            return;
        }

//        Hawk.put(LOGIN_USER, user);

        PreferencesUtils.putString(context, LOGIN_USER, new Gson().toJson(user));
    }


    /**
     * 获取用户信息
     *
     * @param context
     */
    public static User getUser(Context context) {
        User user = null;
        String resoult = PreferencesUtils.getString(context, LOGIN_USER);
        if (TextUtils.isEmpty(resoult)) {
            return user;
        }
        try {
            user = GsonUtil.changeGsonToBean(resoult, User.class);
//            Hawk.get(LOGIN_USER);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    /**
     * 退出登录
     */
    public static void logout(Context context) {


        User user = getUser(context);
        if (user != null) {
            user.setToken("");
            saveUser(context, user);
        }
    }

    /**
     * 判断是不是
     *
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        User user = getUser(context);
        if (user == null) {
            return false;
        }
        if (TextUtils.isEmpty(user.getToken())) {
            return false;
        }
        return true;
    }


}
