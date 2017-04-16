package com.xunfenqi.activity; /**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.global.AppManager;
import com.xunfenqi.model.CityModel;
import com.xunfenqi.model.CountyModel;
import com.xunfenqi.model.ProvinceModel;
import com.xunfenqi.model.domain.SendSmsModelInfo;
import com.xunfenqi.model.domain.UserIntoEnchashment;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.ButtonUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.ParamButton;
import com.xunfenqi.view.titlebar.AbTitleBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: EnchashmentActivity
 * @Description: 提现界面
 * @author Xuebo Li
 * @date 2015-9-29 下午12:38:40
 *
 */
public class EnchashmentActivity extends BaseActivity implements
        OnClickListener {

    protected static final String TAG = "EnchashmentActivity";

    private String AddressXML; // xml格式的中国省市区信息
    private ParamButton btn_province;
    private ParamButton btn_city;
    private List<ProvinceModel> provinceList; // 地址列表
    private int pPosition;
    private int cPosition;
    private boolean isCity = true;
    private boolean isCounty = true;

    private TextView tv_enchashment_money;
    private LinearLayout ll_enchashment_act_bank;
    private EditText et_enchashment_act_open_bank_name;
    private TextView tv_enchashment_act_card;
    private EditText et_enchashment_act_tixian_money;
    private Button btn_enchashment_act_confirm_enchash;
    private ImageView iv_enchashment_act_bank;

    private EditText et_enchashment_input_verify_number;// 输入验证码
    private TextView tv_btn_enchashment_act_getVerifyNumber;// 获取验证码


    private TimeCount time;

    private String kyye;
    private String txje;
    private String payPws;
    private String yhmc;
    private String tel;
    private String branchBankName;
    private String kaihuCity;
    private String loginUid;


    private String verifyCode = "";
    private String verifyNumber = "";

    private ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            // .showImageOnLoading(R.drawable.ic_error_page)
            // .showImageOnFail(R.drawable.ic_error_page)
            .cacheInMemory(true).cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    private String sheng = "";

    private String shi = "";

    private String flag = ""; // 标识
    private String f = "1";

    private String index;

    private TextView tv_text;

    @Override
    public void initView() {
        AppManager.getAppManager().addActivity(EnchashmentActivity.this);
        setAbContentView(R.layout.activity_enchashment);
        initImageLoader(EnchashmentActivity.this);


        time = new TimeCount(120000, 1000);

        btn_province = (ParamButton) findViewById(R.id.btn_province);
        btn_city = (ParamButton) findViewById(R.id.btn_city);
        tv_text = (TextView) findViewById(R.id.tv_ench_act_text);

        btn_province.setOnClickListener(this);
        btn_city.setOnClickListener(this);


        tv_btn_enchashment_act_getVerifyNumber = (TextView) findViewById(R.id.tv_btn_enchashment_act_getVerifyNumber);
        et_enchashment_input_verify_number = (EditText) findViewById(R.id.et_enchashment_input_verify_number);

        tv_enchashment_money = (TextView) findViewById(R.id.tv_enchashment_act_can_enchashment_money);
        ll_enchashment_act_bank = (LinearLayout) findViewById(R.id.ll_enchashment_act_bank);
        et_enchashment_act_open_bank_name = (EditText) findViewById(R.id.et_enchashment_act_open_bank_name);
        tv_enchashment_act_card = (TextView) findViewById(R.id.tv_enchashment_act_card);
        et_enchashment_act_tixian_money = (EditText) findViewById(R.id.et_enchashment_act_tixian_money);
        btn_enchashment_act_confirm_enchash = (Button) findViewById(R.id.btn_enchashment_act_confirm_enchash);
        iv_enchashment_act_bank = (ImageView) findViewById(R.id.iv_enchashment_act_bank);
        tv_btn_enchashment_act_getVerifyNumber.setOnClickListener(this);

     //   tv_btn_enchashment_act_forget_deal_pwd.setOnClickListener(this);
        btn_enchashment_act_confirm_enchash
                .setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (ButtonUtils.isFastDoubleClickFor1s()) {
                            return;
                        } else {
                            doSubmit();
                        }
                    }
                });

    }

    public static void obtainFocus(View v) {
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setFocusableInTouchMode(false);
    }

    @Override
    public void initData() {
        doNetwork();
        AddressXML = getRawAddress().toString();// 获取中国省市区信息
        try {
            analysisXML(AddressXML);
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化button数据
        btn_province.setText("点击选择省");
        btn_city.setText("点击选择市");
        // 初始化列表下标
        pPosition = 0;
        cPosition = 0;

    }

    private void doNetwork() {
        loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            AbDialogUtil.getWaitDialog(this);
            HaiHeApi.userIntoEnchashment(loginUid, new AbSoapListener() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    final UserIntoEnchashment info = HaiheReturnApi
                            .userIntoEnchashment(content);
                    if (info != null) {
                        EnchashmentActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if ("000".equals(info.getRespCode())) {
                                    kyye = info.getKyye();
                                    if (kyye != null && !"".equals(kyye)) {
                                        double k = Double.parseDouble(kyye);
                                        if (k <= 50) {
                                            et_enchashment_act_tixian_money
                                                    .setText(k + "");
                                            et_enchashment_act_tixian_money
                                                    .setEnabled(false);
                                        }
                                    }

                                    if (tv_text != null) {
                                        tv_text.setText(Html.fromHtml(info
                                                .getContent()));
                                    }

//                                    if (tv_hqdj != null) {
//                                        if (TextUtils.isEmpty(info.getHqdj())) {
//                                            tv_hqdj.setVisibility(View.GONE);
//                                        } else {
//                                            tv_hqdj.setVisibility(View.VISIBLE);
//                                            tv_hqdj.setText(info.getHqdj());
//                                        }
//                                    }

                                    branchBankName = info.getBranchBankName();
                                    if (branchBankName != null
                                            && !"".equals(branchBankName)
                                            && !branchBankName.equals("null")) {
                                        ll_enchashment_act_bank
                                                .setVisibility(View.GONE);
                                        et_enchashment_act_open_bank_name
                                                .setVisibility(View.GONE);
                                        flag = "0";
                                    } else {
                                        ll_enchashment_act_bank
                                                .setVisibility(View.VISIBLE);
                                        et_enchashment_act_open_bank_name
                                                .setVisibility(View.VISIBLE);
                                        flag = "1";
                                    }
                                    tel = info.getUserTel();

                                    if (iv_enchashment_act_bank != null) {
                                        imageLoader.displayImage(
                                                info.getImgPath(),
                                                iv_enchashment_act_bank,
                                                options);
                                    }

                                    tv_enchashment_money.setText(kyye);
                                    tv_enchashment_act_card.setText(info
                                            .getCardlast());
                                } else {
                                    AbToastUtil.showToastInThread(
                                            EnchashmentActivity.this,
                                            info.getRespCodeDesc());
                                }
                            }
                        });
                    }
                    AbDialogUtil.removeDialog(EnchashmentActivity.this);
                }

                @Override
                public void onFailure(int statusCode, final String content,
                                      Throwable error) {
                    error.printStackTrace();
                    AbToastUtil.showToastInThread(EnchashmentActivity.this,
                            error.getMessage());
                    AbDialogUtil.removeDialog(EnchashmentActivity.this);
                }
            });
        }
    }

    @Override
    public void initActionBar() {
        // 初始化ActionBar
        AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "提现");
        tTitleBar.setLogo(R.drawable.titlebar_back);
        tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), 0,
                UIUtils.dip2px(14));
        TextView tv = new TextView(this);
        tv.setText("提现记录");
        tv.setPadding(10, 10, 10, 10);
        tv.setTextSize(14);
        tv.setTextColor(Color.WHITE);
        tTitleBar.getRightLayout().setPadding(0, 0, UIUtils.px2dip(60), 0);
        tTitleBar.addRightView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(EnchashmentActivity.this,
                        EnchashmentRecordActivity.class);
            }
        });
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_province:
                createDialog(1);
                break;
            case R.id.btn_city:
                if (isCity == true) {
                    createDialog(2);
                }
                break;
//            case R.id.tv_btn_enchashment_act_forget_deal_pwd:// 忘记交易密码
//                ActivityUtil.startActivity(EnchashmentActivity.this,
//                        SetDealPwdActivity.class);
//                break;

            case R.id.tv_btn_enchashment_act_getVerifyNumber:// 获取验证码
                // // 开始计时
                time.start();
                // // 获取验证码
                getVerifyNumber();
                break;
            default:
                break;
        }
    }

    private void doSubmit() {
        txje = et_enchashment_act_tixian_money.getText().toString().trim();

        verifyNumber = et_enchashment_input_verify_number.getText().toString()
                .trim();
        if (branchBankName != null && !"".equals(branchBankName)
                && !branchBankName.equals("null")) {
            branchBankName = "";
            kaihuCity = "";
            sheng = "";
            shi = "";
        } else {
            if ("1".equals(flag)) {
                sheng = btn_province.getText().toString().trim();
                shi = btn_city.getText().toString().trim();
                branchBankName = et_enchashment_act_open_bank_name.getText()
                        .toString().trim();
                String city = btn_province.getText().toString().trim();
                if ("点击选择省".equals(city)) {
                    AbToastUtil.showToast(getApplicationContext(), "请选择选择省");
                    return;
                }
                if (branchBankName == null || "".equals(branchBankName)) {
                    AbToastUtil.showToast(getApplicationContext(), "开户行名称不能为空");
                    return;
                }
            }

        }
        Double txje1 = 0d;
        Double kyye1 = Double.parseDouble(kyye);
        if (txje != null && !"".equals(txje)) {
            txje1 = Double.parseDouble(txje);
        } else {
            AbToastUtil.showToast(getApplicationContext(), "提现金额不能为空");
            return;
        }
        if (txje1 == 0.00) {
            AbToastUtil.showToast(getApplicationContext(), "提现金额不能为0");
            return;
        }
        if (txje1 > kyye1) {
            AbToastUtil.showToast(getApplicationContext(), "提现金额不能大于可用余额");
            return;
        }
        if (txje1 > 0 && txje1 < 50) {
            if (kyye1 >= 50) {
                AbToastUtil.showToast(getApplicationContext(), "单笔最小提现金额为50元");
                return;
            }
        }

//        if (payPws == null || "".equals(payPws)) {
//            AbToastUtil.showToast(getApplicationContext(), "交易密码不能为空");
//            return;
//        }
         if (verifyNumber == null || TextUtils.isEmpty(verifyNumber)) {
         AbToastUtil.showToast(getApplicationContext(), "验证码不能为空");
         return;
         }
         if (!verifyCode.equals(verifyNumber)) {
         AbToastUtil.showToast(getApplicationContext(), "验证码输入错误");
         return;
         }

        AbDialogUtil.getWaitDialog(EnchashmentActivity.this);
        HaiHeApi.userEnchashmentSumTwo(loginUid, txje1 + "", payPws,
                branchBankName, sheng, btn_province.getStringParam() + "",
                btn_city.getStringParam() + "", shi, new AbSoapListener() {
                    @Override
                    public void onSuccess(int statusCode, String content) {
                        AbDialogUtil.removeDialog(EnchashmentActivity.this);
                        UserIntoEnchashment info = HaiheReturnApi
                                .userEnchashmentSum(content);
                        if (info != null) {
                            if (info.getRespCode().equals("000")) {
                                ActivityUtil.startActivityAndFinish(
                                        EnchashmentActivity.this,
                                        EnchashmentSuccessActivity.class);

                            } else {
                                AbToastUtil.showToastInThread(
                                        getApplicationContext(),
                                        info.getRespCodeDesc());
                                AbDialogUtil
                                        .removeDialog(EnchashmentActivity.this);
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, final String content,
                                          Throwable error) {
                        error.printStackTrace();
                        AbDialogUtil.removeDialog(EnchashmentActivity.this);
                        AbToastUtil.showToastInThread(EnchashmentActivity.this,
                                error.getMessage());
                    }
                });
    }

    /**
     * 根据调用类型显示相应的数据列表
     *
     * @param type
     *            显示类型 1.省；2.市；3.县、区
     */
    public void createDialog(final int type) {
        View view = View.inflate(EnchashmentActivity.this,
                R.layout.layout_city, null);
        ListView lv = (ListView) view.findViewById(R.id.lv_city);
        if (type == 1) {
            ProvinceAdapter pAdapter = new ProvinceAdapter(provinceList);
            lv.setAdapter(pAdapter);

        } else if (type == 2) {
            CityAdapter cAdapter = new CityAdapter(provinceList.get(pPosition)
                    .getCity_list());
            lv.setAdapter(cAdapter);
        }
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                if (type == 1) {
                    pPosition = position;
                    btn_province.setText(provinceList.get(position)
                            .getProvince());
                    btn_province.setStringParam(provinceList.get(position)
                            .getCode());
                    // 判断该省下是否有市级
                    if (provinceList.get(position).getCity_list().size() < 1) {
                        btn_city.setText("");
                        isCity = false;
                        isCounty = false;
                    } else {
                        isCity = true;
                        btn_city.setText(provinceList.get(position)
                                .getCity_list().get(0).getCity());
                        btn_city.setStringParam(provinceList.get(position)
                                .getCity_list().get(0).getCode());
                        cPosition = 0;
                        // 判断该市下是否有区级或县级
                        if (provinceList.get(position).getCity_list().get(0)
                                .getCounty_list().size() < 1) {
                            isCounty = false;

                        } else {
                            isCounty = true;
                        }

                    }

                } else if (type == 2) {
                    cPosition = position;
                    btn_city.setText(provinceList.get(pPosition).getCity_list()
                            .get(position).getCity());
                    btn_city.setStringParam(provinceList.get(pPosition)
                            .getCity_list().get(position).getCode());

                    if (provinceList.get(pPosition).getCity_list()
                            .get(position).getCounty_list().size() < 1) {
                        isCounty = false;
                    } else {
                        isCounty = true;
                    }

                }
                AbDialogUtil.removeDialog(EnchashmentActivity.this);
            }
        });

        AbDialogUtil.showAlertDialog(view);
    }

    class ProvinceAdapter extends BaseAdapter {
        public List<ProvinceModel> adapter_list;

        public ProvinceAdapter(List<ProvinceModel> list) {
            adapter_list = list;
        }

        @Override
        public int getCount() {
            return adapter_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            TextView tv = new TextView(EnchashmentActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(adapter_list.get(position).getProvince());
            return tv;
        }

    }

    class CityAdapter extends BaseAdapter {
        public List<CityModel> adapter_list;

        public CityAdapter(List<CityModel> list) {
            adapter_list = list;
        }

        @Override
        public int getCount() {
            return adapter_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            TextView tv = new TextView(EnchashmentActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(adapter_list.get(position).getCity());
            return tv;
        }

    }

    /**
     * 获取地区raw里的地址xml内容
     * */
    public StringBuffer getRawAddress() {
        InputStream in = getResources().openRawResource(R.raw.address);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            br.close();
            isr.close();
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return sb;
    }

    /**
     * 解析省市区xml， 采用的是pull解析， 为什么选择pull解析：因为pull解析简单浅显易懂！
     * */
    public void analysisXML(String data) throws XmlPullParserException {
        try {
            ProvinceModel provinceModel = null;
            CityModel cityModel = null;
            CountyModel countyModel = null;
            List<CityModel> cityList = null;
            List<CountyModel> countyList = null;

            InputStream xmlData = new ByteArrayInputStream(
                    data.getBytes("UTF-8"));
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser;
            parser = factory.newPullParser();
            parser.setInput(xmlData, "utf-8");
            String currentTag = null;

            String province;
            String city;
            String county;

            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                String typeName = parser.getName();

                if (type == XmlPullParser.START_TAG) {
                    if ("root".equals(typeName)) {
                        provinceList = new ArrayList<ProvinceModel>();

                    } else if ("province".equals(typeName)) {
                        province = parser.getAttributeValue(0);// 获取标签里第一个属性,例如<city
                        // name="北京市"
                        // index="1">中的name属性
                        provinceModel = new ProvinceModel();
                        provinceModel.setProvince(province);
                        provinceModel.setCode(parser.getAttributeValue(1));
                        cityList = new ArrayList<CityModel>();

                    } else if ("city".equals(typeName)) {
                        city = parser.getAttributeValue(0);
                        cityModel = new CityModel();
                        cityModel.setCity(city);
                        cityModel.setCode(parser.getAttributeValue(1));
                        countyList = new ArrayList<CountyModel>();

                    } else if ("area".equals(typeName)) {
                        county = parser.getAttributeValue(0);
                        countyModel = new CountyModel();
                        countyModel.setCounty(county);

                    }

                    currentTag = typeName;

                } else if (type == XmlPullParser.END_TAG) {
                    if ("root".equals(typeName)) {

                    } else if ("province".equals(typeName)) {
                        provinceModel.setCity_list(cityList);
                        provinceList.add(provinceModel);

                    } else if ("city".equals(typeName)) {
                        cityModel.setCounty_list(countyList);
                        cityList.add(cityModel);

                    } else if ("area".equals(typeName)) {
                        countyList.add(countyModel);
                    }

                } else if (type == XmlPullParser.TEXT) {

                    currentTag = null;
                }

                type = parser.next();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_btn_enchashment_act_getVerifyNumber.setText("重新获取验证码");
            tv_btn_enchashment_act_getVerifyNumber.setClickable(true);
            tv_btn_enchashment_act_getVerifyNumber.setBackgroundColor(Color
                    .parseColor("#ffffff"));
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_btn_enchashment_act_getVerifyNumber.setClickable(false);
            tv_btn_enchashment_act_getVerifyNumber
                    .setBackgroundColor(Color.WHITE);
            tv_btn_enchashment_act_getVerifyNumber.setText(millisUntilFinished
                    / 1000 + "秒后重新获取");
        }
    }


    private void getVerifyNumber() {

        HaiHeApi.getVerifyNumber(tel, "6", new AbSoapListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                AbLogUtil.d(TAG, content);

                SendSmsModelInfo sendSmsModelInfo = HaiheReturnApi
                        .sendSmsModelReturn(content);

                if (sendSmsModelInfo != null) {
                    if (sendSmsModelInfo.getRespCode().equals("000")) {
                        AbToastUtil.showToast(getApplicationContext(),
                                "验证码发送成功");
                        verifyCode = sendSmsModelInfo.getVerifyCode();

                    } else {
                        AbToastUtil.showToastInThread(getApplicationContext(),
                                sendSmsModelInfo.getRespCodeDesc());
                        return;
                    }
                    // TODO 这里判断其他情况
                }
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbToastUtil.showToast(EnchashmentActivity.this,
                        error.getMessage());
                error.printStackTrace();
            }
        });
    }


}
