package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunfenqi.model.domain.QueryNewsInfo;
import com.xunfenqi.utils.AbViewHolder;

import java.util.List;

/**
 * 红包
 */
public class XunTouTiaoAdapter extends BaseAdapter {

    private static String TAG = "MyJieKuanAdapter";

    private Activity mContext;
    // xml转View对象
    private LayoutInflater mInflater;
    // 单行的布局
    private int mResource;
    // 列表展现的数据
    private List<QueryNewsInfo.QueryNews> mData;
    // Map中的key
    private String[] mFrom;
    // view的id
    private int[] mTo;


    /**
     * 构造方法
     *
     * @param context
     * @param data     列表展现的数据
     * @param resource 单行的布局
     * @param from     Map中的key
     * @param to       view的id
     */

    public XunTouTiaoAdapter(Activity context, List data, int resource,
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
        final QueryNewsInfo.QueryNews queryNews = (QueryNewsInfo.QueryNews) mData.get(position);


//		"tv_xun_toutiao_item_title", "tv_xun_toutiao_item_new_flag",
//				"tv_xun_toutiao_item_time", "tv_xun_toutiao_item_content"

        TextView tv_title = AbViewHolder.get(convertView, mTo[0]);
        TextView tv_newsflag = AbViewHolder.get(convertView, mTo[1]);
        TextView tv_time = AbViewHolder.get(convertView, mTo[2]);
        TextView tv_content = AbViewHolder.get(convertView, mTo[3]);


        tv_title.setText(queryNews.getTitle());
        //tv_time.setText(queryNew);
        tv_content.setText(queryNews.getNr());

        if (position == 0) {
            tv_newsflag.setVisibility(View.VISIBLE);

        } else {
            tv_newsflag.setVisibility(View.GONE);
        }

        return convertView;
    }

}
