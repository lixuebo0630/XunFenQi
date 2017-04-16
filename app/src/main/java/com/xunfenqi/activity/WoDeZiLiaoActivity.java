/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class WoDeZiLiaoActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_sfrz, tv_lxrxxrz, tv_jdrz, tv_xxwrz, tv_sjfwmrz;
    private RelativeLayout rl_sfrz, rl_lxrrz, rl_jdrz, rl_xxwrz, rl_sjfwmrz;

    @Override
    public void initView() {

        setAbContentView(R.layout.activity_myziliao);

        rl_sfrz = (RelativeLayout) findViewById(R.id.rl_btn_my_ziliao_act_sfrz);
        rl_lxrrz = (RelativeLayout) findViewById(R.id.rl_btn_my_ziliao_act_fqxx);
        rl_jdrz = (RelativeLayout) findViewById(R.id.rl_btn_my_ziliao_act_jdrz);
        rl_xxwrz = (RelativeLayout) findViewById(R.id.rl_btn_my_ziliao_act_xxwrz);
        rl_sjfwmrz = (RelativeLayout) findViewById(R.id.rl_btn_my_ziliao_act_sjfwmrz);

        rl_sfrz.setOnClickListener(this);
        rl_lxrrz.setOnClickListener(this);
        rl_jdrz.setOnClickListener(this);
        rl_xxwrz.setOnClickListener(this);
        rl_sjfwmrz.setOnClickListener(this);

        tv_sfrz = (TextView) findViewById(R.id.tv_my_ziliao_act_sfrz);
        tv_lxrxxrz = (TextView) findViewById(R.id.tv_my_ziliao_act_lxrxxrz);
        tv_jdrz = (TextView) findViewById(R.id.tv_my_ziliao_act_jdrz);
        tv_xxwrz = (TextView) findViewById(R.id.tv_my_ziliao_act_xxwrz);
        tv_sjfwmrz = (TextView) findViewById(R.id.tv_my_ziliao_act_sjfwmrz);

    }

    @Override
    public void initData() {

        UserCenterInfo loginUser = MyApplication.getInstance().getLoginUser();
        String jdrz = loginUser.getJdrz();

        String lxr = loginUser.getLxrrz();
        String wxwrz = loginUser.getWxwrz();
        String sjfwm = loginUser.getSjfwmrz();
        String yhkrz = loginUser.getYhkrz();


        if ("0".equals(jdrz)) {
           // rl_jdrz.setEnabled(false);
            tv_jdrz.setText("已认证");
            tv_jdrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_jdrz.setTextColor(getResources().getColor(R.color.content_base));

        }
        if ("0".equals(lxr)) {
            //rl_lxrrz.setEnabled(false);
            tv_lxrxxrz.setText("已认证");
            tv_lxrxxrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_lxrxxrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(wxwrz)) {
          //  rl_xxwrz.setEnabled(false);
            tv_xxwrz.setText("已认证");
            tv_xxwrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_xxwrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(sjfwm)) {
           // rl_sjfwmrz.setEnabled(false);
            tv_sjfwmrz.setText("已认证");
            tv_sjfwmrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_sjfwmrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(yhkrz)) {
            rl_sfrz.setEnabled(false);
            tv_sfrz.setText("已认证");
            tv_sfrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_sfrz.setTextColor(getResources().getColor(R.color.content_base));
        }

    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "我的资料");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_btn_my_ziliao_act_sfrz:
                showDialog();
                break;
            case R.id.rl_btn_my_ziliao_act_fqxx:

                ActivityUtil.startActivity(this, LianXiRenActivity.class);

                break;
            case R.id.rl_btn_my_ziliao_act_jdrz:


                ActivityUtil.startActivityForStringData(this, "rzFlag", RZActivity.class, "1");
                break;
            case R.id.rl_btn_my_ziliao_act_xxwrz:
                ActivityUtil.startActivityForStringData(this, "rzFlag", RZActivity.class, "3");

                break;
            case R.id.rl_btn_my_ziliao_act_sjfwmrz:
                ActivityUtil.startActivityForStringData(this, "rzFlag", RZActivity.class, "4");

                break;

            default:
                break;
        }
    }

    private void showDialog() {
        list.clear();

        ListView listView = new ListView(this);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListData()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) {//学生
                    ActivityUtil.startActivityForStringData(WoDeZiLiaoActivity.this, "flag", ShenFenRZActivity.class, "学生");
                } else {
                    ActivityUtil.startActivityForStringData(WoDeZiLiaoActivity.this, "flag", ShenFenRZActivity.class, "成人");

                }
                AbDialogUtil.removeDialog(WoDeZiLiaoActivity.this);

            }
        });
        AbDialogUtil.showAlertDialog(listView);
    }


    private ArrayList<String> list = new ArrayList<String>();

    private List<String> getListData() {

        list.add("学生");
        list.add("成人");
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();


        UserCenterInfo loginUser = MyApplication.getInstance().getLoginUser();
        String jdrz = loginUser.getJdrz();

        String lxr = loginUser.getLxrrz();
        String wxwrz = loginUser.getWxwrz();
        String sjfwm = loginUser.getSjfwmrz();
        String yhkrz = loginUser.getYhkrz();


        if ("0".equals(jdrz)) {
            rl_jdrz.setEnabled(false);
            tv_jdrz.setText("已认证");
            tv_jdrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_jdrz.setTextColor(getResources().getColor(R.color.content_base));

        }
        if ("0".equals(lxr)) {
            rl_lxrrz.setEnabled(false);
            tv_lxrxxrz.setText("已认证");
            tv_lxrxxrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_lxrxxrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(wxwrz)) {
            rl_xxwrz.setEnabled(false);
            tv_xxwrz.setText("已认证");
            tv_xxwrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_xxwrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(sjfwm)) {
            rl_sjfwmrz.setEnabled(false);
            tv_sjfwmrz.setText("已认证");
            tv_sjfwmrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_sjfwmrz.setTextColor(getResources().getColor(R.color.content_base));
        }
        if ("0".equals(yhkrz)) {
            rl_sfrz.setEnabled(false);
            tv_sfrz.setText("已认证");
            tv_sfrz.setBackgroundResource(R.drawable.shape_base_line_btn);
            tv_sfrz.setTextColor(getResources().getColor(R.color.content_base));
        }


    }
}
