package com.sineverything.news.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.ui.main.adpater.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class SearchNewsActivity extends BaseActivity {


    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.txt_action)
    TextView txtAction;
    @Bind(R.id.rl_search)
    RecyclerView rlSearch;

    List<NewsItem> dataList;
    SearchAdapter searchAdapter;
    @Bind(R.id.edt_search)
    EditText edtSearch;

    @Override
    public int getLayoutId() {
        return R.layout.act_searchnews;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        searchAdapter = new SearchAdapter(dataList);
        rlSearch.setLayoutManager(new LinearLayoutManager(this));
        rlSearch.setAdapter(searchAdapter);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = edtSearch.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        return true;
                    }
                    // TODO: 16/4/7  搜索现象

                    loadData();

                    ((InputMethodManager) edtSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    txtAction.setEnabled(true);
                } else {
                    txtAction.setEnabled(false);
                }
            }
        });
        txtAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }





    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchNewsActivity.class);
        context.startActivity(intent);

    }


    /**
     * 下载中央数据
     *
     * @param
     */
    private void loadData() {
        if (TextUtils.isEmpty(edtSearch.getText().toString())) {
            return;
        }

        OkHttpUtils.get()
                .url(HostConstants.SEARCH + edtSearch.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        startProgressDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        stopProgressDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {

                        try {
                            List<NewsItem> newsItems = GsonUtil.stringToArray(response, NewsItem[].class);
                            dataList.clear();
                            dataList.addAll(newsItems);
                            searchAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }


}
