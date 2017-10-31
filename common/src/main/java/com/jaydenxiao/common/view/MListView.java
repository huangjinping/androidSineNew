package com.jaydenxiao.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by longtaoge on 2016/3/24.
 */
public class MListView extends ListView {

    public MListView(Context context) {
        super(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 重新测量高度
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
