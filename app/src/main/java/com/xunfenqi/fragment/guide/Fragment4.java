package com.xunfenqi.fragment.guide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.xunfenqi.R;
import com.xunfenqi.activity.MainActivity;
import com.xunfenqi.utils.ActivityUtil;

/**
 * 
 * @ClassName: Fragment4
 * @Description: 引导页面的第四个界面
 * @author Xuebo Li
 * @date 2015-8-14 下午4:20:09
 * 
 */
public class Fragment4 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_4, container, false);
		view.findViewById(R.id.tvInNew).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 跳转到主界面
						ActivityUtil.startActivityAndFinish(getActivity(),
								MainActivity.class);
					}
				});
		return view;
	}

}
