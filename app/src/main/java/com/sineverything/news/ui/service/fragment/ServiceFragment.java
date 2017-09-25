package com.sineverything.news.ui.service.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.bean.service.ChildMenu;
import com.sineverything.news.bean.service.ServiceMenu;
import com.sineverything.news.ui.service.activity.ServiceListActivity;
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

    //    SERVICE_INFO_LIST
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
            serviceMenu.setType("233");
            serviceMenu.setBackgroundColor(R.color.service_red);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("餐厅推荐","231"));
            list.add(new ChildMenu("美食介绍","234"));
            list.add(new ChildMenu("吃货报告","235"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);

        }

        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon1);
            serviceMenu.setTitle("旅游");
            serviceMenu.setType("237");
            serviceMenu.setBackgroundColor(R.color.service_yellow);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("热门景点","236"));
            list.add(new ChildMenu("旅游攻略","243"));
            list.add(new ChildMenu("周边游","244"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }


        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon2);
            serviceMenu.setTitle("教育");
            serviceMenu.setType("238");
            serviceMenu.setBackgroundColor(R.color.service_green);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("热门院校","239"));
            list.add(new ChildMenu("热门课程","240"));
            list.add(new ChildMenu("留学攻略","241"));
            list.add(new ChildMenu("教育资讯","242"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }


        {
            ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.setIcon(R.mipmap.ic_service_icon3);
            serviceMenu.setTitle("房产");
            serviceMenu.setType("245");
            serviceMenu.setBackgroundColor(R.color.service_blue);
            List<ChildMenu> list = new ArrayList<>();
            list.add(new ChildMenu("准证信息","246"));
            list.add(new ChildMenu("创业指南","247"));
            list.add(new ChildMenu("移民百科","248"));
            list.add(new ChildMenu("政策咨询","248"));
            serviceMenu.setList(list);
            dataList.add(serviceMenu);
        }

        adapter = new ServiceAdapter(dataList);
        recService.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setCallBack(new ServiceAdapter.CallBack() {

            @Override
            public void call(String type, String name) {
                ServiceListActivity.startAction(getActivity(),type,name);
            }
        });
        recService.setAdapter(adapter);
    }
//
//    /**
//     *
//     */
//    private void  getServiceData(){
//
//
//
//        OkHttpUtils.post()
//                .url(HostConstants.ORDER_SUBMIT)
//                .addParams("userId", user.getId())
//                .addParams("token", user.getToken())
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//
//            }
//
//            @Override
//            public void onBefore(Request request) {
//                super.onBefore(request);
//            }
//
//            @Override
//            public void onAfter() {
//                super.onAfter();
//                stopProgressDialog();
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                GoodsDetailsResponse detailsResponse = GsonUtil.changeGsonToBean(response, GoodsDetailsResponse.class);
//                if (detailsResponse != null) {
//                    if (isOkCode(detailsResponse.getCode(), detailsResponse.getMessage())) {
//                        GoodsDetails result = detailsResponse.getResult();
//
//                    }
//                }
//            }
//        });
//    }
}
