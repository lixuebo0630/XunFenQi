package com.xunfenqi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.utils.AbViewHolder;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: RechargeRecordItemAdapter
 * @Description: 提现记录List适配器
 * @author Xuebo Li
 * @date 2015-10-13 下午5:30:08
 * 
 */
public class EnchashmentRecordItemAdapter extends BaseAdapter {

	private static String TAG = "EnchashmentRecordItemAdapter";

	private Context mContext;
	// xml转View对象
	private LayoutInflater mInflater;
	// 单行的布局
	private int mResource;
	// 列表展现的数据
	private List mData;
	// Map中的key
	private String[] mFrom;
	// view的id
	private int[] mTo;

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

	public EnchashmentRecordItemAdapter(Context context, List data,
			int resource, String[] from, int[] to) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// 使用自定义的list_items作为Layout
			convertView = mInflater.inflate(mResource, parent, false);
		}

		// 获取该行的数据
		final Map<String, Object> obj = (Map<String, Object>) mData
				.get(position);

		TextView tv_date = AbViewHolder.get(convertView, mTo[0]);
		TextView tv_time = AbViewHolder.get(convertView, mTo[1]);
		TextView tv_enchashment_money = AbViewHolder.get(convertView, mTo[2]);
		TextView tv_state = AbViewHolder.get(convertView, mTo[3]);
		TextView tv_bank_up = AbViewHolder.get(convertView, mTo[4]);
		TextView tv_bank_down = AbViewHolder.get(convertView, mTo[5]);
		ImageView iv_fail_state = AbViewHolder.get(convertView, mTo[6]);
		LinearLayout ll_state = AbViewHolder.get(convertView, mTo[7]);

		tv_date.setText((String) obj
				.get("tv_enchashment_record_frag_item_date"));

		tv_time.setText((String) obj
				.get("tv_enchashment_record_frag_item_time"));
		tv_enchashment_money.setText((String) obj
				.get("tv_enchashment_record__frag_enchashment_money"));
		String state = (String) obj
				.get("tv_enchashment_record_frag_item_state");

		tv_bank_up.setText((String) obj
				.get("tv_enchashment_record_frag_item_bank_up"));
		tv_bank_down.setText((String) obj
				.get("tv_enchashment_record_frag_item_bank_down"));

		if ("处理中".equals(state)) {
			tv_state.setTextColor(Color.parseColor("#FF9700"));
		} else {
			tv_state.setTextColor(Color.parseColor("#666666"));
		}
		if ("失败".equals(state)) {
			iv_fail_state.setVisibility(View.VISIBLE);
			ll_state.setClickable(true);
			ll_state.setEnabled(true);
		} else {
			ll_state.setEnabled(false);
			ll_state.setClickable(false);
			iv_fail_state.setVisibility(View.INVISIBLE);
		}

		ll_state.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AbToastUtil.showToast(mContext,
						"失败原因:" + (String) obj.get("beizhu"));
			}
		});
		tv_state.setText(state);
		return convertView;
	}
}
