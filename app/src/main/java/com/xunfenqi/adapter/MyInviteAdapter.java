package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunfenqi.model.domain.QueryUserInviteInfo;
import com.xunfenqi.utils.AbViewHolder;

import java.util.List;
import java.util.Map;

/**
 * 红包
 */
public class MyInviteAdapter extends BaseAdapter {

	private static String TAG = "MyInviteAdapter";

	private Activity mContext;
	// xml转View对象
	private LayoutInflater mInflater;
	// 单行的布局
	private int mResource;
	// 列表展现的数据
	private List<QueryUserInviteInfo.UserInvite> mData;
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

	public MyInviteAdapter(Activity context, List data, int resource,
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
		final Map<String, Object> obj = (Map<String, Object>) mData
				.get(position);


		TextView tv_wdhy = AbViewHolder.get(convertView, mTo[0]);
		TextView tv_zcqd = AbViewHolder.get(convertView, mTo[1]);
		TextView tv_zcrq = AbViewHolder.get(convertView, mTo[2]);



		String zcqd = (java.lang.String) obj.get("item_zcqd");
		tv_wdhy.setText((String)obj.get("item_yhm"));
		if("-1".equals(zcqd)){
		tv_zcqd.setText("");
		}else {
			tv_zcqd.setText(zcqd);
		}
		tv_zcrq.setText((String)obj.get("item_zcrq"));



		return convertView;
	}

}
