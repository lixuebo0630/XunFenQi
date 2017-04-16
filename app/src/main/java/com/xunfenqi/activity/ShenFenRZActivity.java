/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.model.domain.UserIdentityAffirmInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 *
 */
public class ShenFenRZActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_name, et_bank_num, et_bank_name;
    private EditText et_id_num;
    private EditText et_rxsj;
    private EditText et_xxdz;
    private EditText et_xxmc;
    private EditText et_ykt;
    private TextView tv_xl;
    private String qx;
    private LinearLayout ll_stu_flag;
    private Button bt_commit;
    private String flag;
    private String userName;
    private String id_num;
    private String ykt;
    private String xxmc;
    private String rxsj;
    private String xxdz;
    private String bankName;
    private String bankNum;
    private String xl;

    @Override
    public void initView() {


        flag = getIntent().getStringExtra("flag");
        setAbContentView(R.layout.activity_my_sfrz);

        et_name = (EditText) findViewById(R.id.et_my_sfrz_act_name);
        et_id_num = (EditText) findViewById(R.id.et_my_sfrz_act_id_num);
        et_rxsj = (EditText) findViewById(R.id.et_my_sfrz_act_rxsj);
        et_xxdz = (EditText) findViewById(R.id.et_my_sfrz_act_xxdz);
        et_xxmc = (EditText) findViewById(R.id.et_my_sfrz_act_xxmc);
        et_bank_num = (EditText) findViewById(R.id.et_my_sfrz_act_bank_num);
        et_bank_name = (EditText) findViewById(R.id.et_my_sfrz_act_bank_name);
        et_ykt = (EditText) findViewById(R.id.et_my_sfrz_act_ykt);
        tv_xl = (TextView) findViewById(R.id.tv_my_sfrz_act_xl);
        ll_stu_flag = (LinearLayout) findViewById(R.id.ll_sfrz_act_stu_flag);
        bt_commit = (Button) findViewById(R.id.bt_sfrz_act_commit);


        if ("成人".equals(flag)) {
            ll_stu_flag.setVisibility(View.GONE);
        } else if ("学生".equals(flag)) {
            ll_stu_flag.setVisibility(View.VISIBLE);
            et_xxdz.setHint("请输入学校详细地址");

        }
        tv_xl.setOnClickListener(this);
        bt_commit.setOnClickListener(this);


    }

    @Override
    public void initData() {

    }


    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "身份认证");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
                UIUtils.dip2px(14));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_sfrz_act_xl:
                showDialog();
                break;
            case R.id.bt_sfrz_act_commit:
                doCommit();
                break;

            default:
                break;
        }
    }

    private void doCommit() {
        userName = et_name.getText().toString().trim();
        bankName = et_bank_name.getText().toString().trim();
        bankNum = et_bank_num.getText().toString().trim();

        id_num = et_id_num.getText().toString().trim();
        ykt = et_ykt.getText().toString().trim();
        xxmc = et_xxmc.getText().toString().trim();
        rxsj = et_rxsj.getText().toString().trim();
        xxdz = et_xxdz.getText().toString().trim();
        xl = tv_xl.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入姓名");
            return;

        }
        if (TextUtils.isEmpty(id_num)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入身份证号");
            return;

        }
        if (TextUtils.isEmpty(xxdz)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入详细地址");
            return;

        }
        if (TextUtils.isEmpty(bankNum)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入银行卡号");
            return;

        }
        if (TextUtils.isEmpty(bankName)) {
            AbToastUtil.showToast(getApplicationContext(), "请输入所属银行");
            return;

        }

        if ("学生".equals(flag)) {
            if (TextUtils.isEmpty(ykt)) {
                AbToastUtil.showToast(getApplicationContext(), "请输入一卡通或学号");
                return;

            }

            if (TextUtils.isEmpty(xxmc)) {
                AbToastUtil.showToast(getApplicationContext(), "请输入学校名称");
                return;
            }
            if (TextUtils.isEmpty(rxsj)) {
                AbToastUtil.showToast(getApplicationContext(), "请输入入学时间");
                return;
            }

            if ("请选择".equals(xl)) {
                AbToastUtil.showToast(getApplicationContext(), "请选择学历");
                return;

            }

        }


        doNetWork();
    }

    private void doNetWork() {

        String loginUid = MyApplication.getInstance().getLoginUid();

        if (loginUid != null) {
            AbDialogUtil.getWaitDialog(this);
            HaiHeApi.userIdentityAffirm(loginUid, id_num, userName, ykt, bankNum, bankName, xxmc, rxsj, xl, xxdz, new AbSoapListener() {

                @Override
                public void onSuccess(int statusCode, String content) {

                    UserIdentityAffirmInfo info = HaiheReturnApi.
                            userIdentityAffirm(content);
                    if (info != null) {
                        if ("000".equals(info.getRespCode())) {

                            AbToastUtil.showToastInThread(getApplicationContext(),"认证成功");
                             ActivityUtil.startActivity(ShenFenRZActivity.this,MainActivity.class);
                            EventBus.getDefault().post(AbConstant.MY_ACCOUNT_REFRESH);
                            CallBackManager.getInstance().sendSwitchRadio(2);
                        } else {

                            AbToastUtil.showToastInThread(
                                    ShenFenRZActivity.this,
                                    info.getRespCodeDesc());
                        }
                    }
                    AbDialogUtil.removeDialog(ShenFenRZActivity.this);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbDialogUtil.removeDialog(ShenFenRZActivity.this);
                    AbToastUtil.showToastInThread(ShenFenRZActivity.this,

                            error.getMessage());
                }
            });
        }
    }


    private void showDialog() {
        list.clear();

        ListView listView = new ListView(this);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListData()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                qx = list.get(position);

                AbDialogUtil.removeDialog(ShenFenRZActivity.this);
                tv_xl.setText(qx);
                AbDialogUtil.removeDialog(ShenFenRZActivity.this);

            }
        });
        AbDialogUtil.showAlertDialog(listView);
    }


    private ArrayList<String> list = new ArrayList<String>();

    private List<String> getListData() {

        list.add("博士后");
        list.add("博士");
        list.add("硕士");
        list.add("研究生");
        list.add("大学本科");
        list.add("大学专科");
        list.add("高中");
        list.add("中专");
        list.add("初中");
        list.add("小学");
        return list;
    }
}
