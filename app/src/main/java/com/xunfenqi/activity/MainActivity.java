package com.xunfenqi.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.fragment.main.HomeFragment;
import com.xunfenqi.fragment.main.InviteFragment;
import com.xunfenqi.fragment.main.MyFragment;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.global.AppManager;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.global.SwitchMainRadioCallback;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.DataCleanManager;
import com.xunfenqi.utils.DoubleClickExitHelper;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.view.NoScrollViewPager;

import java.util.ArrayList;


/**
 * 
 * @ClassName: MainActivity
 * @Description: 主界面
 * @author Xuebo Li
 * @date 2015-8-17 上午10:32:39
 * 
 */
public class MainActivity extends BaseActivity implements
		SwitchMainRadioCallback {

	public static final String tag = "MainActivity";
	private NoScrollViewPager layout_content;
	private RadioGroup main_radio;
	private ArrayList<Fragment> fragmentsList;
	private DoubleClickExitHelper mDoubleClickExit;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// EventBus.getDefault().register(this);

		// 回调管理器
		CallBackManager.getInstance().setOnSwitchMainRadioCallback(this);
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_main);
		mDoubleClickExit = new DoubleClickExitHelper(this);
		main_radio = (RadioGroup) findViewById(R.id.rg_main_activity_radio);
		layout_content = (NoScrollViewPager) findViewById(R.id.v_main_activity_layout_content);
		layout_content.setOffscreenPageLimit(2);
		layout_content.setNoScroll(true);
		 if (MyApplication.isFristStart()) {
		 DataCleanManager.cleanInternalCache(MyApplication.getInstance());
		 MyApplication.setFristStart(false);
		 }



	}

	@Override
	public void initData() {
		// RadioButton的监听
		main_radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_main_activity_home:
					layout_content.setCurrentItem(0);
					break;
				case R.id.rb_main_activity_account:
					layout_content.setCurrentItem(1);
					break;
				case R.id.rb_main_activity_more:
					layout_content.setCurrentItem(2);
					break;
				}
			}
		});
		// 默认选中首页
		main_radio.check(R.id.rb_main_activity_home);
		fragmentsList = new ArrayList<Fragment>();

		HomeFragment homeFragment = new HomeFragment();
		InviteFragment inviteFragment =new InviteFragment();
		MyFragment myFragment = new MyFragment();
		fragmentsList.add(homeFragment);
		fragmentsList.add(inviteFragment);
		fragmentsList.add(myFragment);
//		fragmentsList.add(myAccountFragment);
//		fragmentsList.add(moreFragment);

		layout_content.setAdapter(new MyFragmentPagerAdapter(
				getFragmentManager(), fragmentsList));
		// 设置viewPager的监听
		// layout_content.setOnPageChangeListener(new OnPageChangeListener() {
		// @Override
		// public void onPageSelected(int position) {
		// // Fragment baseFragment = fragmentsList.get(position);
		// }
		//
		// @Override
		// public void onPageScrolled(int position, float positionOffset,
		// int positionOffsetPixels) {
		// }
		//
		// @Override
		// public void onPageScrollStateChanged(int state) {
		// }
		// });
		// Fragment baseFragment = fragmentsList.get(0);

	}

	@Override
	public void initActionBar() {
	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> listFragments;

		public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
			super(fm);
			listFragments = al;
		}

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			if (position < listFragments.size()) {
				fragment = listFragments.get(position);
			} else {
				fragment = listFragments.get(0);
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return listFragments.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否退出应用
			return mDoubleClickExit.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		if (MyApplication.getInstance().isLogin()) {
			// 如果登录了没设置手势密码 跳转到设置手势密码界面
			if ("".equals(SettingUtils.getInstance(MainActivity.this).getValue(
					AbConstant.PW_NINELOCK, ""))) {
				ActivityUtil.startActivity(MainActivity.this,
						SetNineLockActivity.class);
			}
		}

		// 极光推送统计
	//	JPushInterface.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 极光推送统计
	//	JPushInterface.onPause(this);
	}

	// 回调管理器---选中主页某页
	@Override
	public void switchRadio(int position) {
		switch (position) {
		case 0:
			((RadioButton) findViewById(R.id.rb_main_activity_home))
					.setChecked(true);
			break;
		case 1:
			((RadioButton) findViewById(R.id.rb_main_activity_account))
					.setChecked(true);
			break;
		case 2:
			((RadioButton) findViewById(R.id.rb_main_activity_more))
					.setChecked(true);
			break;
		default:
			break;
		}
	}

}
