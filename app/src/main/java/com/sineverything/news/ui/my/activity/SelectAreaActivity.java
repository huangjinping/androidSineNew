package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.jaydenxiao.common.utils.RegexUtils;
import com.jaydenxiao.common.view.widget.MyLetterListView;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.Nations;
import com.sineverything.news.bean.main.NationsResponse;
import com.sineverything.news.ui.my.adapter.AreaAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/2.
 * email : huangjinping@hdfex.com
 */

public class SelectAreaActivity extends BaseActivity {


    @Bind(R.id.ic_nav)
    NormalTitleBar icNav;
    @Bind(R.id.sh)
    EditText sh;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.search_result)
    ListView searchResult;
    @Bind(R.id.tv_noresult)
    TextView tvNoresult;
    @Bind(R.id.letterListView)
    MyLetterListView letterListView;
    private AreaAdapter areaAdapter;
    private AreaAdapter searchAdapter;
    private List<Nations> dataList;
    private List<Nations> resultList;


    public static void startAction(Context context) {
        Intent intent = new Intent(context, SelectAreaActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_selectarea;
    }

    @Override
    public void initPresenter() {
        sh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString().trim();
                if (TextUtils.isEmpty(temp)) {
                    searchResult.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    return;
                }

                if (RegexUtils.chinese(temp)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        Nations nations = dataList.get(i);

                        Log.d("okhttp","==chinese=="+nations.toString());
                        if (nations.getNationCn().contains(temp)) {
                            resultList.add(nations);
                        }
                    }
                } else if (RegexUtils.english(temp)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        Nations nations = dataList.get(i);
                        if (nations.getNationEn().contains(temp)) {
                            resultList.add(nations);
                        }
                        Log.d("okhttp","==english=="+nations.toString());

                    }
                }
                if (resultList.size() > 0) {
                    searchResult.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    searchAdapter.notifyDataSetChanged();
                } else {
                    searchResult.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 下载数据
     */
    private void loadData() {
        startProgressDialog();
        OkHttpUtils.post().url(HostConstants.GET_NATIONS)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                stopProgressDialog();

            }

            @Override
            public void onResponse(String response) {
                NationsResponse nationsResponse = GsonUtil.changeGsonToBean(response, NationsResponse.class);
                if (isOkCode(nationsResponse.getCode(), nationsResponse.getMessage())) {
                    List<Nations> result = nationsResponse.getResult();
                    dataList.clear();
                    dataList.addAll(result);
                    areaAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initView() {
        letterListView.setOnTouchingLetterChangedListener(new MyLetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {

                for (int i = 0; i < dataList.size(); i++) {
                    Nations nations = dataList.get(i);
                    if (nations.getNationShort().startsWith(s)){
                        listView.setSelection(i);
                        return;
                    }
                }
            }
        });

        icNav.setTitleText("选择城市");
        dataList = new ArrayList<>();
        resultList = new ArrayList<>();
        searchAdapter = new AreaAdapter(this, resultList);
        areaAdapter = new AreaAdapter(this, dataList);
        /**
         *显示的
         */
        listView.setAdapter(areaAdapter);
        searchResult.setAdapter(searchAdapter);
        loadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nations nations = dataList.get(position);
                RxBus.getInstance().post("area",nations);
                finish();
            }
        });
        searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nations nations = resultList.get(position);
                RxBus.getInstance().post("area",nations);
                finish();
            }
        });

    }
}
