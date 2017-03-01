package com.xunfenqi.base;

import com.xunfenqi.application.MyApplication;

/**
 * Created by lixuebo on 16/12/6.
 */

public class MyBaseFragment extends AbFragment {
    @Override
    protected void lazyLoad() {

    }

    /**
     * 可见
     */
    protected void onVisible() {
        if(MyApplication.getInstance().isLogin()){

        lazyLoad();
        }
    }
}
