package com.xunfenqi.base;


import android.os.Bundle;

/**
 * 
 * @author Xuebo Li
 * 
 *         2015-7-27 下午1:21:33
 * 
 * @version
 * @description Activity的基类
 * 
 * @revise
 */

public abstract class BaseActivity extends AbActivity {

	private static final String TAG = BaseActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initActionBar();
		initData();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// super.onSaveInstanceState(outState);
	}

	// 初始化布局
	public abstract void initView();
	// 初始化数据
	public abstract void initData();
	// 初始化ActionBar
	public abstract void initActionBar();

	@Override
	protected void onResume() {
		super.onResume();
		// 友盟统计




	}



}
