package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.model.domain.UserGetMyRedInfo;
import com.xunfenqi.model.domain.UserMyRedInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbDialogUtil;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewHolder;
import com.xunfenqi.view.dialog.SweetAlertDialog;

import java.util.List;

/**
 * 红包
 */
public class MyredAdapter extends BaseAdapter {

	private static String TAG = "MyredAdapter";

	private Activity mContext;
	// xml转View对象
	private LayoutInflater mInflater;
	// 单行的布局
	private int mResource;
	// 列表展现的数据
	private List<UserMyRedInfo.UserMyRed> mData;
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
	 * @param data
	 *            列表展现的数据
	 * @param resource
	 *            单行的布局
	 * @param from
	 *            Map中的key
	 * @param to
	 *            view的id
	 *
	 */

	public MyredAdapter(Activity context, List data, int resource,
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
		final UserMyRedInfo.UserMyRed userMyRed = (UserMyRedInfo.UserMyRed) mData.get(position);



		TextView tv_bt = AbViewHolder.get(convertView, mTo[0]);
		TextView tv_beizhu = AbViewHolder.get(convertView, mTo[1]);
		TextView tv_ffrq = AbViewHolder.get(convertView, mTo[2]);
		TextView tv_lqrq = AbViewHolder.get(convertView, mTo[3]);
		TextView tv_jine = AbViewHolder.get(convertView, mTo[4]);
		tv_caozuo = AbViewHolder.get(convertView, mTo[5]);
		iv_state = AbViewHolder.get(convertView, mTo[6]);
		ll_lqrq = AbViewHolder.get(convertView, mTo[7]);


		tv_beizhu.setText(userMyRed.getBeizhu());
		tv_bt.setText(userMyRed.getBt());
		tv_ffrq.setText("发放日期 "+userMyRed.getFfrq());
		tv_jine.setText("¥ "+userMyRed.getJine());
		tv_lqrq.setText("领取日期 "+userMyRed.getKlrq());

		String type = userMyRed.getLqzt();
		if ("待领取".equals(type)) {
			tv_caozuo.setClickable(true);
			tv_caozuo.setEnabled(true);
			ll_lqrq.setVisibility(View.GONE);
			tv_caozuo.setBackgroundResource(R.drawable.shape_bg_my_frag_recharge);
			tv_caozuo.setVisibility(View.VISIBLE);
			iv_state.setVisibility(View.GONE);
			tv_caozuo.setText("待领取");
		} else if ("领取".equals(type)) {
			tv_caozuo.setClickable(true);
			tv_caozuo.setVisibility(View.VISIBLE);

			ll_lqrq.setVisibility(View.GONE);
			iv_state.setVisibility(View.GONE);
			tv_caozuo.setBackgroundResource(R.drawable.shape_bg_my_frag_recharge);
			tv_caozuo.setText("领取");
			tv_caozuo.setEnabled(true);

		} else if ("已领取".equals(type) || "已到账".equals(type)) {
			tv_caozuo.setClickable(false);
			tv_caozuo.setBackgroundResource(R.drawable.shape_gray);
			tv_caozuo.setText("已领取");
			tv_caozuo.setVisibility(View.GONE);
			ll_lqrq.setVisibility(View.VISIBLE);
			iv_state.setVisibility(View.VISIBLE);
			tv_caozuo.setEnabled(false);
		} else if("失效".equals(type)){

			ll_lqrq.setVisibility(View.GONE);
			tv_caozuo.setClickable(false);
			tv_caozuo.setVisibility(View.VISIBLE);
			iv_state.setVisibility(View.GONE);
			tv_caozuo.setEnabled(false);
			tv_caozuo.setBackgroundResource(R.drawable.shape_gray);
			tv_caozuo.setText("已失效");
		}

		tv_caozuo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doNetworkToGetRedBag(position, userMyRed);
			}
		});



		return convertView;
	}

	private void doNetworkToGetRedBag(int position, final UserMyRedInfo.UserMyRed userRed) {

		String redId = userRed.getHbid();
		AbDialogUtil.getWaitDialog(mContext);
		String loginUid = MyApplication.getInstance().getLoginUid();
		if (loginUid != null && !"".equals(loginUid)) {
			HaiHeApi.userGetMyRed(loginUid, redId, new AbSoapListener() {
				@Override
				public void onSuccess(int statusCode, String content) {
					AbDialogUtil.removeDialog(mContext);
					final UserGetMyRedInfo getUserMyRed = HaiheReturnApi
							.userGetMyRed(content);
					MyApplication.getInstance().myRedRefreshFlag = 2;
					if (getUserMyRed != null) {
						if (getUserMyRed.getRespCode().equals("000")) {
							mContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									userRed.setLqzt("已领取");

									userRed.setKlrq(getUserMyRed.getLqsj());
									tv_caozuo.setText("已领取");
									tv_caozuo.setClickable(false);
									tv_caozuo.setBackgroundResource(R.drawable.shape_gray);
									tv_caozuo.setText("已领取");
									tv_caozuo.setVisibility(View.GONE);

									ll_lqrq.setVisibility(View.VISIBLE);
									iv_state.setVisibility(View.VISIBLE);
									tv_caozuo.setEnabled(false);
									MyredAdapter.this.notifyDataSetChanged();
									new SweetAlertDialog(mContext,
											SweetAlertDialog.CUSTOM_IMAGE_TYPE)
											.setTitleText("提示")
											.setContentText("红包领取成功")
											.setConfirmText("确认")
											.setConfirmClickListener(
													new SweetAlertDialog.OnSweetClickListener() {
														@Override
														public void onClick(
																SweetAlertDialog sDialog) {
															sDialog.dismiss();
														}
													}).show();
								}
							});

						} else {
							AbToastUtil.showToastInThread(mContext,
									getUserMyRed.getRespCodeDesc());
							return;
						}
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
	}
}
