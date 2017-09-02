package com.sineverything.news.ui.service.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.bean.service.ChildMenu;
import com.sineverything.news.bean.service.ServiceMenu;
import com.sineverything.news.ui.service.adapter.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class ServiceFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.rec_service)
    RecyclerView recService;
    private ServiceAdapter adapter;
    private List<ServiceMenu> dataList;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_service;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ntb.setBackVisibility(false);
        ntb.setTitleText(R.string.service);
        dataList = new ArrayList<>();

        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon0);
            serviceMenu.setTitle("美食");
            serviceMenu.setBackgroundColor(R.color.service_red);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("餐厅推荐"));
            list.add(new ChildMenu("美食介绍"));
            list.add(new ChildMenu("吃货报告"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);

        }

        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon1);
            serviceMenu.setTitle("旅游");
            serviceMenu.setBackgroundColor(R.color.service_yellow);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("热门景点"));
            list.add(new ChildMenu("旅游攻略"));
            list.add(new ChildMenu("周边游"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }


        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon2);
            serviceMenu.setTitle("教育");
            serviceMenu.setBackgroundColor(R.color.service_green);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("热门院校"));
            list.add(new ChildMenu("热门可能"));
            list.add(new ChildMenu("留学攻略"));
            list.add(new ChildMenu("教育资讯"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }


        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon2);
            serviceMenu.setTitle("工作移民");
            serviceMenu.setBackgroundColor(R.color.service_blue);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("准证信息"));
            list.add(new ChildMenu("创业指南"));
            list.add(new ChildMenu("移民百科"));
            list.add(new ChildMenu("政策咨询"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }

        adapter = new ServiceAdapter(dataList);
        recService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recService.setAdapter(adapter);
    }


}
