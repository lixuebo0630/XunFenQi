package com.xunfenqi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunfenqi.model.domain.UserMonthLoansDetailInfo;
import com.xunfenqi.utils.AbViewHolder;

import java.util.List;
import java.util.Map;

/**
 * 红包
 */
public class MyZhangDanAdapter extends BaseAdapter {

    private static String TAG = "MyInviteAdapter";

    private Activity mContext;
    // xml转View对象
    private LayoutInflater mInflater;
    // 单行的布局
    private int mResource;
    // 列表展现的数据
    private List<UserMonthLoansDetailInfo.UserMonthLoansDetail> mData;
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

    public MyZhangDanAdapter(Activity context, List data, int resource,
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


        TextView tv_je = AbViewHolder.get(convertView, mTo[0]);
        TextView tv_ddh = AbViewHolder.get(convertView, mTo[1]);
        TextView tv_qs = AbViewHolder.get(convertView, mTo[2]);


        tv_je.setText(mData.get(position).getJe());
        tv_ddh.setText("订单号  " + mData.get(position).getLsh());
        tv_qs.setText(mData.get(position).getQs());

        return convertView;
    }

}
