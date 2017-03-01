package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserLoansDetailInfo;
import com.xunfenqi.model.domain.UserLoansPayBackDaoInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewHolder;
import com.xunfenqi.utils.ButtonUtils;
import com.xunfenqi.utils.Md5Util;

import java.util.List;

import de.greenrobot.event.EventBus;

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


                doHuanKuan(userLoansDetail.getMxid(), userLoansDetail.getZe());
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

        tv_sxf.setText(userLoansDetail.getZe() + "(包含手续费" + userLoansDetail.getSxf() + "元)");

        tv_sfyq.setText(userLoansDetail.getSfyq());

        tv_yqts.setText(userLoansDetail.getYqts());


        userLoansDetail.getSfhk();
        return convertView;
    }

    private void doHuanKuan(final String mxid, final String ze) {

        final View view = View.inflate(mContext, R.layout.layout_dialog_with_edittext, null);

        final EditText editText = (EditText) view
                .findViewById(R.id.et_dialog_with_edit_deal_pwd);
        TextView tv_money = (TextView) view.findViewById(R.id.tv_money);

        tv_money.setText("支付 " + ze + " 元");
        Button bt_cancel = (Button) view
                .findViewById(R.id.bt_dialog_with_edit_cancel);
        Button bt_confirm = (Button) view
                .findViewById(R.id.bt_dialog_with_edit_confirm);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbDialogUtil.removeDialog(view);
            }
        });
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ButtonUtils.isFastDoubleClick()) {
                    return;

                } else {

                    String payPws = editText.getText().toString().trim();

                    if (!TextUtils.isEmpty(payPws)) {
                        String loginUid = MyApplication.getInstance().getLoginUid();

                        if (loginUid != null && !"".equals(loginUid)) {
                            HaiHeApi.userLoansPayBackDao(loginUid, mxid, Md5Util.md5Diagest(payPws), ze, new AbSoapListener() {
                                @Override
                                public void onSuccess(int statusCode, String content) {
                                    AbDialogUtil.removeDialog(mContext);
                                    final UserLoansPayBackDaoInfo userLoansPayBackDaoInfo = HaiheReturnApi
                                            .userLoansPayBackDao(content);
                                    if (userLoansPayBackDaoInfo != null) {
                                        if (userLoansPayBackDaoInfo.getRespCode().equals("000")) {
                                            EventBus.getDefault().post(AbConstant.MY_ACCOUNT_REFRESH);
                                            EventBus.getDefault().post(AbConstant.JIEKUAN_DETAIL_REFRESH);
                                            AbToastUtil.showToast(mContext,"还款成功");
                                            AbDialogUtil.removeDialog(view);

                                        } else {
                                            AbToastUtil.showToastInThread(mContext,
                                                    userLoansPayBackDaoInfo.getRespCodeDesc());
                                            return;
                                        }
                                    } else {
                                        AbToastUtil.showToast(mContext, "----------");
                                        return;
                                    }

                                }

                                @Override
                                public void onFailure(int statusCode, String content,
                                                      Throwable error) {
                                    error.printStackTrace();
                                    AbDialogUtil.removeDialog(mContext);
                                    AbToastUtil.showToastInThread(mContext, error.getMessage());
                                }

                            });
                        }


                    } else {
                        AbToastUtil.showToast(mContext, "请输入交易密码");
                        return;
                    }


                }
            }
        });


        AbDialogUtil.showAlertDialog(view);

    }


}
