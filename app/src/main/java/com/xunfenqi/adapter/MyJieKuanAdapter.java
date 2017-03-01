package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.xunfenqi.model.domain.UserMyJiekuanInfo;
import com.xunfenqi.utils.AbViewHolder;

import java.util.List;

/**
 * 红包
 */
public class MyJieKuanAdapter extends BaseAdapter {

	private static String TAG = "MyJieKuanAdapter";

	private Activity mContext;
	// xml转View对象
	private LayoutInflater mInflater;
	// 单行的布局
	private int mResource;
	// 列表展现的数据
	private List<UserMyJiekuanInfo.UserMyJiekuan> mData;
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

	public MyJieKuanAdapter(Activity context, List data, int resource,
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
		final UserMyJiekuanInfo.UserMyJiekuan userMyJiekuan = (UserMyJiekuanInfo.UserMyJiekuan) mData.get(position);



		TextView tv_ddh = AbViewHolder.get(convertView, mTo[0]);
		TextView tv_fqze = AbViewHolder.get(convertView, mTo[1]);
		TextView tv_jklx = AbViewHolder.get(convertView, mTo[2]);
		TextView tv_jksj = AbViewHolder.get(convertView, mTo[3]);
		TextView tv_yhqs = AbViewHolder.get(convertView, mTo[4]);
		TextView tv_syyh = AbViewHolder.get(convertView, mTo[5]);
		Button bt_opertation = AbViewHolder.get(convertView,mTo[6]);




		tv_ddh.setText(userMyJiekuan.getLs());
		tv_fqze.setText(userMyJiekuan.getZe()+"  (包含手续费"+userMyJiekuan.getSxf()+")");
		tv_jklx.setText(userMyJiekuan.getLx());
		tv_jksj.setText(userMyJiekuan.getSj());
		tv_syyh.setText(userMyJiekuan.getYh());
		tv_yhqs.setText(userMyJiekuan.getQs());
		bt_opertation.setText(userMyJiekuan.getZt());



		return convertView;
	}

}
