/**
 * Project Name:HaiHeFinance
 * File Name:HomeFragment.java
 * Package Name:com.haihefinance.fragment
 * Date:2015-8-20下午5:22:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.fragment.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.activity.H5Activity;
import com.xunfenqi.activity.WoDeZiLiaoActivity;
import com.xunfenqi.activity.WoYaoJieKuanActivity;
import com.xunfenqi.activity.XunTouTiaoActivity;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbFragment;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserIntoIndex;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.MarqueeView;
import com.xunfenqi.view.RollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * @date: 2015-8-20 下午5:22:09
 * @author: XueBo Li
 * @version:
 * @description:主界面--首页碎片
 * @see
 */
public class HomeFragment extends AbFragment implements OnClickListener {

    private Activity mActivity;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    // 传递图片对应的url地址的集合
    private List<UserIntoIndex.ImageUrl> urlImgList;
    // 放置点的结合
    private List<View> viewList = new ArrayList<View>();
    // 轮播图布局
    private View layout_roll_view;
    private LinearLayout ll_top_news_viewpager, dots_ll;
    private List<UserIntoIndex.Hhdt> newsList;
    private AbPullToRefreshView ptrv;
    private MarqueeView disLv;

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        mActivity = this.getActivity();
        initImageLoader(mActivity);
        newsList = new ArrayList<UserIntoIndex.Hhdt>();
        urlImgList = new ArrayList<UserIntoIndex.ImageUrl>();
        View view = initView(inflater);

        return view;
    }

    private View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_home, null);

        view.findViewById(R.id.bt_home_frag_ljsq).setOnClickListener(this);


        ptrv = (AbPullToRefreshView) view.findViewById(R.id.ptrv_home_frag);
        layout_roll_view = view.findViewById(R.id.rl_home_play_view);
        dots_ll = (LinearLayout) view.findViewById(R.id.dots_ll);
        layout_roll_view = view.findViewById(R.id.rl_home_play_view);
        disLv = (MarqueeView) view
                .findViewById(R.id.lv_frag_home_paoma);

        view.findViewById(R.id.tv_home_frag_news_more).setOnClickListener(this);
        ll_top_news_viewpager = (LinearLayout) view.findViewById(R.id.ll_top_news_viewpager);
        ptrv.setLoadMoreEnable(false);
        ptrv.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                refreshTask();
            }
        });

        disLv.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {

                Intent intent2 = new Intent(mActivity, H5Activity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("title", "讯头条");
                mBundle.putString("url",
                        newsList.get(position)
                                .getContenturl());
                intent2.putExtras(mBundle);
                mActivity.startActivity(intent2);
            }
        });
        this.setAbFragmentOnLoadListener(new AbFragmentOnLoadListener() {
            @Override
            public void onLoad() {
                showContentView();
            }
        });
        doNetwork();
        return view;
    }

    private void initData() {
        initDot();
        // 组装(将对应的view剖析出来，以后按照传递参数的方式直接去使用)
        RollViewPager rollViewPager = new RollViewPager(mActivity, viewList,
                new RollViewPager.onPageClick() {
                    @Override
                    public void onclick(UserIntoIndex.ImageUrl imageUrl) {
                        if (imageUrl != null) {
                            if ("1".equals(imageUrl.getFlag())) {
                                String h5url = imageUrl.getUrlh5();
//                                ActivityUtil.startActivityForStringData(
//                                        mActivity, "h5url",
//                                        ActivityDetailActivity.class, h5url);
                            } else {
                            }
                        }
                    }
                });

        //rollViewPager.setId(1985);
        rollViewPager.initImgUrlList(urlImgList);
        rollViewPager.startRoll();
        ll_top_news_viewpager.removeAllViews();
        ll_top_news_viewpager.addView(rollViewPager);
    }


    // 刷新
    protected void refreshTask() {
        doNetwork();
    }

    private void doNetwork() {
        // AbDialogUtil.getWaitDialog(mActivity);
        HaiHeApi.userIntoIndexTwo(MyApplication.getInstance().getLoginUid(), new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                AbDialogUtil.removeDialog(mActivity);
                final UserIntoIndex userIntoIndexInfo = HaiheReturnApi
                        .userIntoIndex(content);
                if (userIntoIndexInfo != null) {

                    if ("000".equals(userIntoIndexInfo.getRespCode())) {
                        urlImgList = userIntoIndexInfo.getLbUrlList();
                        ArrayList<String> titles = new ArrayList<String>();
                        newsList = userIntoIndexInfo.getNewsList();
                        for (UserIntoIndex.Hhdt hhdt : newsList) {
                            titles.add(hhdt.getTitle());
                        }

                        disLv.startWithList(titles);

                        initData();
                    } else {
                        AbToastUtil.showToastInThread(mActivity,
                                userIntoIndexInfo.getRespCodeDesc());
                    }
                } else {
                    AbToastUtil.showToastInThread(mActivity, "数据异常,请重新登录");
                }
                ptrv.onHeaderRefreshFinish();
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                error.printStackTrace();
                ptrv.onHeaderRefreshFinish();
                AbToastUtil.showToastInThread(mActivity, error.getMessage());
            }
        });

    }


//    Intent intent2 = new Intent(mActivity, H5Activity.class);
//    Bundle mBundle = new Bundle();
//                    mBundle.putString("title", "讯头条");
//                    mBundle.putString("url",
//                            newsList.get(arg2)
//            .getContenturl());
//                    intent2.putExtras(mBundle);
//                    mActivity.startActivity(intent2);

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            // .showImageOnLoading(R.drawable.ic_error_page)
            // .showImageOnFail(R.drawable.ic_error_page)
            .cacheInMemory(true).cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565).build();
    private Timer timer;


    private void initDot() {
        dots_ll.removeAllViews();
        viewList.clear();

        for (int i = 0; i < urlImgList.size(); i++) {
            View view = new View(mActivity);
            if (i == 0) {
                view.setBackgroundResource(R.drawable.dot_focus);
            } else {
                view.setBackgroundResource(R.drawable.dot_blur);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    UIUtils.dip2px(6), UIUtils.dip2px(6));
            view.setLayoutParams(layoutParams);
            layoutParams.setMargins(5, 0, 5, 0);
            dots_ll.addView(view);
            viewList.add(view);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_home_frag_ljsq:
                if (!MyApplication.getInstance().getLoginUser().getYhkrz().equals("0")) {
                    AbToastUtil.showToast(mActivity, AbConstant.SF_NOTIFY);
                    ActivityUtil.startActivity(mActivity, WoDeZiLiaoActivity.class);
                    return;
                }
                ActivityUtil.startActivity(mActivity, WoYaoJieKuanActivity.class);


                break;
            case R.id.tv_home_frag_news_more:

                ActivityUtil.startActivity(mActivity, XunTouTiaoActivity.class);


                break;
            default:
                break;

        }
    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove
                .build();
        ImageLoader.getInstance().init(config);
    }


    private void stopTimer() {
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
}
