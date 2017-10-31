package com.sineverything.news.view.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.view.articlebase.ArticleDetailsViewGroup;
import com.jaydenxiao.common.view.articlebase.IBaseCommentLayout;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.Comments;
import com.sineverything.news.ui.main.adpater.CommsAdapter;

import java.util.List;

/**
 * @version V1.0.0
 * @Description: 评论列表
 */
public class ArticleCommentLayout extends LinearLayout implements
        View.OnClickListener, IBaseCommentLayout {
    private Context mContext;
    protected CommentListView mPullToRefreshListView;
    private CommsAdapter mArticleAdapter;
    private List<Comments> mArticleComments;
    private ArticleDetailsViewGroup articleDetailsViewGroup;
    private boolean flag;

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public ArticleCommentLayout(Context context, ArticleDetailsViewGroup articleDetailsViewGroup, List<Comments> mArticleComments) {
        super(context);
        mContext = context;
        this.articleDetailsViewGroup = articleDetailsViewGroup;
        this.mArticleComments = mArticleComments;
        afterViews();
    }


    public void afterViews() {
        LayoutInflater.from(mContext).inflate(
                R.layout.fragment_article_comment, this, true);
        mPullToRefreshListView = (CommentListView) findViewById(R.id.articleListView);
        mPullToRefreshListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mPullToRefreshListView.setDividerHeight(0);
        mPullToRefreshListView.addFooterView(addFooterView());
        mArticleAdapter = new CommsAdapter(mContext, mArticleComments);

        mPullToRefreshListView.setAdapter(mArticleAdapter);
        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!articleDetailsViewGroup.isScrollerFinished()) {
                    return;
                }
                Toast.makeText(mContext, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        mPullToRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i) {
                    case SCROLL_STATE_IDLE:
                        flag = isListViewReachBottomEdge(absListView);
                        if (flag) {
                            if (callBack != null) {

                                viewHolder.progress_bar.setVisibility(VISIBLE);
                                viewHolder.txt_load_message.setText("加载中......");
                                callBack.loadMode();
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });


    }


    public boolean isListViewReachBottomEdge(final AbsListView listView) {
        boolean result = false;
        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
            final View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
            result = (listView.getHeight() >= bottomChildView.getBottom());
        }
        ;
        return result;
    }

    @Override
    public boolean isFirstViewTop() {
        return mPullToRefreshListView
                .isFirstViewTop();
    }

    @Override
    public void setIsScroll(boolean isScroll) {
        mPullToRefreshListView
                .setIsScroll(isScroll);
    }

    @Override
    public void onClick(View v) {

    }

    public void notifyDataSetChanged() {
        mArticleAdapter.notifyDataSetChanged();

    }

    ViewHolder viewHolder;

    private View addFooterView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_addmore, null);
        viewHolder = new ViewHolder(view);
        return view;
    }

    public void endLoadMore() {
        viewHolder.progress_bar.setVisibility(GONE);
        viewHolder.txt_load_message.setText("加载完成");
    }

    public void showNoLoadMore() {
        viewHolder.progress_bar.setVisibility(GONE);
        viewHolder.txt_load_message.setText("暂无更多");
    }

    public class ViewHolder {
        public View rootView;
        public ProgressBar progress_bar;
        public TextView txt_load_message;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.progress_bar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
            this.txt_load_message = (TextView) rootView.findViewById(R.id.txt_load_message);
        }

    }


    public interface CallBack {
        void loadMode();

    }

}
