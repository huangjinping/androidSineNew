package com.sineverything.news.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.jaydenxiao.common.view.articlebase.ArticleDetailsViewGroup;
import com.sineverything.news.R;
import com.sineverything.news.api.HTMLConstants;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.main.Comments;
import com.sineverything.news.bean.main.CommentsResponse;
import com.sineverything.news.bean.main.NewsDetails;
import com.sineverything.news.bean.main.NewsDetailsResponse;
import com.sineverything.news.bean.main.Picture;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.ShareProcess;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.fragment.CommmentFragemnt;
import com.sineverything.news.ui.my.activity.LoginActivity;
import com.sineverything.news.view.article.ArticleCommentLayout;
import com.sineverything.news.view.article.ArticleDetailsLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import rx.Subscriber;

/**
 * author Created by harrishuang on 2017/8/6.
 * email : huangjinping@hdfex.com
 */

public class NewsActivity extends BaseActivity {

    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.articleDetailsViewGroup)
    ArticleDetailsViewGroup articleDetailsViewGroup;
    ArticleDetailsLayout articleDetailsLayout;
    ArticleCommentLayout articleCommentLayout;
    @Bind(R.id.edt_comment_content)
    EditText edtCommentContent;
    @Bind(R.id.txt_comment_submit)
    TextView txtCommentSubmit;
    @Bind(R.id.layout_content_view)
    LinearLayout layoutContentView;
    @Bind(R.id.roov_view)
    LinearLayout roovView;
    @Bind(R.id.btn_message_count)
    Button btnMessageCount;
    @Bind(R.id.layout_comments)
    LinearLayout layoutComments;
    private String id;
    private List<Comments> commentsList;
    private ShareProcess process;
    private User user;


    @Override
    public int getLayoutId() {
        return R.layout.act_news;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = UserManager.getUser(this);
    }

    @Override
    public void initView() {
        commentsList = new ArrayList<>();
        ArticleDetailsViewGroup articleDetailsViewGroup = (ArticleDetailsViewGroup) findViewById(R.id.articleDetailsViewGroup);
        articleDetailsLayout = new ArticleDetailsLayout(this, articleDetailsViewGroup);
        articleCommentLayout = new ArticleCommentLayout(this, articleDetailsViewGroup, commentsList);
        articleCommentLayout.setCallBack(new ArticleCommentLayout.CallBack() {
            @Override
            public void loadMode() {
                loadComments(LoadMode.UP_REFRESH);
            }
        });
        articleDetailsViewGroup.addArticleView(articleDetailsLayout);
        articleDetailsViewGroup.addCommentView(articleCommentLayout);
        articleDetailsViewGroup.requestLayout();
        id = getIntent().getStringExtra("id");
        process = new ShareProcess(this);
        loadData(id);
        loadComments(LoadMode.NOMAL);
        navBar.setTitleText(R.string.news_details);
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navBar.setRightImagSrc(R.drawable.grey_share);
        navBar.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process.shotAllView(mContext, roovView, new View[]{layoutContentView});
            }
        });
        RxBus.getInstance().register(Comments.class.getSimpleName()).subscribe(new Subscriber<Object>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Comments coments = (Comments) o;
                showFragment(coments);
            }
        });
    }

    public static void startAction(Context context, String id) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    /**
     * 文件数据
     * @param id
     */
    private void loadData(String id) {
        OkHttpUtils.post()
                .url(HostConstants.NEWDETAILS).addParams("cmsId", id)
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
                            NewsDetailsResponse details = GsonUtil.changeGsonToBean(response, NewsDetailsResponse.class);
                            NewsDetails result = details.getResult();
                            String data = result.getContent();
                            String htmlData = getHtmlData(data);
                            try {
                                if (!TextUtils.isEmpty(result.getHits())) {
                                    btnMessageCount.setVisibility(View.VISIBLE);
                                    btnMessageCount.setText(result.getHits() + "");
                                } else {
                                    btnMessageCount.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String title = "<div style=\"background-color: white;margin: 10px auto\">\n" +
                                    "    <h1 style=\"margin: 10px 10px;font-size: 20px\">\n" +
                                    result.getTitle() +
                                    "    </h1>\n" +
                                    "    <div style=\"margin: 10px 10px\"><span style=\"color: deepskyblue;\">" + result.getSource() + "</span><span>" + result.getCreateTime() + "</span>\n" +
                                    "    </div>\n" +
                                    "</div>";

                            articleDetailsLayout.setData(HTMLConstants.head + title + htmlData + HTMLConstants.footer);
                            Document parse = Jsoup.parse(data);
                            Elements elements1 = parse.select("body img");
                            for (Element element : elements1) {
                                String attr = element.select("img").attr("src");
                                Picture picture = new Picture();
                                picture.setImgSrc(attr);
                                picture.setTitle("");
//                                imageList.add(picture);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    /**
     * @param cmsId
     */

    private int page = 1;

    private void loadComments(final LoadMode loadMode) {
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }

        if (loadMode == LoadMode.NOMAL) {
            startProgressDialog();
        }
        OkHttpUtils.post()
                .tag(this)
                .addParams("cmsId", id)
                .url(HostConstants.COMMENTS)
                .addParams("pageIndex", page + "")
                .addParams("pageSize", "10")
                .build()
                .execute(new StringCallback() {
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
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            CommentsResponse newsItemResponse = GsonUtil.changeGsonToBean(response, CommentsResponse.class);
                            if (isOkCode(newsItemResponse.getCode(), newsItemResponse.getMessage())) {
                                List<Comments> result = newsItemResponse.getResult();
                                if (loadMode != LoadMode.UP_REFRESH) {
                                    commentsList.clear();
                                    page++;
                                } else {
                                    articleCommentLayout.endLoadMore();
                                }
                                commentsList.addAll(result);
                                articleCommentLayout.notifyDataSetChanged();
                            } else {
                                articleCommentLayout.showNoLoadMore();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }
                });
    }


    /**
     * 提交
     */
    @OnClick(R.id.txt_comment_submit)
    public void submitCommemt() {
        if (!UserManager.isLogin(this)) {
            LoginActivity.startAction(this);
            return;
        }
        String commentContent = edtCommentContent.getText().toString().trim();
        if (!TextUtils.isEmpty(commentContent)) {
            addComment(id, commentContent);
        }
    }


    /**
     * 添加评论
     */
    private void addComment(String cmsId, String content) {
        if (!UserManager.isLogin(this)) {
            LoginActivity.startAction(this);
            return;
        }
        OkHttpUtils.post()
                .tag(this)
                .addParams("cmsId", cmsId)
                .addParams("commentId", "")
                .addParams("content", content)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
                .url(HostConstants.COMMENTS_ADD)
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

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        stopProgressDialog();
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                            if (isOkCode(response1.getCode(), response1.getMessage())) {
                                edtCommentContent.setText("");
                                loadComments(LoadMode.NOMAL);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 开启页面
     */
    private void showFragment(Comments comments) {
        Fragment fragment = CommmentFragemnt.getInstance();
        Bundle bundle = new Bundle();
        comments.setCmsId(id);
        bundle.putSerializable(Comments.class.getSimpleName(), comments);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragment_up_enter,
                R.anim.fragment_down_outer,
                R.anim.fragment_up_enter,
                R.anim.fragment_down_outer);
        transaction.replace(R.id.layout_comments, fragment);
        transaction.addToBackStack("CommmentFragemnt");
        transaction.commit();
    }


}
