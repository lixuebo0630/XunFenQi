package com.xunfenqi.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.adapter.MyInviteAdapter;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.QueryUserInviteInfo;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
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

public class MyInviteActivity extends BaseActivity {

    private TextView tv_yqm, tv_wdhy, tv_zqhb;
    private UserCenterInfo loginUser;
    private AbPullToRefreshView ptrv;

    private int currentPage = 1;
    private LoadingLayout loadingLayout;
    private ListView lv;
    private MyInviteAdapter myInviteAdapter;

    // 数据
    private List<Map<String, Object>> list = null;
    private List<Map<String, Object>> newList = null;
    // 产品集合
    private List<QueryUserInviteInfo.UserInvite> userInviteList;
    private QueryUserInviteInfo.UserInvite userInvite;

    @Override
    public void initView() {
        setAbContentView(R.layout.activity_myinvite);
        tv_yqm = (TextView) findViewById(R.id.tv_myinvite_act_my_innum);
        tv_wdhy = (TextView) findViewById(R.id.tv_myinvite_act_wdhy);
        tv_zqhb = (TextView) findViewById(R.id.tv_myinvite_act_zqhb);
        loginUser = MyApplication.getInstance().getLoginUser();
        if (loginUser != null) {
            tv_yqm.setText(loginUser.getYqm());
            tv_wdhy.setText(loginUser.getWdhy() + " 人");
            tv_zqhb.setText(loginUser.getZqhb() + " 元");
        }

        loadingLayout = (LoadingLayout) findViewById(R.id.loading_myinvite_act);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                refreshTask();
            }
        });
        ptrv = (AbPullToRefreshView) findViewById(R.id.ptrv_myinvite_act);

        ptrv.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                refreshTask();
            }
        });
        ptrv.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                loadMoreTask();
            }
        });

        lv = (ListView) findViewById(R.id.lv_myinvite_act);
    }

    @Override
    public void initData() {

        list = new ArrayList<Map<String, Object>>();


        myInviteAdapter = new MyInviteAdapter(MyInviteActivity.this, list,
                R.layout.list_item_my_invite, new String[]{"tv_myinvite_item_wdhy",
                "tv_myinvite_item_zcqd", "tv_myinvite_item_zcrq"},
                new int[]{R.id.tv_myinvite_item_wdhy, R.id.tv_myinvite_item_zcqd, R.id.tv_myinvite_item_zcrq});
        lv.setAdapter(myInviteAdapter);
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

        HaiHeApi.queryUserInvite(currentPage, MyApplication
                .getInstance().getLoginUid(), new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                QueryUserInviteInfo queryProductListInfo = HaiheReturnApi
                        .queryUserInvite(content);
                if (queryProductListInfo != null) {
                    if ("000".equals(queryProductListInfo.getRespCode())) {// 操作成功
                        // 判断总页数和当前页
                        if (Integer.parseInt(queryProductListInfo
                                .getCurrentPage()) == Integer
                                .parseInt(queryProductListInfo.getPageCount())) {
                            ptrv.setLoadMoreEnable(false);
                            ptrv.getFooterView().hide();
                        } else {
                            ptrv.setLoadMoreEnable(true);
                        }
                        ptrv.onFooterLoadFinish();
                        // 处理数据
                        processData(queryProductListInfo, currentPage);

                    } else {
                        loadingLayout.setStatus(LoadingLayout.Error);
                        AbToastUtil.showToastInThread(MyInviteActivity.this,
                                queryProductListInfo.getRespCodeDesc());
                    }
                }

                AbDialogUtil.removeDialog(MyInviteActivity.this);
            }

            @Override
            public void onFailure(int statusCode, final String content,
                                  Throwable error) {
                currentPage--;
                error.printStackTrace();
                AbToastUtil.showToastInThread(MyInviteActivity.this, error.getMessage());
                // 显示重试的框
                loadingLayout.setStatus(LoadingLayout.Error);
                AbDialogUtil.removeDialog(MyInviteActivity.this);
            }
        });
    }


    private void processData(QueryUserInviteInfo queryUserInviteInfo,
                             int currentPage) {
        // 如果currentPage 是1,刷新数据
        if (currentPage == 1) {
            list.clear();
        }

        newList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        userInviteList = queryUserInviteInfo.getDataList();
        if (userInviteList != null) {
            for (int i = 0; i < userInviteList.size(); i++) {
                map = new HashMap<String, Object>();
                userInvite = userInviteList.get(i);
                map.put("item_yhm", userInvite.getYhm());
                map.put("item_zcqd", userInvite.getZcqd());
                map.put("item_zcrq", userInvite.getZcrq());

                newList.add(map);
                if (newList != null && newList.size() > 0) {
                    list.addAll((List<Map<String, Object>>) newList);
                    myInviteAdapter.notifyDataSetChanged();
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
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的邀请");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }
}
