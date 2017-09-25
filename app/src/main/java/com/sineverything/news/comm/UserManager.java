package com.sineverything.news.comm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.bean.main.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class UserManager {
    private static final String LOGIN_USER = "LOGIN_USER";
    private static final int LIMITE_LENGTH = 10;
    private static final String SEARCH_HISTORY_NEW = "SEARCH_HISTORY_NEW";
    private static final String SEARCH_HISTORY_SP = "SEARCH_HISTORY_SP";



    /**
     * 获取信息
     *
     * @param context
     * @return
     */
    public static List<String> getSearchNewsHistory(Context context) {
        String resoult = PreferencesUtils.getString(context, SEARCH_HISTORY_NEW);
        if (TextUtils.isEmpty(resoult)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> list = gson.fromJson(resoult, type);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存信息
     *
     * @param context
     * @param name
     */
    public static void saveSearchNews(Context context, String name) {

        List<String> searchHistory = getSearchNewsHistory(context);
        if (searchHistory != null) {

            for (int i = 0; i < searchHistory.size(); i++) {
                if (searchHistory.get(i).equals(name)) {
                    searchHistory.remove(i);
                }
            }
        } else {
            searchHistory = new ArrayList<String>();
        }
        searchHistory.add(name);
        if (searchHistory.size() >= LIMITE_LENGTH) {
            searchHistory.remove(0);
        }
        PreferencesUtils.putString(context, SEARCH_HISTORY_NEW, new Gson().toJson(searchHistory));
    }
    /**
     * 保存信息
     *
     * @param context
     * @param name
     */
    public static void saveSearchName(Context context, String name) {

        List<String> searchHistory = getSearchHistory(context);
        if (searchHistory != null) {

            for (int i = 0; i < searchHistory.size(); i++) {
                if (searchHistory.get(i).equals(name)) {
                    searchHistory.remove(i);
                }
            }
        } else {
            searchHistory = new ArrayList<String>();
        }
        searchHistory.add(name);
        if (searchHistory.size() >= LIMITE_LENGTH) {
            searchHistory.remove(0);
        }
        PreferencesUtils.putString(context, SEARCH_HISTORY_SP, new Gson().toJson(searchHistory));
    }

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
     * 获取信息
     *
     * @param context
     * @return
     */
    public static List<String> getSearchHistory(Context context) {
        String resoult = PreferencesUtils.getString(context, SEARCH_HISTORY_SP);
        Log.d("hjp", "resoult" + resoult);
        if (TextUtils.isEmpty(resoult)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> list = gson.fromJson(resoult, type);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
