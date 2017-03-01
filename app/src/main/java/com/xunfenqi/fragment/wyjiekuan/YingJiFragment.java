/**
 * Project Name:HaiHeFinance
 * File Name:HomeFragment.java
 * Package Name:com.haihefinance.fragment
 * Date:2015-8-20下午5:22:09
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */

package com.xunfenqi.fragment.wyjiekuan;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.activity.ApplyJIeKuanSuccessActivity;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.AbFragment;
import com.xunfenqi.model.domain.ApplyLoanInfo;
import com.xunfenqi.model.domain.QueryUserRateInfo;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.ActivityUtil;

import static java.lang.Double.parseDouble;

/**
 * @date: 2015-8-20 下午5:22:09
 * @author: XueBo Li
 * @version:
 * @description:主界面--首页碎片
 * @see
 */
public class YingJiFragment extends AbFragment implements OnClickListener {

    private Activity mActivity;
    private TextView tv_jkqx, tv_jkfy, tv_sjdz, tv_verify_num;

    private EditText et_jkje, et_xxsm, et_verfiy_num;
    private String qx = "";

    private TimeCount time;
    private boolean flag = false;
    private String sxfFv;
    private String yjFv;
    private String fqFv;
    private String fwfFv;
    private double dYjje = 0;

    private String verifyCode = "";
    private int sxf;
    private int yj;
    private int bj;

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateContentView(LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {
        mActivity = this.getActivity();
        View view = initView(inflater);

        return view;
    }

    private View initView(LayoutInflater inflater) {

        time = new TimeCount(120000, 1000);

        View view = inflater.inflate(R.layout.frag_fenqi_jiekuan, null);

        tv_jkqx = (TextView) view.findViewById(R.id.tv_fenqi_jiekuan_frag_jkqx);
        tv_jkfy = (TextView) view.findViewById(R.id.tv_fenqi_jiekuan_frag_jkfy);
        tv_sjdz = (TextView) view.findViewById(R.id.tv_fenqi_jiekuan_frag_sjdz);
        tv_verify_num = (TextView) view.findViewById(R.id.tv_fenqi_jiekuan_frag_verify_num);
        et_jkje = (EditText) view.findViewById(R.id.et_fenqi_jiekuan_frag_jkje);
        et_xxsm = (EditText) view.findViewById(R.id.et_fenqi_jiekuan_frag_sjsm);
        et_verfiy_num = (EditText) view.findViewById(R.id.et_fenqi_jiekuan_frag_verify_num);
        view.findViewById(R.id.bt_fenqi_jiekuan_frag_ljsq).setOnClickListener(this);

        tv_jkqx.setText("7天");
        tv_jkqx.setCompoundDrawables(null,null,null,null);
        //tv_jkqx.setOnClickListener(this);
        tv_verify_num.setOnClickListener(this);
        initData();
        doNetwork();

        return view;
    }

    private void initData() {


        et_jkje.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String jkje1 = et_jkje.getText().toString().trim();
                if (!hasFocus&&!TextUtils.isEmpty(jkje1)) {
                    double d = parseDouble(jkje1);
                    double ss = d / 100;
                    int a = (int) Math.floor(ss);
                    int money = a * 100;

                    et_jkje.setText("" + money);


                    if (d > dYjje) {
                        et_jkje.setText("" + (int) dYjje);
                    } else if (d < 1000) {
                        et_jkje.setText("" + 1000);

                    }
                }
            }
        });
        et_jkje.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (flag) {
                    return;
                }

                flag = true;
                if (!TextUtils.isEmpty(sxfFv) && !TextUtils.isEmpty(yjFv)) {


                    double sxffv = Double.parseDouble(sxfFv);

                    double yjfv = Double.parseDouble(yjFv);

                    if (!TextUtils.isEmpty(s.toString())) {

                        bj = Integer.parseInt(s.toString());
                        sxf = (int) (bj * sxffv / 100);


                        int jkfy = sxf;
                        int sjdz = bj - jkfy;
                        tv_jkfy.setText("" + jkfy+"元" );
                        tv_sjdz.setText(sjdz + "元");
                    } else {
                        tv_jkfy.setText("0元");
                        tv_sjdz.setText("0元");

                    }

                }
                flag = false;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void doNetwork() {
        HaiHeApi.queryUserRate(MyApplication.getInstance().getLoginUid(), new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                QueryUserRateInfo info = HaiheReturnApi
                        .queryUserRate(content);

                if (info != null) {

                    if ("000".equals(info.getRespCode())) {

                        String yjje = info.getYjje();

                        dYjje = parseDouble(yjje);

                        if (dYjje == 1000) {
                            et_jkje.setText("1000");
                            et_jkje.setHint("");
                        } else if (dYjje > 1000) {
                            et_jkje.setHint("1000-" + dYjje + "的整数,100递增");
                        }


                        sxfFv = info.getSxf();
                        yjFv = info.getYj();
                        fqFv = info.getFq();
                        fwfFv = info.getFwf();


                        showContentView();
                    } else {
                        AbToastUtil.showToastInThread(
                                mActivity,
                                info.getRespCodeDesc());
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, String content, Throwable error) {
                AbToastUtil.showToastInThread(mActivity,
                        error.getMessage());
                error.printStackTrace();
                AbDialogUtil.removeDialog(mActivity);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fenqi_jiekuan_frag_ljsq:

                doOpertation();

                break;
            case R.id.tv_fenqi_jiekuan_frag_verify_num:
// 开始计时
                time.start();
                // 获取验证码
                getVerifyNumber();
                break;
            default:
                break;

        }
    }


    private void getVerifyNumber() {

        HaiHeApi.getVerifyNumber(MyApplication.getInstance().getLoginUser().getTel(), "10", new AbSoapListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
                        .sendSmsModelReturn(content);
                if (sendSmsModelInfo != null) {
                    if (sendSmsModelInfo.getRespCode().equals("000")) {
                        AbToastUtil.showToastInThread(mActivity,
                                "验证码发送成功");
                        verifyCode = sendSmsModelInfo.getVerifyCode();
                    } else {
                        AbToastUtil.showToastInThread(mActivity,
                                sendSmsModelInfo.getRespCodeDesc());
                        return;
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbToastUtil.showToastInThread(
                        mActivity, error.getMessage());
                error.printStackTrace();
            }
        });
    }

    /**
     * @author Xuebo Li
     * @ClassName: TimeCount
     * @Description: 计时器
     * @date 2015-9-15 下午3:59:06
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_verify_num.setText("重新获取验证码");
            tv_verify_num.setClickable(true);
            tv_verify_num.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_verify_num.setClickable(false);
            tv_verify_num.setBackgroundColor(Color.WHITE);
            tv_verify_num.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
    }

    private void doOpertation() {


        String jkje = et_jkje.getText().toString().trim();

        if (TextUtils.isEmpty(jkje)) {
            AbToastUtil.showToast(mActivity, "请输入借款金额");

            return;

        }

        String verifyNum = et_verfiy_num.getText().toString().trim();

        if (TextUtils.isEmpty(verifyNum)) {
            AbToastUtil.showToast(mActivity, "请输入验证码");

            return;
        }


        if (!verifyCode.equals(verifyNum)) {
            AbToastUtil.showToast(mActivity, "验证码输入错误");
            return;
        }

        double d = parseDouble(et_jkje.getText().toString().trim());
        double ss = d / 100;
        int a = (int) Math.floor(ss);
        int money = a * 100;

        et_jkje.setText("" + money);


        if (d > dYjje) {
            et_jkje.setText("" + (int) dYjje);
        } else if (d < 1000) {
            et_jkje.setText("" + 1000);

        }


        submit();

    }

    private void submit() {

        String jkqx = tv_jkqx.getText().toString().substring(0, 1);
        String jkje = et_jkje.getText().toString().trim();
        String jksm = et_xxsm.getText().toString().trim();
        HaiHeApi.applyLoan("2", jkqx, "0", jkje, jksm, (bj - sxf ) + "", yj + "", sxf + "",
                MyApplication.getInstance().getLoginUid(), new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {

                        ApplyLoanInfo info = HaiheReturnApi.applyLoan(content);
                        if (info != null) {
                            if (info.getRespCode().equals("000")) {
                                ActivityUtil.startActivityAndFinish(mActivity, ApplyJIeKuanSuccessActivity.class);
                            } else {
                                AbToastUtil.showToastInThread(mActivity,
                                        info.getRespCodeDesc());
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String content, Throwable error) {
                        AbToastUtil.showToastInThread(mActivity,
                                error.getMessage());
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(mActivity);
                    }
                });


    }



}
