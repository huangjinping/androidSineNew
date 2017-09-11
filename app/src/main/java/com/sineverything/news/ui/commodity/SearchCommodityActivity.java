package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.kenya.view.flowlayout.FlowLayout;
import com.kenya.view.flowlayout.TagAdapter;
import com.kenya.view.flowlayout.TagFlowLayout;
import com.sineverything.news.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class SearchCommodityActivity extends BaseActivity {


    @Bind(R.id.tag_layout)
    TagFlowLayout tagLayout;
    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.layout_search)
    LinearLayout layoutSearch;
    @Bind(R.id.txt_action)
    TextView txtAction;
    private LayoutInflater mInflater;
    private List<String> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_searchcommodity;
    }

    @Override
    public void initPresenter() {


    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        dataList.add("袜子");
        dataList.add("裴元庆");
        dataList.add("大明王");
        dataList.add("东方");
        dataList.add("袜子");
        dataList.add("宇文化及");
        dataList.add("张绍忠");
        dataList.add("莫哈默德.拖拉机");
        dataList.add("张狂因");
        dataList.add("阿米尔汗");
        mInflater = LayoutInflater.from(this);
        tagLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {

                CommodityActivity.startActionWithKeywords(mContext, dataList.get(i));

                return false;
            }
        });
        tagLayout.setAdapter(new TagAdapter<String>(dataList) {
            @Override
            public View getView(FlowLayout flowLayout, int i, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv_flowlayout,
                        tagLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        edtSearch.setEnabled(true);
        txtAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edtSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    CommodityActivity.startActionWithKeywords(mContext, trim);
                }
            }
        });

    }

    @OnClick(R.id.ic_left_icon)
    public void onBack(){
        finish();
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchCommodityActivity.class);
        context.startActivity(intent);
    }


}
