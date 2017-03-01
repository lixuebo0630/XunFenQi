package com.xunfenqi.fragment.guide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunfenqi.R;


/**
 * 
 * @ClassName: Fragment3
 * @Description: 引导界面的第三个界面
 * @author Xuebo Li
 * @date 2015-8-14 下午4:13:27
 * 
 */
public class Fragment3 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_3, container, false);
		return view;
	}

}
