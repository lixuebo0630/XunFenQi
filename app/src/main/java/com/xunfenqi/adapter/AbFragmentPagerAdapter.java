package com.xunfenqi.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * 
* @ClassName: AbFragmentPagerAdapter 
* @Description: 一个通用的Fragment适配器
* @author Xuebo Li
* @date 2015-8-17 下午2:03:46 
*
 */
public class AbFragmentPagerAdapter extends FragmentPagerAdapter {

	/** The m fragment list. */
	private ArrayList<Fragment> mFragmentList = null;

	/**
	 * Instantiates a new ab fragment pager adapter.
	 * @param mFragmentManager the m fragment manager
	 * @param fragmentList the fragment list
	 */
	public AbFragmentPagerAdapter(FragmentManager mFragmentManager,ArrayList<Fragment> fragmentList) {
		super(mFragmentManager);
		mFragmentList = fragmentList;
	}

	/**
	 * 描述：获取数量.
	 *
	 * @return the count
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	/**
	 * 描述：获取索引位置的Fragment.
	 *
	 * @param position the position
	 * @return the item
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		if (position < mFragmentList.size()){
			fragment = mFragmentList.get(position);
		}else{
			fragment = mFragmentList.get(0);
		}
		return fragment;

	}
}
