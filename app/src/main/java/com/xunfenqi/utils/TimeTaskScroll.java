package com.xunfenqi.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.xunfenqi.adapter.HomeFragPaoMaAdapter;
import com.xunfenqi.model.domain.UserIntoIndex;

import java.util.List;
import java.util.TimerTask;


public class TimeTaskScroll extends TimerTask {

    private ListView listView;
    Context context;
    public TimeTaskScroll(Context context, ListView listView, List<UserIntoIndex.Hhdt> list){
        this.listView = listView;
        this.context = context;
        listView.setAdapter(new HomeFragPaoMaAdapter(list,context));
    }

    int i = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            listView.smoothScrollToPositionFromTop(i++, 1);
        };
    };

    @Override
    public void run() {
//		Message msg = handler.obtainMessage();
        handler.sendEmptyMessage(200);//.sendMessage(msg);
    }

}