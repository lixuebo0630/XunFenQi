package com.xunfenqi.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.xunfenqi.R;
import com.xunfenqi.adapter.GuideViewPagerAdapter;
import com.xunfenqi.fragment.guide.Fragment1;
import com.xunfenqi.fragment.guide.Fragment4;
import com.xunfenqi.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GuideViewPagerActivity
 * @Description: 引导界面
 * @author Xuebo Li
 * @date 2015-8-14 下午4:02:11
 */
public class GuideViewPagerActivity extends FragmentActivity {
	private ViewPager mVPActivity;

	private Fragment1 mFragment1;
//	private Fragment2 mFragment2;
//	private Fragment3 mFragment3;
	private Fragment4 mFragment4;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	// 放置点的结合
	private List<View> viewList = new ArrayList<View>();
	private PagerAdapter mPgAdapter;

	private LinearLayout dots_ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_viewpager);

		initView();
		initDot();
	}

	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		dots_ll = (LinearLayout) findViewById(R.id.dots_ll_guide);
		mFragment1 = new Fragment1();
//		mFragment2 = new Fragment2();
//		mFragment3 = new Fragment3();
		mFragment4 = new Fragment4();
		mListFragment.add(mFragment1);
//		mListFragment.add(mFragment2);
//		mListFragment.add(mFragment3);
		mListFragment.add(mFragment4);
		mPgAdapter = new GuideViewPagerAdapter(getFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);

		mVPActivity.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < mListFragment.size(); i++) {
					if (i == arg0) {
//						viewList.get(arg0).setBackgroundResource(
//								R.drawable.dot_focus_2);
					} else {
//						viewList.get(i).setBackgroundResource(
//								R.drawable.dot_blur_2);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	private void initDot() {
		dots_ll.removeAllViews();
		viewList.clear();

		for (int i = 0; i < mListFragment.size(); i++) {
			View view = new View(GuideViewPagerActivity.this);
			if (i == 0) {
				//view.setBackgroundResource(R.drawable.dot_focus);
			} else {
				//view.setBackgroundResource(R.drawable.dot_blur);
			}
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					UIUtils.dip2px(10), UIUtils.dip2px(10));
			view.setLayoutParams(layoutParams);
			layoutParams.setMargins(5, 0, 5, 0);
			dots_ll.addView(view);
			viewList.add(view);
		}
	}

}
