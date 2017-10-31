package com.sineverything.news.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.main.Comments;
import com.sineverything.news.bean.main.CommentsResponse;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.adpater.CommentChildAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/26.
 * email : huangjinping@hdfex.com
 */

public class CommmentFragemnt extends BaseFragment {
    @Bind(R.id.img_userPhoto)
    ImageView imgUserPhoto;
    @Bind(R.id.txt_userNickName)
    TextView txtUserNickName;
    @Bind(R.id.txt_content)
    TextView txtContent;
    @Bind(R.id.txt_time)
    TextView txtTime;
    @Bind(R.id.txt_Reply)
    TextView txtReply;
    @Bind(R.id.list_child_comment)
    ListView listChildComment;
    @Bind(R.id.edt_comment_content)
    EditText edtCommentContent;
    @Bind(R.id.txt_comment_submit)
    TextView txtCommentSubmit;
    @Bind(R.id.layout_rootComment)
    LinearLayout layoutRootComment;
    private User user;
    private CommentChildAdapter commentChildAdapter;
    private List<Comments> replys;
    public Comments comments;

    public static Fragment getInstance() {
        CommmentFragemnt fragemnt = new CommmentFragemnt();
        return fragemnt;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        user = UserManager.getUser(getActivity());
        Bundle bundle = getArguments();
        if (bundle.getSerializable(Comments.class.getSimpleName()) == null) {
            return;
        }
        comments = (Comments) bundle.getSerializable(Comments.class.getSimpleName());
        replys = new ArrayList<>();
        if (!TextUtils.isEmpty(comments.getUserNickName())) {
            txtUserNickName.setText(comments.getUserNickName());
        }
        if (!TextUtils.isEmpty(comments.getContent())) {
            txtContent.setText(comments.getContent());
        }
        if (!TextUtils.isEmpty(comments.getTime())) {
            txtTime.setText(comments.getTime());
        }
        commentChildAdapter = new CommentChildAdapter(getActivity(), replys);
        listChildComment.setAdapter(commentChildAdapter);
        List<Comments> replys = comments.getReplys();
        if (comments.getReplys() != null) {
            replys.addAll(comments.getReplys());
            commentChildAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.layout_rootComment)
    public void onRootClick() {

    }

    /**
     * 提交
     */
    @OnClick(R.id.txt_comment_submit)
    public void submitCommemt() {

        String commentContent = edtCommentContent.getText().toString().trim();
        if (!TextUtils.isEmpty(commentContent)) {
            addComment(comments.getCmsId(), commentContent);
        }
    }


    /**
     * 添加评论
     */
    private void addComment(String cmsId, String content) {

        OkHttpUtils.post()
                .tag(this)
                .addParams("cmsId", cmsId)
                .addParams("commentId", comments.getCommentId())
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
                                loadComments(LoadMode.NOMAL);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


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
                .addParams("cmsId", comments.getCmsId())
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
                                    replys.clear();
                                    page++;
                                }
                                replys.addAll(result);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                        commentChildAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
