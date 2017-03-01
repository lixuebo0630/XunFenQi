package com.xunfenqi.application;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.tencent.bugly.Bugly;
import com.weavey.loading.lib.LoadingLayout;
import com.xunfenqi.R;
import com.xunfenqi.global.AbConstant;
import com.xunfenqi.model.domain.UserCenterInfo;
import com.xunfenqi.utils.SettingUtils;

import java.util.ArrayList;

/**
 * 
 * @author Xuebo Li 2015-7-27 下午1:05:18
 * @version
 * @description Application基类
 * @revise
 */
public class MyApplication extends Application {
	// 获取实例
	private static final String KEY_FRITST_START = "KEY_FRITST_START";
	private static MyApplication instance;
	// 获取到主线程的handler
	private static Handler mMainThreadHandler = null;
	// 获取到主线程
	private static Thread mMainThread = null;
	// 获取到主线程的id
	private static int mMainThreadId;
	// 获取到主线程的looper
	private static Looper mMainThreadLooper = null;
	// activity启动栈，记录栈中的activity实例
	private static ArrayList<Activity> activitystack;
	// 登录用户的id
	private String loginUid = "";
	// 是否登录
	private boolean isLogin;

	public static final int LOGIN_SUCCESS = 1;
	public static final int REGIST_SUCCESS = 2;
	public static final int CHANGE_NINE = 3;

	public static final int INVEST_SANBIAO_FLAG = 1;
	public static final int INVEST_ZHAIQUAN_FLAG = 2;

	private int switchProductListTab = INVEST_SANBIAO_FLAG;
	private int nineFlag = LOGIN_SUCCESS;
	public int myRedRefreshFlag = 1;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThread = Thread.currentThread();
		this.mMainThreadId = android.os.Process.myTid();
		this.mMainThreadLooper = getMainLooper();
		activitystack = new ArrayList<Activity>();
		// Bugly开启
		// 异常处理
		Bugly.init(getApplicationContext(), "9685d195a3", false);
		// 初始化登
		initLogin();



		LoadingLayout.getConfig()
				.setErrorText("出错啦~请稍后重试！")
				.setEmptyText("抱歉，暂无数据")
				.setNoNetworkText("无网络连接，请检查您的网络···")
				.setErrorImage(R.mipmap.define_error)
				.setEmptyImage(R.mipmap.define_empty)
				.setNoNetworkImage(R.mipmap.define_nonetwork)
				.setAllTipTextColor(R.color.gray)
				.setAllTipTextSize(14)
				.setReloadButtonText("点我重试哦")
				.setReloadButtonTextSize(14)
				.setReloadButtonTextColor(R.color.gray)
				.setReloadButtonWidthAndHeight(150,40)
				.setAllPageBackgroundColor(R.color.white).setLoadingPageLayout(R.layout.define_loading_page);

	}

	/**
	 * 获得当前app运行的Context
	 * 
	 * @return
	 */
	public static MyApplication getInstance() {
		return instance;
	}

	// 对外暴露主线程的handler
	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	public int getSwitchProductListTab() {
		return switchProductListTab;
	}

	public void setSwitchProductListTab(int switchProductListTab) {
		this.switchProductListTab = switchProductListTab;
	}

	// 对外暴露主线程
	public static Thread getMainThread() {
		return mMainThread;
	}

	// 对外暴露主线程id
	public static int getMainThreadId() {
		return mMainThreadId;
	}

	public int getNineFlag() {
		return nineFlag;
	}

	public void setNineFlag(int nineFlag) {
		this.nineFlag = nineFlag;
	}

	// 对外暴露主线程的looper
	public static Looper getMainThreadLooper() {
		return mMainThreadLooper;
	}

	@Override
	public void onTerminate() {
		// 程序安全退出
		for (Activity activity : activitystack) {
			activity.finish();
		}
		activitystack.clear();
		super.onTerminate();
	}

	private void initLogin() {
		UserCenterInfo user = getLoginUser();
		if (null != user && user.getUserId().length() > 0) {
			isLogin = true;
			loginUid = user.getUserId();
		} else {
			this.cleanLoginInfo();
		}
	}

	/**
	 * 获得登录用户的信息
	 * 
	 * @return
	 */
	public UserCenterInfo getLoginUser() {
		UserCenterInfo user = new UserCenterInfo();
		user.setUserId(SettingUtils.getInstance(instance).getValue(
				SettingUtils.USERID, ""));
		user.setUserName(SettingUtils.getInstance(instance).getValue(
				SettingUtils.USERNAME, ""));
		user.setLoginName(SettingUtils.getInstance(instance).getValue(
				SettingUtils.LOGIN_NAME, ""));
		user.setTel(SettingUtils.getInstance(instance).getValue(
				SettingUtils.TEL, ""));
		user.setSfrz(SettingUtils.getInstance(instance).getValue(
				SettingUtils.SFRZ, ""));
		user.setPw(SettingUtils.getInstance(instance).getValue(SettingUtils.PW,
				""));
		user.setKyye(SettingUtils.getInstance(instance).getValue(
				SettingUtils.KYYE, ""));
		user.setHasMsg(SettingUtils.getInstance(instance).getValue(
				SettingUtils.HAS_MSG, ""));
		user.setSfzh(SettingUtils.getInstance(instance).getValue(
				SettingUtils.SFZH, ""));
		user.setYhkh(SettingUtils.getInstance(instance).getValue(
				SettingUtils.YHKH, ""));

		user.setYqm(SettingUtils.getInstance(instance).getValue("yqm",""));
		user.setWdhy(SettingUtils.getInstance(instance).getValue("wdhy",""));
		user.setZqhb(SettingUtils.getInstance(instance).getValue("zqhb",""));
		return user;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void saveUserInfo(final UserCenterInfo user) {
		this.isLogin = true;
		SettingUtils.getInstance(instance).saveValue(SettingUtils.USERID,
				user.getUserId());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.KYYE,
				user.getKyye());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.USERNAME,
				user.getUserName());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.LOGIN_NAME,
				user.getLoginName());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.TEL,
				user.getTel());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.SFRZ,
				user.getSfrz());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.PW,
				user.getPw());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.KYYE,
				user.getKyye());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.HAS_MSG,
				user.getHasMsg());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.YHKH,
				user.getYhkh());
		SettingUtils.getInstance(instance).saveValue(SettingUtils.SFZH,
				user.getSfzh());
		SettingUtils.getInstance(instance).saveValue("yqm",
				user.getYqm());
		SettingUtils.getInstance(instance).saveValue("wdhy",
				user.getWdhy());
		SettingUtils.getInstance(instance).saveValue("zqhb",
				user.getZqhb());

	}

	/**
	 * 清除登录信息
	 */
	public void cleanLoginInfo() {
		this.loginUid = "";
		this.isLogin = false;
		SettingUtils.getInstance(instance).remove(SettingUtils.USERID);
		SettingUtils.getInstance(instance).remove(SettingUtils.USERNAME);
		SettingUtils.getInstance(instance).remove(SettingUtils.TEL);
		SettingUtils.getInstance(instance).remove(SettingUtils.SFRZ);
		SettingUtils.getInstance(instance).remove(SettingUtils.PW);
		SettingUtils.getInstance(instance).remove(SettingUtils.HAS_MSG);
		SettingUtils.getInstance(instance).remove(SettingUtils.KYYE);
		SettingUtils.getInstance(instance).remove(SettingUtils.HAS_HB);
		SettingUtils.getInstance(instance).remove(SettingUtils.LOGIN_NAME);
		SettingUtils.getInstance(instance).remove(SettingUtils.HASZHIHANG);
		SettingUtils.getInstance(instance).remove(SettingUtils.ISOLDUSER);
		SettingUtils.getInstance(instance).remove(SettingUtils.REG_DATETIME);
		SettingUtils.getInstance(instance).remove(SettingUtils.LOGINPW);
		SettingUtils.getInstance(instance).remove(SettingUtils.YHKH);
		SettingUtils.getInstance(instance).remove(SettingUtils.SFZH);
		SettingUtils.getInstance(instance).remove(AbConstant.PW_NINELOCK);
		SettingUtils.getInstance(instance).remove("yqm");
		SettingUtils.getInstance(instance).remove("wdhy");
		SettingUtils.getInstance(instance).remove("zqhb");

	}

	public String getLoginUid() {
		return loginUid;
	}

	public void setLoginUid(String uid) {
		this.loginUid = uid;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * 用户注销
	 */
	public void Logout() {
		cleanLoginInfo();
		this.isLogin = false;
		this.loginUid = "";
		SettingUtils.getInstance(instance).remove(AbConstant.PW_NINELOCK);
	}

	public static boolean isFristStart() {
		return SettingUtils.getInstance(getInstance()).getValue(
				KEY_FRITST_START, true);
	}

	public static void setFristStart(boolean frist) {
		SettingUtils.getInstance(getInstance()).saveValue(KEY_FRITST_START,
				frist);
	}
}
