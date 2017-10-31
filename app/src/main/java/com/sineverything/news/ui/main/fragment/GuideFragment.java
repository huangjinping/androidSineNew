package com.sineverything.news.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sineverything.news.R;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.activity.MainActivity;
import com.sineverything.news.ui.my.activity.LoginActivity;

/**
 * author Created by harrishuang on 2017/9/26.
 * email : huangjinping@hdfex.com
 */

public class GuideFragment extends Fragment {


    private int type;
    private LinearLayout layout_submit;

    public static Fragment getInstance(int type) {
        GuideFragment fragment = new GuideFragment();
        fragment.type = type;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        switch (type) {
            case 0:
                view = inflater.inflate(R.layout.layout_guide1, container, false);
                break;
            case 1:
                view = inflater.inflate(R.layout.layout_guide2, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.layout_guide3, container, false);
                initView(view);
                break;
        }

        return view;
    }

    /**
     * 初始化数据
     *
     * @param view
     */
    private void initView(View view) {
        layout_submit = (LinearLayout) view.findViewById(R.id.layout_submit);
        layout_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

            }
        });
    }
}
