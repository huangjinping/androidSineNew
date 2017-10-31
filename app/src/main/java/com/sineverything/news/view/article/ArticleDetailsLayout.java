package com.sineverything.news.view.article;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.jaydenxiao.common.view.articlebase.ArticleDetailsViewGroup;
import com.jaydenxiao.common.view.articlebase.IBaseArticleLayout;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.NewsPhotoDetail;
import com.sineverything.news.bean.main.Picture;
import com.sineverything.news.ui.main.activity.NewsPhotoDetailActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiahongfei jiahongfeinew@163.com
 * @version V1.0.0
 * @Title: ArticleDetailsLayout.java
 * @Package hbyc.china.medical.view.modules.home.articleviewpager.fragment
 * @ClassName: ArticleDetailsLayout
 * @Description: 详情
 * @date 2015-7-13 下午3:23:22
 */
public class ArticleDetailsLayout extends LinearLayout implements IBaseArticleLayout {

    private Context mContext;
    private ArticleDetailWebView mArticleDetailWebView;
    private ViewGroup mArticleDetailsLayout;
    private MyWebViewClient client;
    private List<Picture> imageList = new ArrayList<Picture>();
    private String data;

    public ArticleDetailsLayout(Context context, ArticleDetailsViewGroup articleDetailsViewGroup) {
        super(context);
        mContext = context;
        LayoutInflater.from(mContext).inflate(
                R.layout.fragment_details_article, this, true);

        mArticleDetailsLayout = (ViewGroup) findViewById(R.id.articleDetailsLayout);

        mArticleDetailWebView = new ArticleDetailWebView(mContext);
        client = new MyWebViewClient(context, mArticleDetailWebView);

        mArticleDetailWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mArticleDetailWebView.loadUrl(url);
                return true;
            }
        });
        mArticleDetailWebView.addJavascriptInterface(new JavascriptInterface(context), "imagelistner");
        mArticleDetailWebView.setWebViewClient(client);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mArticleDetailWebView.setLayoutParams(layoutParams);
        mArticleDetailsLayout.removeAllViews();
        mArticleDetailsLayout.addView(mArticleDetailWebView);

    }

    public void setData(String data) {
        this.data = data;
        Document parse = Jsoup.parse(data);
        Elements elements1 = parse.select("body img");
        for (Element element : elements1) {
            String attr = element.select("img").attr("src");
            Picture picture = new Picture();
            picture.setImgSrc(attr);
            picture.setTitle("");
            imageList.add(picture);
        }
        String local = "file:///android_asset";
        mArticleDetailWebView.loadDataWithBaseURL(local, data, "text/html", "utf-8", null);
    }

    @Override
    public void setIsScroll(boolean isScroll) {
        mArticleDetailWebView.setIsScroll(isScroll);
    }

    @Override
    public boolean isScrollBottom() {
        return mArticleDetailWebView.isScrollBottom();
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

        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            super.onPageFinished(view, url);

            addImageClickListner(mArticleDetailWebView);

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

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
            NewsPhotoDetail detail = new NewsPhotoDetail();
            detail.setPictures(imageList);
            NewsPhotoDetailActivity.startAction(context, detail, index);
        }
    }
}
