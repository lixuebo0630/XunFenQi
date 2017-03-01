package com.xunfenqi.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

/**
 * 
 * @ClassName: RegistSuccessActivity
 * @Description: 注册成功界面
 * @author Xuebo Li
 * @date 2015-9-15 下午5:14:42
 * 
 */

public class RegistSuccessActivity extends BaseActivity implements
		OnClickListener {
	protected static final String TAG = "RegistSuccessActivity";
	private Button bt_safesetting;
	private TextView bt_regist_jump2home;

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_register_success);
		bt_safesetting = (Button) findViewById(R.id.bt_regist_success_safesetting);
		bt_regist_jump2home = (TextView) findViewById(R.id.bt_regist_success_jump2home);
		bt_safesetting.setOnClickListener(this);
		bt_regist_jump2home.setOnClickListener(this);

	}

	@Override
	public void initData() {
	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "注册成功");
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(45),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_regist_success_jump2home:// 跳转到首页

			ActivityUtil.startActivityAndFinish(RegistSuccessActivity.this,
					MainActivity.class);
			break;
//		case R.id.bt_regist_success_safesetting:// 跳转到安全设置
//			EventBus.getDefault().post(new EventPost());
//			SettingUtils.getInstance(RegistSuccessActivity.this).saveValue(
//					AbConstant.CLOSE_RED, AbConstant.CLOSE_RED);
//
//			ActivityUtil.startActivityAndFinish(RegistSuccessActivity.this,
//					SafeSettingActivity.class);
//			break;
		default:
			break;
		}
	}

}
