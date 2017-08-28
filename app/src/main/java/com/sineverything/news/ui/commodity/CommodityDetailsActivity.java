package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.view.details.GradationScrollView;
import com.jaydenxiao.common.view.details.MyImageLoader;
import com.jaydenxiao.common.view.details.NoScrollListView;
import com.jaydenxiao.common.view.details.ScrollViewContainer;
import com.jaydenxiao.common.view.details.StatusBarUtil;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.activity.ShopCarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/8/6.
 * email : huangjinping@hdfex.com
 */

public class CommodityDetailsActivity extends BaseActivity implements GradationScrollView.ScrollViewListener {


    @Bind(R.id.iv_good_detai_img)
    ImageView ivGoodDetaiImg;
    @Bind(R.id.tv_good_detail_discount)
    TextView tvGoodDetailDiscount;
    @Bind(R.id.ll_offset)
    LinearLayout llOffset;
    @Bind(R.id.ll_good_detail_service)
    LinearLayout llGoodDetailService;
    @Bind(R.id.rl_good_detail_jin)
    RelativeLayout rlGoodDetailJin;
    @Bind(R.id.tv_good_detail_cate)
    TextView tvGoodDetailCate;
    @Bind(R.id.tv_talent_detail_open)
    TextView tvTalentDetailOpen;
    @Bind(R.id.nlv_)
    NoScrollListView nlv;
    @Bind(R.id.tv_good_detail_tuodong)
    TextView tvGoodDetailTuodong;
    @Bind(R.id.scrollview)
    GradationScrollView scrollview;
    @Bind(R.id.nlv_good_detial_imgs)
    NoScrollListView nlvGoodDetialImgs;
    @Bind(R.id.tv_good_detail_daodi)
    TextView tvGoodDetailDaodi;
    @Bind(R.id.sv_container)
    ScrollViewContainer svContainer;
    @Bind(R.id.iv_good_detai_back)
    ImageView ivGoodDetaiBack;
    @Bind(R.id.tv_good_detail_title_good)
    TextView tvGoodDetailTitleGood;
    @Bind(R.id.iv_good_detai_shop)
    ImageView ivGoodDetaiShop;
    @Bind(R.id.iv_good_detai_share)
    ImageView ivGoodDetaiShare;
    @Bind(R.id.ll_good_detail)
    RelativeLayout llGoodDetail;
    @Bind(R.id.tv_good_detail_shop)
    TextView tvGoodDetailShop;
    @Bind(R.id.iv_good_detai_collect_unselect)
    ImageView ivGoodDetaiCollectUnselect;
    @Bind(R.id.iv_good_detai_collect_select)
    ImageView ivGoodDetaiCollectSelect;
    @Bind(R.id.ll_good_detail_collect)
    LinearLayout llGoodDetailCollect;
    @Bind(R.id.tv_good_detail_shop_cart)
    TextView tvGoodDetailShopCart;
    @Bind(R.id.tv_good_detail_buy)
    TextView tvGoodDetailBuy;
    @Bind(R.id.ll_good_detail_bottom)
    LinearLayout llGoodDetailBottom;
    private QuickAdapter<String> imgAdapter;
    private List<String> imgsUrl;

    private int height;
    private int width;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commoditydetails;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
//透明状态栏
        StatusBarUtil.setTranslucentForImageView(this, llOffset);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llOffset.getLayoutParams();
        params1.setMargins(0, -StatusBarUtil.getStatusBarHeight(this) / 4, 0, 0);
        llOffset.setLayoutParams(params1);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivGoodDetaiImg.getLayoutParams();
        params.height = getScreenHeight(this) * 2 / 3;
        ivGoodDetaiImg.setLayoutParams(params);

        svContainer = new ScrollViewContainer(getApplicationContext());


        initImgDatas();

        initListeners();
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, CommodityDetailsActivity.class);
        context.startActivity(intent);
    }

    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    // TODO: 16/8/21 模拟图片假数据
    private void initImgDatas() {
        width = getScreenWidth(getApplicationContext());
        imgsUrl = new ArrayList<>();
        imgsUrl.add("https://img.alicdn.com/imgextra/i4/714288429/TB2dLhGaVXXXXbNXXXXXXXXXXXX-714288429.jpg");
        imgsUrl.add("https://img.alicdn.com/imgextra/i3/726966853/TB2vhJ6lXXXXXbJXXXXXXXXXXXX_!!726966853.jpg");
        imgsUrl.add("https://img.alicdn.com/imgextra/i4/2081314055/TB2FoTQbVXXXXbuXpXXXXXXXXXX-2081314055.png");
        imgAdapter = new QuickAdapter<String>(this, R.layout.adapter_good_detail_imgs) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width / 2;
                iv.setLayoutParams(params);
                MyImageLoader.getInstance().displayImageCen(getApplicationContext(), item, iv, width, width / 2);
            }
        };
        imgAdapter.addAll(imgsUrl);
        nlvGoodDetialImgs.setAdapter(imgAdapter);
    }

    private void initListeners() {

        ViewTreeObserver vto = ivGoodDetaiImg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llGoodDetail.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = ivGoodDetaiImg.getHeight();
                scrollview.setScrollViewListener(CommodityDetailsActivity.this);
            }
        });
    }

    /**
     * 滑动监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llGoodDetail.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            tvGoodDetailTitleGood.setTextColor(Color.argb((int) alpha, 1, 24, 28));
            llGoodDetail.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            llGoodDetail.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }


    @OnClick(R.id.tv_good_detail_shop_cart)
    public void toShopCar(){
        ShopCarActivity.startAction(this);
    }

}