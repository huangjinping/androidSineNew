package com.sineverything.news.ui.commodity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.kenya.view.flowlayout.FlowLayout;
import com.kenya.view.flowlayout.TagAdapter;
import com.kenya.view.flowlayout.TagFlowLayout;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.GoodsDetails;
import com.sineverything.news.bean.commodity.SpecsChild;
import com.sineverything.news.bean.commodity.SpecsParent;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.my.activity.ShopCarActivity;
import com.sineverything.news.ui.order.activity.ConfirmOrderActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/12.
 * email : huangjinping@hdfex.com
 */

public class PreViewBuyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.layout_back)
    LinearLayout layoutBack;
    @Bind(R.id.img_product_icon)
    ImageView imgProductIcon;
    @Bind(R.id.txt_price)
    TextView txtPrice;
    @Bind(R.id.txt_kucun)
    TextView txtKucun;
    @Bind(R.id.txt_reduce)
    TextView txtReduce;
    @Bind(R.id.id_product_num)
    EditText idProductNum;
    @Bind(R.id.txt_add)
    TextView txtAdd;
    @Bind(R.id.btn_enter)
    Button btnEnter;
    @Bind(R.id.txt_cancel)
    TextView txtCancel;
    @Bind(R.id.layout_content)
    RelativeLayout layoutContent;
    @Bind(R.id.layout_specifications)
    LinearLayout layoutSpecifications;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private User user;
    public String status;

    public static final String STATUS_SHAPCART = "STATUS_SHAPCART";
    public static final String STATUS_COMMODITY = "STATUS_COMMODITY";

    String goodsGspIds = "";
    String goodsGspVal = "";
    public static PreViewBuyFragment getInstance(String status) {
        PreViewBuyFragment preViewBuyFragment = new PreViewBuyFragment();
        preViewBuyFragment.status = status;
        return preViewBuyFragment;
    }

    private List<TagFlowLayout> viewList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_previewbuy;
    }

    @Override
    public void initPresenter() {

    }

    private Goods goods;
    private GoodsDetails goodDetails;

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(getActivity());
        viewList = new ArrayList<>();
        user = UserManager.getUser(getActivity());
        Bundle bundle = getArguments();
        goods = (Goods) bundle.getSerializable("goods");
        goodDetails = (GoodsDetails) bundle.getSerializable("goodDetails");
        ImageLoaderUtils.display(getActivity(), imgProductIcon, goods.getGoodsMainPhoto());
        txtPrice.setText("S$" + goods.getGoodsPrice());
        txtKucun.setText("库存" + goodDetails.getGoodsInventory() + "件");
        if (goodDetails.getSpecsList() != null) {
            layoutSpecifications.removeAllViews();
            for (int i = 0; i < goodDetails.getSpecsList().size(); i++) {
                SpecsParent specsParent = goodDetails.getSpecsList().get(i);
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_specifications, layoutSpecifications, false);
                viewHolder = new ViewHolder(view);
                viewHolder.txt_specsName.setText(specsParent.getSpecsName());
                viewList.add(viewHolder.tag_specifications);
                viewHolder.tag_specifications.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int i, FlowLayout flowLayout) {

                        return true;
                    }
                });


                viewHolder.tag_specifications.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

                    @Override
                    public void onSelected(Set<Integer> set) {


                    }
                });

                viewHolder.tag_specifications.setAdapter(new TagAdapter<SpecsChild>(specsParent.getSpecsPros()) {

                    @Override
                    public View getView(FlowLayout flowLayout, int i, SpecsChild specsChild) {
                        TextView tv = (TextView) mInflater.inflate(R.layout.tv_specifications,
                                viewHolder.tag_specifications, false);
                        Log.d("okhttp", specsChild.toString());
                        tv.setText(specsChild.getSpecsProName());
                        return tv;
                    }
                });
                layoutSpecifications.addView(view);
            }
        }
    }

    /**
     * 是否选择了规格
     *
     * @return
     */
    private boolean isSelectedGsp() {
        List<SpecsParent> specsList = goodDetails.getSpecsList();

        for (int i = 0; i < viewList.size(); i++) {
            Set<Integer> selectedList = viewList.get(i).getSelectedList();
            if (selectedList != null && selectedList.size() > 0) {

            } else {
                showLongToast("请选择" + specsList.get(i).getSpecsName());
                return false;

            }
        }

        return true;

    }


    /**
     * 获取数据问题
     */
    private String getGspListString() {
        StringBuffer buffer = new StringBuffer();
        List<SpecsParent> specsList = goodDetails.getSpecsList();
        for (int i = 0; i < viewList.size(); i++) {
            Set<Integer> selectedList = viewList.get(i).getSelectedList();
            if (selectedList != null && selectedList.size() > 0) {
                String specsProId = specsList.get(i).getSpecsPros().get(selectedList.iterator().next()).getSpecsProId();
                buffer.append(specsProId);
                buffer.append(",");
            }
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }


    /**
     * 获取数据问题
     */
    private String getGspNameList() {


        StringBuffer buffer = new StringBuffer();

        List<SpecsParent> specsList = goodDetails.getSpecsList();
        for (int i = 0; i < viewList.size(); i++) {
            SpecsParent specsParent = specsList.get(i);
            Set<Integer> selectedList = viewList.get(i).getSelectedList();
            if (selectedList != null && selectedList.size() > 0) {
                String specsProName = specsList.get(i).getSpecsPros().get(selectedList.iterator().next()).getSpecsProName();
                buffer.append(specsParent.getSpecsName() + ":" + specsProName);
                buffer.append(" ");
            }
        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }


//      showLongToast(buffer.toString());


        return buffer.toString();
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.btn_enter)
    public void submit() {
        if (!isSelectedGsp()) {
            return;
        }
        goodsGspIds = getGspListString();
        goodsGspVal=  getGspNameList();
        getGoodsGsp();

    }


    /**
     * 获取
     */
    private void getGoodsGsp() {



        startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.GET_GOODS_GSP)
                .addParams("userId", user.getId())
                .addParams("goodsId", goodDetails.getGoodsId())
                .addParams("gspIds", goodsGspIds)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                stopProgressDialog();
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();

            }

            @Override
            public void onResponse(String response) {
                Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                if (isOkCode(response1.getCode(), response1.getMessage())) {


                    if (PreViewBuyFragment.STATUS_SHAPCART.equals(status)) {
                        addGoodsCart();


                    } else {
                        goods.setGoodsGspIds(goodsGspIds);
                        goods.setGoodsGspVal(goodsGspVal);
                        ConfirmOrderActivity.startAction(getActivity(), goodDetails, goods, Integer.parseInt(idProductNum.getText().toString().trim()));
                    }
                } else {
                    stopProgressDialog();
                }
            }
        });
    }


//    userId 用户id
//    goodsId 商品id
//    count 商品数量
//    gsp: 规格属性id 用逗号分隔

    /**
     * 添加购物车
     */
    private void addGoodsCart() {

        OkHttpUtils.post()
                .url(HostConstants.ADD_GOODSCART)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
                .addParams("goodsId", goodDetails.getGoodsId())
                .addParams("count", idProductNum.getText().toString().trim())
                .addParams("gsp", getGspListString())
                .addParams("gspName", getGspNameList())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

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
            public void onResponse(String response) {
                Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                if (response1 != null) {
                    if (isOkCode(response1.getCode(), response1.getMessage())) {
                        ShopCarActivity.startAction(getActivity());
                    }
                }
            }
        });
    }


    @OnClick(R.id.txt_cancel)
    public void onBack() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.txt_add)
    public void add() {
        String trim = idProductNum.getText().toString().trim();
        int i = Integer.parseInt(trim);
        idProductNum.setText((i + 1) + "");
    }

    @OnClick(R.id.txt_reduce)
    public void reduce() {
        String trim = idProductNum.getText().toString().trim();
        int i = Integer.parseInt(trim);
        if (i == 1) {
            return;
        }
        idProductNum.setText((i - 1) + "");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class ViewHolder {
        public View rootView;
        public TextView txt_specsName;
        public TagFlowLayout tag_specifications;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.txt_specsName = (TextView) rootView.findViewById(R.id.txt_specsName);
            this.tag_specifications = (TagFlowLayout) rootView.findViewById(R.id.tag_specifications);
        }

    }
}
