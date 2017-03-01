package com.xunfenqi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @ClassName: SettingUtils
 * @Description: 设置sp信息工具类
 * @author Xuebo Li
 * @date 2015-8-17 上午9:37:08
 * 
 */
public class SettingUtils {

	public static final String USERID = "userId";// 用户id
	public static final String ZHZZC = "zhzzc";// 账户总资产
	public static final String KYYE = "kyye";// 可用余额
	public static final String DJZJ = "djzj";// 冻结资金
	public static final String DSSY = "dssy";// 待收收益
	public static final String DSBJ = "dsbj";// 待收本金
	public static final String LJSY = "ljsy";// 累计收益
	public static final String LJTZ = "ljtz";// 累计投资
	public static final String USERNAME = "userName";// 用户名称
	public static final String LOGIN_NAME = "loginName"; // 账号
	public static final String TEL = "tel";// 会员手机号
	public static final String SFRZ = "sfrz";// 会员认证 0代表认证 1代表没认证
	public static final String PW = "pw";// 会员支付密码 0代表有 1代表没有
	public static final String HASZHIHANG = "hasZhihang";// 0无支行信息 1有支行
	public static final String LOGINPW = "userPsw";
	public static final String YHKH = "yhkh";// 银行卡
	public static final String SFZH = "sfzh";// 身份证
	public static final String TOKEN = "token";
	public static final String SETTING_PREF = "haihe_setting";
	public static final String HAS_MSG = "hasMsg";
	public static final String HAS_HB = "hasHb";
	public static final String ISOLDUSER = "isOldUser";
	public static final String REG_DATETIME = "regDatetime";
	public static SettingUtils utilInstance;
	private Context mContext;

	// 单例
	public static SettingUtils getInstance(Context context) {
		if (utilInstance == null) {
			utilInstance = new SettingUtils(context);
		}
		return utilInstance;
	}

	private SettingUtils(Context context) {
		this.mContext = context;
	}

	public void saveValue(String key, int value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public int getValue(String key, int defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getInt(key, defaultValue);
	}

	public void saveValue(String key, long value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getValue(String key, long defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getLong(key, defaultValue);
	}

	public void saveValue(String key, String value) {
		try {
			SharedPreferences pref = mContext.getSharedPreferences(
					SETTING_PREF, Context.MODE_PRIVATE);
			Editor editor = pref.edit();
			// String encrypt = AESUtils.encrypt("haihehe", value);
			// String encrypt = AESUtils.encrypt("haihe111", value);
			// editor.putString(key, encrypt);
			// String encrypt = AESUtils.encrypt("haihe111", value);
			editor.putString(key, value);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue(String key, String defaultValue) {
		try {
			SharedPreferences pref = mContext.getSharedPreferences(
					SETTING_PREF, Context.MODE_PRIVATE);
			String value = pref.getString(key, defaultValue);
			// String decrypt = AESUtils.decrypt("haihehe", value);
			// String decrypt = AESUtils.decrypt("haihe111", value);
			return value;
			// String decrypt = AESUtils.decrypt("haihe111", value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	public void saveValue(String key, boolean value) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getValue(String key, boolean defaultValue) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		return pref.getBoolean(key, defaultValue);
	}

	public void remove(String key) {
		SharedPreferences pref = mContext.getSharedPreferences(SETTING_PREF,
				Context.MODE_PRIVATE);
		Editor editor = pref.edit().remove(key);
		editor.commit();
	}

}
