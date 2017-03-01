package com.xunfenqi.activity;

import android.view.View;
import android.widget.ListView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.MyZhangDanAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserMonthLoansDetailInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.AbPullToRefreshView;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixuebo on 16/11/23.
 */

public class MyZhangDanActivity extends BaseActivity {

    private AbPullToRefreshView ptrv;

    private int currentPage = 1;
    private LoadingLayout loadingLayout;
    private ListView lv;
    private MyZhangDanAdapter myZhangDanAdapter;

    // 数据
    private List<Map<String, Object>> list = null;
    private List<Map<String, Object>> newList = null;
    // 产品集合
    private List<UserMonthLoansDetailInfo.UserMonthLoansDetail> userMonthLoansDetailList;
    private UserMonthLoansDetailInfo.UserMonthLoansDetail userMonthLoansDetail;

    @Override
    public void initView() {
        setAbContentView(R.layout.activity_my_zhangdan);

        loadingLayout = (LoadingLayout) findViewById(R.id.loading_myinvite_act);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                refreshTask();
            }
        });
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myinvite_act);

        ptrv.setLoadMoreEnable(false);
        ptrv.setPullRefreshEnable(false);
        findViewById(R.id.iv_btn_myzhangdan_act_cjwt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MyZhangDanActivity.this,MessageActivity.class);
            }
        });

        lv = (ListView) findViewById(R.id.lv_myinvite_act);
    }

    @Override
    public void initData() {

        list = new ArrayList<Map<String, Object>>();


        myZhangDanAdapter = new MyZhangDanAdapter(MyZhangDanActivity.this, list,
                R.layout.list_item_my_zhangdan, new String[]{"tv_my_zhangdan_item_je",
                "tv_my_zhangdan_item_ddh", "tv_my_zhangdan_item_qs"},
                new int[]{R.id.tv_my_zhangdan_item_je, R.id.tv_my_zhangdan_item_ddh, R.id.tv_my_zhangdan_item_qs});
        lv.setAdapter(myZhangDanAdapter);
        doNetwork();

    }

    // 加载更多
    protected void loadMoreTask() {
        currentPage++;
        doNetwork();
    }

    // 刷新
    protected void refreshTask() {
        currentPage = 1;
        doNetwork();
    }

    private void doNetwork() {
        loadingLayout.setStatus(LoadingLayout.Loading);
        // AbDialogUtil.getWaitDialog(mActivity);

        HaiHeApi.userMonthLoansDetailDao( MyApplication
                .getInstance().getLoginUid(), new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                UserMonthLoansDetailInfo userMonthLoansDetailInfo = HaiheReturnApi
                        .userMonthLoansDetailDao(content);
                if (userMonthLoansDetailInfo != null) {
                    if ("000".equals(userMonthLoansDetailInfo.getRespCode())) {// 操作成功
                        // 判断总页数和当前页

                        // 处理数据
                        processData(userMonthLoansDetailInfo);

                    } else {
                        loadingLayout.setStatus(LoadingLayout.Error);
                        AbToastUtil.showToastInThread(MyZhangDanActivity.this,
                                userMonthLoansDetailInfo.getRespCodeDesc());
                    }
                }

                AbDialogUtil.removeDialog(MyZhangDanActivity.this);
            }

            @Override
            public void onFailure(int statusCode, final String content,
                                  Throwable error) {
                currentPage--;
                error.printStackTrace();
                AbToastUtil.showToastInThread(MyZhangDanActivity.this, error.getMessage());
                // 显示重试的框
                loadingLayout.setStatus(LoadingLayout.Error);
                AbDialogUtil.removeDialog(MyZhangDanActivity.this);
            }
        });
    }


    private void processData(UserMonthLoansDetailInfo userMonthLoansDetailInfo)

{
        newList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        userMonthLoansDetailList = userMonthLoansDetailInfo.getDataList();
        if (userMonthLoansDetailList != null) {
            for (int i = 0; i < userMonthLoansDetailList.size(); i++) {
                map = new HashMap<String, Object>();
                userMonthLoansDetail = userMonthLoansDetailList.get(i);
                map.put("item_fqid", userMonthLoansDetail.getFqid());
                map.put("item_je", userMonthLoansDetail.getJe());
                map.put("item_lsh", userMonthLoansDetail.getLsh());
                map.put("item_qs", userMonthLoansDetail.getQs());

                newList.add(map);
                if (newList != null && newList.size() > 0) {
                    list.addAll((List<Map<String, Object>>) newList);
                    myZhangDanAdapter.notifyDataSetChanged();
                    newList.clear();
                }
            }

            if (list.size() <= 0) {
                loadingLayout.setStatus(LoadingLayout.Empty);
                return;
            }
            ptrv.onHeaderRefreshFinish();
            ptrv.onFooterLoadFinish();
            loadingLayout.setStatus(LoadingLayout.Success);
        } else {
            // TODO 这里显示错误界面
            loadingLayout.setStatus(LoadingLayout.Success);
        }
    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的账单");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
