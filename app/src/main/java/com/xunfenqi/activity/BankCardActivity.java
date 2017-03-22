/**
 * Project Name:HaiHeFinance
 * File Name:SettingActivity.java
 * Package Name:com.chinazccy.manpuman.activity
 * Date:2015-9-9下午2:28:29
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xunfenqi.R;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.SettingUtils;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

/**
 * 
 * @ClassName: BankCardActivity
 * @Description: 银行卡界面 (已绑定状态下)
 * @author Xuebo Li
 * @date 2015-10-12 上午11:08:52
 * 
 */
public class BankCardActivity extends BaseActivity implements OnClickListener {

	private ImageView iv_act_imgbank;
	private TextView tv_act_cardbank;
	private Button bt_act_enchashment, bt_act_invest, bt_act_recharge;
	private String cardlast;
	private String ssyh;
	private String fen, zhi;
	private RelativeLayout rl_btn_fzhmc;
	private TextView tv_fzhmc;
	private String fzhmc, imgPath;
			//cityCode, cityName, proCode, proName;

	private ImageLoader imageLoader = ImageLoader.getInstance();

	DisplayImageOptions options = new DisplayImageOptions.Builder()
			// .showImageOnLoading(R.drawable.ic_error_page)
			// .showImageOnFail(R.drawable.ic_error_page)
			.cacheInMemory(true).cacheOnDisk(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();

	@Override
	public void initView() {
		// 获取上层传递数据
		Bundle extras = getIntent().getExtras();
		cardlast = (String) extras.get("cardlast");
		imgPath = (String) extras.get("imgPath");
//		cityCode = (String) extras.get("cityCode");
//		cityName = (String) extras.get("cityName");
//		proCode = (String) extras.get("proCode");
//		proName = (String) extras.get("proName");

		// ssyh = (String) extras.get("ssyh");

		setAbContentView(R.layout.activity_bank_card);
		initImageLoader(BankCardActivity.this);
		iv_act_imgbank = (ImageView) this.findViewById(R.id.iv_act_imgbank);
		tv_act_cardbank = (TextView) this.findViewById(R.id.tv_act_cardbank);
		LinearLayout ll_fzhmc = (LinearLayout) findViewById(R.id.ll_bank_card_act_fzhmc);
		rl_btn_fzhmc = (RelativeLayout) findViewById(R.id.rl_btn_bank_card_act_fzhmc);

		tv_fzhmc = (TextView) findViewById(R.id.tv_bank_card_act_fzhmc);
		fen = SettingUtils.getInstance(BankCardActivity.this).getValue("fen",
				"");
		zhi = SettingUtils.getInstance(BankCardActivity.this).getValue("zhi",
				"");

		if (!TextUtils.isEmpty(fen) && !TextUtils.isEmpty(zhi)) {
			ll_fzhmc.setVisibility(View.VISIBLE);

			tv_fzhmc.setText(fen + zhi);
		} else {
			ll_fzhmc.setVisibility(View.GONE);
		}

		bt_act_enchashment = (Button) this
				.findViewById(R.id.bt_bank_card_act_to_enchashment);
		bt_act_invest = (Button) this
				.findViewById(R.id.bt_bank_card_act_to_invest);
		bt_act_recharge = (Button) this
				.findViewById(R.id.bt_bank_card_act_to_recharge);

		bt_act_enchashment.setOnClickListener(this);
		bt_act_invest.setOnClickListener(this);
		bt_act_recharge.setOnClickListener(this);
		rl_btn_fzhmc.setOnClickListener(this);

	}

	@Override
	public void initData() {
		if (cardlast != null && !"".equals(cardlast)
				&& !"null".equals(cardlast)) {

			tv_act_cardbank.setText(cardlast);
		}

		if (!TextUtils.isEmpty(imgPath)) {
			//imageLoader.displayImage(imgPath, iv_act_imgbank, options);

			Glide.with(this).load(imgPath).into(iv_act_imgbank);
		}


	}

	@Override
	public void initActionBar() {
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, "银行卡");
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//
//		case R.id.bt_bank_card_act_to_enchashment:// 跳转到提现界面
//
//			// String pw = MyApplication.getInstance().getLoginUser().getPw();
//			// if ("1".equals(pw)) { // 1代表没有设置支付密码
//			// new SweetAlertDialog(BankCardActivity.this,
//			// SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//			// .setTitleText("提示")
//			// .setContentText("请先设置支付密码")
//			// .setConfirmText("确认")
//			// .setConfirmClickListener(
//			// new SweetAlertDialog.OnSweetClickListener() {
//			// @Override
//			// public void onClick(SweetAlertDialog sDialog) {
//			// ActivityUtil.startActivity(
//			// BankCardActivity.this,
//			// SetDealPwdActivity.class);
//			// sDialog.dismiss();
//			// }
//			// }).show();
//			// return;
//			// }
//			ActivityUtil.startActivity(BankCardActivity.this,
//					EnchashmentActivity.class);
//			break;
//		case R.id.bt_bank_card_act_to_invest:
//
//			CallBackManager.getInstance().sendSwitchRadio(1);
//			finish();
//			break;
//		case R.id.bt_bank_card_act_to_recharge:// 跳转到充值
//			ActivityUtil.startActivity(BankCardActivity.this,
//					RechargeBindActivity.class);
//			break;
//		case R.id.rl_btn_bank_card_act_fzhmc:// 跳转到分支行名称
//
//			Intent intent = new Intent(this, FZHBankNameActivity.class);
//			Bundle mBundle = new Bundle();
//			mBundle.putString("yhkh", cardlast);
//			mBundle.putString("fen", fen);
//			mBundle.putString("zhi", zhi);
//
//			mBundle.putString("proName", proName);
//			mBundle.putString("cityName", cityName);
//			mBundle.putString("proCode", proCode);
//			mBundle.putString("cityCode", cityCode);
//			intent.putExtras(mBundle);
//			startActivity(intent);
//
//			break;
//		default:
//			break;
		}
	}

	@Override
	protected void onResume() {
		fen = SettingUtils.getInstance(BankCardActivity.this).getValue("fen",
				"");
		zhi = SettingUtils.getInstance(BankCardActivity.this).getValue("zhi",
				"");

		if (tv_fzhmc != null) {
			tv_fzhmc.setText(fen + zhi);
		}
		super.onResume();

	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove
				.build();
		ImageLoader.getInstance().init(config);
	}
}
