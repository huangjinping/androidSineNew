package com.sineverything.news.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.kenya.view.flowlayout.FlowLayout;
import com.kenya.view.flowlayout.TagAdapter;
import com.kenya.view.flowlayout.TagFlowLayout;
import com.sineverything.news.R;
import com.sineverything.news.comm.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class SearchNewsActivity extends BaseActivity {


    @Bind(R.id.tag_layout)
    TagFlowLayout tagLayout;
    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.txt_action)
    TextView txtAction;
    @Bind(R.id.layout_search)
    LinearLayout layoutSearch;
    @Bind(R.id.lisv_seatch_resoult)
    ListView lisvSeatchResoult;
    private LayoutInflater mInflater;
    private List<String> dataSearchList;
    private ArrayAdapter<String> arrayAapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_searchcommodity;
    }

    @Override
    public void initPresenter() {


    }


    @Override
    protected void onResume() {
        super.onResume();
        getSearchList();
        arrayAapter.notifyDataSetChanged();

    }

    /**
     * 获取搜索数据
     */
    private void getSearchList() {
        List<String> searchHistory = null;
        searchHistory = UserManager.getSearchNewsHistory(this);
        dataSearchList.clear();
        if (searchHistory == null) {
            return;
        }
        Collections.reverse(searchHistory);
        if (searchHistory != null) {
            dataSearchList.addAll(searchHistory);
        }
    }

    @Override
    public void initView() {
        edtSearch.setEnabled(true);
        dataSearchList = new ArrayList<>();
        mInflater = LayoutInflater.from(this);
        tagLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {

                SearchNewsResultActivity.startActionWithKeywords(mContext, dataSearchList.get(i));

                return false;
            }
        });
        tagLayout.setAdapter(new TagAdapter<String>(dataSearchList) {
            @Override
            public View getView(FlowLayout flowLayout, int i, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv_flowlayout,
                        tagLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        txtAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edtSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    saveSearch(trim);
                    SearchNewsResultActivity.startActionWithKeywords(mContext, trim);
                }
            }
        });

        dataSearchList = new ArrayList<String>();
        arrayAapter = new ArrayAdapter<String>(this, R.layout.item_position_list, dataSearchList);
        lisvSeatchResoult.setAdapter(arrayAapter);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = edtSearch.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        return true;
                    }
                    // TODO: 16/4/7  搜索现象

                    saveSearch(content);

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


        lisvSeatchResoult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveSearch(dataSearchList.get(position));
                SearchNewsResultActivity.startActionWithKeywords(mContext, dataSearchList.get(position));

            }
        });

    }


    /**
     * 保存数据
     */
    private void saveSearch(String msg) {

        UserManager.saveSearchNews(this, msg);

    }


    @OnClick(R.id.ic_left_icon)
    public void onBack() {
        finish();
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchNewsActivity.class);
        context.startActivity(intent);
    }


}
