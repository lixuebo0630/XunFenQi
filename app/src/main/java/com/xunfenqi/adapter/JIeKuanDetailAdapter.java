package com.xunfenqi.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.activity.HuanKuanSuccessActivity;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.model.domain.UserLoansDetailInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.pay.PayResult;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbLogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewHolder;
import com.xunfenqi.utils.ActivityUtil;

import java.util.List;
import java.util.Map;

/**
 * 红包
 */
public class JIeKuanDetailAdapter extends BaseAdapter {


    private Activity mContext;
    // xml转View对象
    private LayoutInflater mInflater;
    // 单行的布局
    private int mResource;
    // 列表展现的数据
    private List<UserLoansDetailInfo.UserLoansDetail> mData;
    // Map中的key
    private String[] mFrom;
    // view的id
    private int[] mTo;

    private static final int SDK_PAY_FLAG = 1;

    private Button tv_caozuo;
    private ImageView iv_state;
    private LinearLayout ll_lqrq;

    /**
     * 构造方法
     *
     * @param context
     * @param data     列表展现的数据
     * @param resource 单行的布局
     * @param from     Map中的key
     * @param to       view的id
     */

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    //    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();

                        ActivityUtil.startActivity(mContext, HuanKuanSuccessActivity.class);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }


                default:
                    break;
            }
        }

        ;
    };


    public JIeKuanDetailAdapter(Activity context, List data, int resource,
                                String[] from, int[] to) {
        this.mContext = context;
        this.mData = data;
        this.mResource = resource;
        this.mFrom = from;
        this.mTo = to;
        // 用于将xml转为View
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // 使用自定义的list_items作为Layout
            convertView = mInflater.inflate(mResource, parent, false);
        }

        // 获取该行的数据
        final UserLoansDetailInfo.UserLoansDetail userLoansDetail = (UserLoansDetailInfo.UserLoansDetail) mData.get(position);


        TextView tv_qs = AbViewHolder.get(convertView, mTo[0]);
        TextView tv_sxf = AbViewHolder.get(convertView, mTo[1]);
        TextView tv_hkr = AbViewHolder.get(convertView, mTo[2]);
        RelativeLayout rl_sfyq = AbViewHolder.get(convertView, mTo[3]);
        TextView tv_sfyq = AbViewHolder.get(convertView, mTo[4]);
        RelativeLayout rl_yqts = AbViewHolder.get(convertView, mTo[5]);
        TextView tv_yqts = AbViewHolder.get(convertView, mTo[6]);
        Button bt_wyhk = AbViewHolder.get(convertView, mTo[7]);
        ImageView iv_yhflag = AbViewHolder.get(convertView, mTo[8]);


        bt_wyhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                doHuanKuan(userLoansDetail.getLsh());
            }
        });

        String zt = userLoansDetail.getZt();
        if ("已还款".equals(zt)) {

            rl_sfyq.setVisibility(View.GONE);
            rl_yqts.setVisibility(View.GONE);
            bt_wyhk.setVisibility(View.GONE);
            iv_yhflag.setVisibility(View.VISIBLE);
        } else {
            rl_sfyq.setVisibility(View.VISIBLE);
            rl_yqts.setVisibility(View.VISIBLE);
            bt_wyhk.setVisibility(View.VISIBLE);
            iv_yhflag.setVisibility(View.GONE);

        }

        String sfyq = userLoansDetail.getSfyq();

        if ("是".equals(sfyq)) {
            tv_sfyq.setBackgroundResource(R.drawable.shape_my_frag_date);
        } else {
            tv_sfyq.setBackgroundResource(R.drawable.shape_blue_corner);

        }

        tv_hkr.setText(userLoansDetail.getHkrq());
        tv_qs.setText(userLoansDetail.getQs());

        tv_sxf.setText(userLoansDetail.getZe() + "(包含分期费用和服务费" + userLoansDetail.getSxf() + "元)");

        tv_sfyq.setText(userLoansDetail.getSfyq());

        tv_yqts.setText(userLoansDetail.getYqts());


        userLoansDetail.getSfhk();
        return convertView;
    }

    private void doHuanKuan(final String lsh) {
       AbDialogUtil.getWaitDialog(mContext);


        String loginUid = MyApplication.getInstance().getLoginUid();
        if (loginUid != null) {
            HaiHeApi.userSendZfb(loginUid, lsh, "recharge",
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            AbDialogUtil.removeDialog(mContext);
                            final UserLoansDetailInfo userLoansDetailInfo = HaiheReturnApi
                                    .sendZfb(content);
                            if (userLoansDetailInfo != null) {
                                if ("000".equals(userLoansDetailInfo.getRespCode())) {
                                    EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

                                    Runnable payRunnable = new Runnable() {

                                        @Override
                                        public void run() {
                                            PayTask alipay = new PayTask(mContext);
                                            Map<String, String> result = alipay.payV2(userLoansDetailInfo.getOrderInfo(), true);
                                            AbLogUtil.d("ALIPay",result.toString());

                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    };

                                    Thread payThread = new Thread(payRunnable);
                                    payThread.start();
                                } else {
                                    AbToastUtil.showToastInThread(mContext,
                                            userLoansDetailInfo.getRespCodeDesc());
                                }
                            }


                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            error.printStackTrace();
                            AbToastUtil.showToastInThread(mContext,
                                    error.getMessage());
                            AbDialogUtil.removeDialog(mContext);
                        }
                    });
        }

    }


}
