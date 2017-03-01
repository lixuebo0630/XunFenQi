package com.xunfenqi.fragment.guide;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunfenqi.R;


/**
 * 
* @ClassName: Fragment2 
* @Description: 引导页的第二个界面
* @author Xuebo Li
* @date 2015-8-14 下午4:13:07 
*
 */
public class Fragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_2, container, false);
		return view;
	}

}
