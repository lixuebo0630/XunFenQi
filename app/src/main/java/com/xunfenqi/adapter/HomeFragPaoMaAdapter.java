package com.xunfenqi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.model.domain.UserIntoIndex;
import com.xunfenqi.utils.UIUtils;

import java.util.List;


public class HomeFragPaoMaAdapter extends BaseAdapter {

	public HomeFragPaoMaAdapter(List<UserIntoIndex.Hhdt> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	private List<UserIntoIndex.Hhdt> list;
	private Context context;
	private LayoutInflater inflater;

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getItem(int arg0) {
		return list.get(arg0 % list.size()).getTitle();
	}

	@Override
	public long getItemId(int arg0) {
		return arg0 % list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.layout_home_frag_paoma_item, null);
		}
		TextView content = (TextView) convertView.findViewById(R.id.content);
		content.setText(list.get(position % list.size()).getTitle());

		int height = UIUtils.dip2px(28);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, height);
		lp.leftMargin = UIUtils.dip2px(10);
		lp.rightMargin = UIUtils.dip2px(10);
		// lp.topMargin = SystemUtils.dip2px(context, 3);
		// lp.bottomMargin = SystemUtils.dip2px(context, 3);
		content.setLayoutParams(lp);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, height);
		convertView.setLayoutParams(params);
		return convertView;
	}

}
