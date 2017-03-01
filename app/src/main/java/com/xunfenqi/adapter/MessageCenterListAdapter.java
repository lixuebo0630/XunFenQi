package com.xunfenqi.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunfenqi.HaiHeApi;
import com.xunfenqi.HaiheReturnApi;
import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.model.domain.UserMessageDetailInfo;
import com.xunfenqi.model.domain.UserMessageInfo;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.utils.AbToastUtil;
import com.xunfenqi.view.widget.ExpandableTextView;

import java.util.List;


public class MessageCenterListAdapter extends BaseAdapter {

    private final Context mContext;
    private final SparseBooleanArray mCollapsedStatus;

    private ViewHolder viewHolder;
    // 列表展现的数据
    private List<UserMessageInfo.UserMessage> mData;

    private MessageCenterCallback messageCenterCallback;

    public MessageCenterListAdapter(Context context, List data,
                                    MessageCenterCallback messageCenterCallback2) {

        mContext = context;
        mCollapsedStatus = new SparseBooleanArray();
        this.mData = data;
        this.messageCenterCallback = messageCenterCallback2;

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

        // 获取该行的数据
        final UserMessageInfo.UserMessage userMessage = mData.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_message_expandable, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_msg_content = (ExpandableTextView) convertView
                    .findViewById(R.id.tv_message_act_msg_content);
            viewHolder.tv_msg_type = (TextView) convertView
                    .findViewById(R.id.tv_message_act_msg_type);
            viewHolder.tv_msg_time = (TextView) convertView
                    .findViewById(R.id.tv_message_act_msg_time);
            viewHolder.iv_red = (ImageView) convertView
                    .findViewById(R.id.iv_message_act_msg_hongdian);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_msg_content.setText(userMessage.getXxnr(),
                mCollapsedStatus, position);
        viewHolder.tv_msg_type.setText(userMessage.getXxlx());
        viewHolder.tv_msg_time.setText(userMessage.getFssj());

        final String msg_id = userMessage.getXxid();
        final String state = userMessage.getCkzt();
        if ("已读取".equals(state)) {
            viewHolder.iv_red.setVisibility(View.GONE);
        } else {
            viewHolder.iv_red.setVisibility(View.VISIBLE);
        }

        viewHolder.tv_msg_content
                .setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
                    @Override
                    public void onExpandStateChanged(TextView textView,
                                                     boolean isExpanded) {

                        if (!("已读取".equals(state))) {
                            // 请求网络读取
                            doNetWorkM(userMessage.getXxid());
                            MessageCenterListAdapter.this
                                    .notifyDataSetChanged();
                            userMessage.setCkzt("已读取");
                        }

                    }

                });

        return convertView;
    }

    public static class ViewHolder {
        ExpandableTextView tv_msg_content;
        TextView tv_msg_time;
        TextView tv_msg_type;
        ImageView iv_red;

    }

    // 通知服务器消息已读 网络请求
    protected void doNetWorkM(String msg_id) {
        String loginUid = MyApplication.getInstance().getLoginUid();

        if (loginUid != null) {
            HaiHeApi.userMessageDetail(loginUid, msg_id, "2",
                    new AbSoapListener() {
                        @Override
                        public void onSuccess(int statusCode, String content) {

                            UserMessageDetailInfo userMessageInfo = HaiheReturnApi
                                    .userMessageDetail(content);

                            if (userMessageInfo != null) {
                                if (userMessageInfo.getRespCode().equals("000")) {

                                } else {
                                    AbToastUtil.showToastInThread(mContext,
                                            userMessageInfo.getRespCodeDesc());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode,
                                              final String content, Throwable error) {
                            error.printStackTrace();
                            AbToastUtil.showToastInThread(mContext,
                                    error.getMessage());
                        }
                    });
        }
    }

    // 按position读取消息回调
    public interface MessageCenterCallback {
        abstract void readMessage(int position, boolean isExpanded,
                                  String state, String msg_id);
    }

}