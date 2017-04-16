/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.chinazccy.manpuman.activity
 * Date:2015-9-9下午2:28:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.fragment.EnchashmentRecordlFragment;
import com.xunfenqi.global.CallBackManager;
import com.xunfenqi.global.EnchashmentRecordClickCallback;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;


/**
 * 
 * @ClassName: EnchashmentRecordActivity
 * @Description: 提现记录界面
 * @author Xuebo Li
 * @date 2015-10-14 上午9:54:30
 * 
 */
public class EnchashmentRecordActivity extends BaseActivity implements
		OnClickListener {

	private PopupWindow popupWindow;
	private FragmentManager fragmentManager;
	private EnchashmentRecordlFragment enchashmentRecordlFragment;
	private EnchashmentRecordClickCallback enchashmentRecordClickCallback;
	private LinearLayout bt_dismiss_touming;
	private TextView tv_title, bt_success, bt_fail, bt_process, bt_all;
	private ImageView iv_title;
	private RelativeLayout bt_pop;

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_enchashment_record);

		bt_pop = (RelativeLayout) findViewById(R.id.rl_btn_enchashment_record_act_popwindow);
		bt_pop.setOnClickListener(this);
		tv_title = (TextView) findViewById(R.id.tv_enchashment_record_act_title);
		iv_title = (ImageView) findViewById(R.id.iv_enchashment_record_act_title);

	}

	@Override
	public void initData() {
		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		enchashmentRecordlFragment = new EnchashmentRecordlFragment();
		transaction.replace(R.id.fl_enchashment_record_act_content,
				enchashmentRecordlFragment).commit();
	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "提现记录");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(45),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_btn_enchashment_record_act_popwindow: // 显示popupwindow
			enchashmentRecordClickCallback = CallBackManager.getInstance()
					.getEnchashmentRecordClickCallback();
			showPopupWindow();
			break;
		case R.id.tv_pop_enchashment_record_act_all:
			tv_title.setText("全部");
			popupWindow.dismiss();
			// 刷新数据
			if (enchashmentRecordClickCallback != null) {
				enchashmentRecordClickCallback.switchPresentStatus("a-b-c");
			}

			break;
		case R.id.tv_pop_enchashment_record_act_fail:
			tv_title.setText("失败");
			popupWindow.dismiss();
			// 刷新数据
			if (enchashmentRecordClickCallback != null) {
				enchashmentRecordClickCallback.switchPresentStatus("b");
			}
			break;
		case R.id.tv_pop_enchashment_record_act_success:
			tv_title.setText("成功");
			popupWindow.dismiss();
			if (enchashmentRecordClickCallback != null) {
				enchashmentRecordClickCallback.switchPresentStatus("a");
			}
			// 刷新数据
			break;
		case R.id.tv_pop_enchashment_record_act_process:
			tv_title.setText("处理中");
			popupWindow.dismiss();
			if (enchashmentRecordClickCallback != null) {
				enchashmentRecordClickCallback.switchPresentStatus("c");
			}
			// 刷新数据
			break;
		case R.id.ll_pop_enchashment_record_act_touming:
			popupWindow.dismiss();
			break;

		default:
			break;
		}

	}

	private void showPopupWindow() {
		iv_title.setImageResource(R.drawable.pop_up);
		View view = View.inflate(this,
				R.layout.layout_pop_enchashment_record_act, null);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		bt_dismiss_touming = (LinearLayout) view
				.findViewById(R.id.ll_pop_enchashment_record_act_touming);
		bt_success = (TextView) view
				.findViewById(R.id.tv_pop_enchashment_record_act_success);
		bt_fail = (TextView) view
				.findViewById(R.id.tv_pop_enchashment_record_act_fail);
		bt_all = (TextView) view
				.findViewById(R.id.tv_pop_enchashment_record_act_all);
		bt_process = (TextView) view
				.findViewById(R.id.tv_pop_enchashment_record_act_process);

		bt_dismiss_touming.setOnClickListener(this);
		bt_success.setOnClickListener(this);
		bt_fail.setOnClickListener(this);
		bt_process.setOnClickListener(this);
		bt_all.setOnClickListener(this);

		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);

		popupWindow.setFocusable(true);
		popupWindow.showAsDropDown(bt_pop);

		// 添加pop窗口关闭事件
		popupWindow.setOnDismissListener(new poponDismissListener());
	}

	// popupwindow 关闭监听
	class poponDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {

			iv_title.setImageResource(R.drawable.pop_down);
		}
	}

}
