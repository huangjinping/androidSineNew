package com.sineverything.news.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.commonwidget.details.DetailListView;
import com.jaydenxiao.common.commonwidget.details.DetailScrollView;
import com.jaydenxiao.common.commonwidget.details.DetailWebView;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HTMLConstants;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.main.Comments;
import com.sineverything.news.bean.main.CommentsResponse;
import com.sineverything.news.bean.main.NewsDetails;
import com.sineverything.news.bean.main.NewsDetailsResponse;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.bean.main.NewsPhotoDetail;
import com.sineverything.news.bean.main.Picture;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.ShareProcess;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.adpater.CommsAdapter;
import com.sineverything.news.ui.my.activity.LoginActivity;

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

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class NewsDetailsActivity extends BaseActivity {

    @Bind(R.id.webview)
    DetailWebView webview;
    @Bind(R.id.list_view)
    DetailListView listView;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;
    @Bind(R.id.scrollView)
    DetailScrollView scrollView;
    @Bind(R.id.tvReply)
    TextView tvReply;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.roov_view)
    LinearLayout roovView;
    @Bind(R.id.edt_comment_content)
    EditText edtCommentContent;
    @Bind(R.id.txt_comment_submit)
    TextView txtCommentSubmit;
    private int mFirstVisibleItem;
    private int mWebViewHeight;
    private int mScreenHeight;
    private int mLastY;
    private boolean isMoving = false;
    private MyWebViewClient client;
    private CommsAdapter commsAdapter;
    private List<Comments> commentsList;
    private int mListScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
    private List<Picture> imageList = new ArrayList<Picture>();
    private ShareProcess process;
    private User user;
    private String id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_newdetails;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        user = UserManager.getUser(this);
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        id = getIntent().getStringExtra("id");
        process = new ShareProcess(this);
        initWebView();
        initListView();
        initScrollView();
        loadData(id);
        loadComments(LoadMode.PULL_REFRSH);
    }

    public static void startAction(Context context, String id) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        user=UserManager.getUser(this);
    }

    private void initWebView() {

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
                process.shotAllView(mContext, roovView, new View[]{scrollView});
            }
        });

        client = new MyWebViewClient(this, webview);
        //webview 高度自动撑开
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
//        url = "https://news-node.seeyouyima.com/article?news_id=1817909&platform=android&v=5.9.1&tbuid=&statinfo=eyJvdCI6IiIsInVhIjoiTGUgWDYyMCIsIm9zIjoiMiIsIm9zdmVyc2lvbiI6IjYuMCIsInNka3ZlcnNpb24iOjIzLCJvcGVudWRpZCI6IjRkZDcwM2VlLTcwNGQtNDZmNy1hNGM1LTU0OTcxYTMyMTI2YiIsImFwbiI6IjQiLCJ1aWQiOjQ5MzQ3NjI4LCJidWlsZHYiOiI1LjkuMSIsIm1hYyI6Ijg2MjM4MDAzOTY0NTExNyIsImFuZHJvaWRpZCI6ImE5NmFmZTJjYzgzMjJhN2QiLCJpbWVpIjoiODYyMzgwMDM5NjQ1MTE3Iiwic291cmNlIjoiU2VleW91QWN0aXZpdHktPk5ld3NEZXRhaWxINUFjdGl2aXR5IiwiY2hhbm5lbGlkIjoiMTExMSIsInYiOiI1LjkuMSJ9&device_id=862380039645117&imei=862380039645117&mode=0&sdkversion=23&bundleid=1111&app_id=1&myclient=0130591111100000&myuid=49347628&auth=3.4BWmwQT1nK5qyKA3W9oyCfPhPihHumJ3l%2BcbuBVupwI%3D";
        webview.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
//        webview.loadUrl(url);
        webview.setWebViewClient(client);


    }


    private void initScrollView() {
        scrollView.setChildListView(listView);
        scrollView.setScanScrollChangedListener(new DetailScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom(int vericalY) {
                //到底的时候,事件交给listview,此时,需要让scrollview惯性滚动一下,没滚动完之前,不运行scrollview拦截
                if (!listView.isHandleTouchEvent() && vericalY < 0 && !scrollView.isTouchingScrollView()) {
                    handleListViewTouchEvent();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        listView.fling(Math.abs(vericalY / 3));
                    } else {
                        listView.startFling(Math.abs(vericalY / 3)/*5000*/);
                    }
                    listView.setHandleTouchEvent(true);
                    return;
                }
                handleListViewTouchEvent();

            }

            @Override
            public void onScrolledToTop() {

            }
        });
        scrollView.setMoveListener(new DetailScrollView.onMoveListener() {
            //当按下scrollview的时候,如果listview还在fling,强制重置它的位置,并抢夺事件;
            @Override
            public void onDown() {

                if (listView != null && mListScrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    listView.stopFling();
                    listView.setSelectionFromTop(0, 1);
                    listView.setHandleTouchEvent(false);
                }
            }

            //ListView过渡到ScrollView的时候,需要再惯性让ScrollView再滚动一下
            @Override
            public void onUp(int velocityY) {

                if (isMoving) {
                    isMoving = false;
                    if (scrollView != null && isWebViewOverScreen()) {
                        scrollView.fling(velocityY);
                    }
                }
                handleListViewTouchEvent();
                //防止自带弹性效果的scrollview 计算不准确,再计算一次
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handleListViewTouchEvent();
                    }
                }, 1000);

            }

            @Override
            public void onMove(float distance) {
                if (listView != null && mListScrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    listView.stopFling();
                    listView.setSelectionFromTop(0, 1);
                    listView.setHandleTouchEvent(false);
                    mListScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
                }
            }
        });
    }


    //webview是否超过可见范围,也就是,是否可滚动
    private boolean isWebViewOverScreen() {
        return mWebViewHeight > (mScreenHeight - getTopAndBottomHeight()) ? true : false;
    }

    private void initListView() {
        //创建ArrayList对象；
//        list = new ArrayList<HashMap<String, String>>();
        commentsList = new ArrayList<>();
        commsAdapter = new CommsAdapter(this, commentsList);
        listView.setAdapter(commsAdapter);
//        TextView textView = new TextView(this);
//        textView.setText("我是底部");
//        listView.addFooterView(textView);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mListScrollState = scrollState;
                int lastIndex = listView.getAdapter().getCount() - 1;
                //滚动到底部
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getLastVisiblePosition() == lastIndex) {
                    appendListView();
                    handleListViewTouchEvent();
                    return;
                }
                //滚动到顶部
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && mFirstVisibleItem == 0 && listView.getChildAt(0).getTop() == 0) {
                    if (scrollView != null)
                        scrollView.smoothScrollBy(0, -5);
                    listView.setHandleTouchEvent(false);
                    return;
                }
                //停止的时候,进行事件决定
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    handleListViewTouchEvent();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mFirstVisibleItem = firstVisibleItem;
                //Log.d(TAG,"onScroll firstVisibleItem:"+firstVisibleItem+"==>visibleItemCount:"+visibleItemCount+"==totalItemCount:"+totalItemCount);
            }
        });
        int height = getListViewHeight();
        listView.getLayoutParams().height = height;
        listView.requestLayout();
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //触摸的时候,让父控件不要拦截我的所有事件
                if (scrollView != null && listView.isHandleTouchEvent()) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        scrollView.requestDisallowInterceptTouchEvent(false);
                    } else {
                        scrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                return false;
            }
        });
        listView.setMoveListener(new DetailListView.onMoveListener() {
            @Override
            public void onMove(float distance) {
                //listview在顶部的时候,往下滑动,此时需要scrollview跟着一起滚动,进行过渡
                if (scrollView != null && listView != null && listView.getChildCount() > 0 && getListViewPositionAtScreen() >= getTopHeight() && distance > 0 && mFirstVisibleItem == 0 && listView.getChildAt(0).getTop() == 0) {
                    // 滚动
                    scrollView.smoothScrollBy(0, -(int) distance);
                    // 取消listview的事件消费,会在其onTouchEvent返回false
                    listView.setHandleTouchEvent(false);
                    isMoving = true;
                }
            }

            //拖动scrollview一起滚动之后,在松开的时候,需要有个惯性滚动
            @Override
            public void onUp(final int velocityY) {
                if (isMoving) {
                    isMoving = false;
                    //延迟一下fling才有效果。
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (scrollView != null)
                                scrollView.fling(-velocityY);
                        }
                    }, 50);
                }
            }
        });
    }

    private int getListViewHeight() {
        int value = getResources().getDisplayMetrics().heightPixels - getTopAndBottomHeight();
        return value;
    }

    private int getTopHeight() {
        return dip2px(getApplicationContext(), 50 + 25);
        //状态栏+标题栏
    }

    private int getTopAndBottomHeight() {
        return getTopHeight() + dip2px(getApplicationContext(), 45);
        //状态栏+标题栏+回复栏
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int getListViewPositionAtScreen() {
        try {
            int[] location = new int[2];
            listView.getLocationInWindow(location);
            int y = location[1];
            return y;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    //停止后,决定事件是由谁来处理的;
    private void handleListViewTouchEvent() {
        try {
            if (listView == null)
                return;
            if (listView.getChildCount() > 0 && getListViewPositionAtScreen() > getTopHeight()) {
                listView.setHandleTouchEvent(false);
            } else {
                listView.setHandleTouchEvent(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
                            String local = "file:///android_asset";
                            webview.loadDataWithBaseURL(local, HTMLConstants.head + htmlData + HTMLConstants.footer, "text/html", "utf-8", null);
                            Document parse = Jsoup.parse(data);
                            Elements elements1 = parse.select("body img");
                            for (Element element : elements1) {
                                String attr = element.select("img").attr("src");
                                Picture picture = new Picture();
                                picture.setImgSrc(attr);
                                picture.setTitle("");
                                imageList.add(picture);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }

    /**
     * body 数据问题
     * @param bodyHTML
     * @return
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
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
                        stopProgressDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
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
                .addParams("pageSize", "30")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
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
                                }
                                commentsList.addAll(result);
                                commsAdapter.notifyDataSetChanged();

                                Log.d("okhttp",commentsList.toString());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }


    class MyWebViewClient extends WebViewClient {

        private static final String TAG = "MyWebViewClient";

        private Context mContext;
        private WebView mwebView;

        public MyWebViewClient(Context context, WebView webView) {
            this.mContext = context;
            this.mwebView = webView;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);


            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            startProgressDialog();
        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            super.onPageFinished(view, url);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWebViewHeight = view.getHeight();
                }
            }, 100);
            addImageClickListner(webview);
            stopProgressDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);
            stopProgressDialog();
        }

        private void addImageClickListner(WebView webView) {

            webView.loadUrl("javascript:(function(){"
                    + "var objs = document.getElementsByTagName(\"img\"); "
                    + "for(var i=0;i<objs.length;i++)  " + "{"
                    + "    objs[i].onclick=function()  " + "    {  "
                    + "        window.imagelistner.openImage(this.src);  "
                    + "    }  " + "}" + "})()");
        }


    }

    public class JavascriptInterface {

        private Context context;

        public JavascriptInterface(Context context) {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img) {
            int index = 0;
            for (int i = 0; i < imageList.size(); i++) {
                if (imageList.get(i).getImgSrc().equals(img)) {
                    index = i;
                    break;
                }
            }
            Toast.makeText(context, "openImage", Toast.LENGTH_SHORT).show();

            NewsPhotoDetail detail = new NewsPhotoDetail();
            detail.setPictures(imageList);

            Log.d("okhttp", imageList.toString());

            NewsPhotoDetailActivity.startAction(NewsDetailsActivity.this, detail, index);


        }
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


    private void appendListView() {
        loadComments(LoadMode.UP_REFRESH);
//        Toast.makeText(getApplicationContext(), "正在加载更多", Toast.LENGTH_SHORT).show();
//        int count = list.size();
//        for (int i = count; i < 20 + count; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();       //为避免产生空指针异常，有几列就创建几个map对象
//            map.put("id", "userId:  " + i);
//            map.put("name", "userName：" + i);
//            list.add(map);
//        }
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
