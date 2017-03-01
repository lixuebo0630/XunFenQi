package com.xunfenqi.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GuideViewPagerAdapter
 * @Description: 引导界面ViewPager适配器
 * @author Xuebo Li
 * @date 2015-8-14 下午4:24:04
 * 
 */
public class GuideViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	public GuideViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public GuideViewPagerAdapter(FragmentManager fragmentManager,
			List<Fragment> arrayList) {
		super(fragmentManager);
		this.fragmentList = arrayList;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
