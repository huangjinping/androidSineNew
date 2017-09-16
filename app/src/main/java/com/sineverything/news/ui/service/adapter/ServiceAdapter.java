package com.sineverything.news.ui.service.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.bean.service.ChildMenu;
import com.sineverything.news.bean.service.ServiceMenu;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.adapter.HomeTopMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int MENU_ONE = 1;

    private static final int MENU_TWO = 2;

    private static final int MENU_Three = 3;

    private List<ServiceMenu> dataList;
    final List<Classify> mClassify = new ArrayList<>();
    private Context context;
    private Resources resources;


    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public ServiceAdapter(List<ServiceMenu> dataList) {
        this.dataList = dataList;
        mClassify.add(new Classify("餐厅推荐", R.mipmap.ic_service_menu1, "231"));
        mClassify.add(new Classify("热门景点", R.mipmap.ic_service_menu2, "236"));
        mClassify.add(new Classify("热门院校", R.mipmap.ic_service_menu3, "239"));
        mClassify.add(new Classify("创业指南", R.mipmap.ic_service_menu4, "240"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (MENU_ONE == viewType) {
            context = parent.getContext();
            resources = context.getResources();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_one, parent, false);
            return new ViewHolderOne(view);
        } else if (MENU_TWO == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_two, parent, false);
            return new ViewHolderTwo(view);

        } else if (MENU_Three == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_three, parent, false);
            return new ViewHolderThree(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderOne) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            Context context = viewHolderOne.rec_service_menu.getContext();
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 4);
            viewHolderOne.rec_service_menu.setLayoutManager(mLayoutManager);
            ServiceTopAdapter mAdapter = new ServiceTopAdapter(context, mClassify);
            viewHolderOne.rec_service_menu.setAdapter(mAdapter);
            mAdapter.setmListener(new MyItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    if (callBack != null) {
                        Classify classify = mClassify.get(postion);
                        callBack.call(mClassify.get(postion).getType(),classify.getTitle());
                    }
                }
            });

        }

        if (holder instanceof ViewHolderTwo) {
            ServiceMenu serviceMenu = dataList.get(position - 1);
            ViewHolderTwo viewholder = (ViewHolderTwo) holder;
            viewholder.layout_service_menu.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
            viewholder.txt_service_title.setText(serviceMenu.getTitle());
            viewholder.img_icon.setImageResource(serviceMenu.getIcon());
            List<ChildMenu> list = serviceMenu.getList();

            setOnclick(viewholder.layout_service_menu, serviceMenu.getType(),serviceMenu.getTitle());


            if (list.size() == 3) {
                ChildMenu childMenu = list.get(0);
                viewholder.txt_child_menu1.setText(childMenu.getTitle());
                viewholder.txt_child_menu1.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                viewholder.txt_child_menu2.setVisibility(View.GONE);
                setOnclick(viewholder.txt_child_menu1, childMenu.getType(),childMenu.getTitle());


                ChildMenu childMenu1 = list.get(1);
                viewholder.txt_child_menu3.setText(childMenu1.getTitle());
                viewholder.txt_child_menu3.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu3, childMenu1.getType(),childMenu1.getTitle());


                ChildMenu childMenu2 = list.get(2);
                viewholder.txt_child_menu4.setText(childMenu2.getTitle());
                viewholder.txt_child_menu4.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu4, childMenu2.getType(),childMenu2.getTitle());


            } else if (list.size() == 4) {
                ChildMenu childMenu = list.get(0);
                viewholder.txt_child_menu1.setText(childMenu.getTitle());
                viewholder.txt_child_menu1.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu1, childMenu.getType(),childMenu.getTitle());


                ChildMenu childMenu1 = list.get(1);
                viewholder.txt_child_menu2.setText(childMenu1.getTitle());
                viewholder.txt_child_menu2.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu2, childMenu1.getType(),childMenu1.getTitle());


                viewholder.txt_child_menu2.setVisibility(View.VISIBLE);


                ChildMenu childMenu2 = list.get(2);
                viewholder.txt_child_menu3.setText(childMenu2.getTitle());
                viewholder.txt_child_menu3.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu3, childMenu2.getType(),childMenu2.getTitle());


                ChildMenu childMenu3 = list.get(3);
                viewholder.txt_child_menu4.setText(childMenu3.getTitle());
                viewholder.txt_child_menu4.setBackgroundColor(resources.getColor(serviceMenu.getBackgroundColor()));
                setOnclick(viewholder.txt_child_menu4, childMenu3.getType(),childMenu3.getTitle());

            }
        }

    }

    /**
     * L
     *
     * @param view
     * @param type
     */
    private void setOnclick(View view, final String type,final  String title) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.call(type,title);
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MENU_ONE;
        } else if (position == dataList.size() + 1) {
            return MENU_Three;
        } else {
            return MENU_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 2;
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView rec_service_menu;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rec_service_menu = (RecyclerView) rootView.findViewById(R.id.rec_service_menu);
        }

    }


    public static class ViewHolderThree extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_bannner;

        public ViewHolderThree(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_bannner = (ImageView) rootView.findViewById(R.id.img_bannner);
        }

    }


    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_service_title;
        public ImageView img_icon;
        public TextView txt_child_menu1;
        public TextView txt_child_menu2;
        public TextView txt_child_menu3;
        public TextView txt_child_menu4;
        public RelativeLayout layout_service_menu;


        public ViewHolderTwo(View rootView) {
            super(rootView);

            this.rootView = rootView;
            this.txt_service_title = (TextView) rootView.findViewById(R.id.txt_service_title);
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
            this.txt_child_menu1 = (TextView) rootView.findViewById(R.id.txt_child_menu1);
            this.txt_child_menu2 = (TextView) rootView.findViewById(R.id.txt_child_menu2);
            this.txt_child_menu3 = (TextView) rootView.findViewById(R.id.txt_child_menu3);
            this.txt_child_menu4 = (TextView) rootView.findViewById(R.id.txt_child_menu4);
            this.layout_service_menu = (RelativeLayout) rootView.findViewById(R.id.layout_service_menu);
        }
    }

    /**
     *
     */
    public interface CallBack {
        void call(String type,String  name);
    }
}
