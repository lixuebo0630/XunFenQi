package com.xunfenqi.global;

import android.app.Activity;
import android.content.Context;

import com.xunfenqi.utils.AbLogUtil;

import java.util.Stack;

public class AppManager {

	private static final String tag = "AppManager";
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
		AbLogUtil.i(tag, "add:" + activity.getClass().getSimpleName());
	}

	// /**
	// * 获取当前Activity（堆栈中最后一个压入的）
	// */
	// public Activity currentActivity() {
	// Activity activity = activityStack.lastElement();
	// return activity;
	// }
	//
	// /**
	// * 结束当前Activity（堆栈中最后一个压入的）
	// */
	// public void finishActivity() {
	// Activity activity = activityStack.lastElement();
	// AbLogUtil.i(tag, "finish:" + activity.getClass().getSimpleName());
	// finishActivity(activity);
	// }

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			activityStack.remove(activity);
			activity.finish();
			AbLogUtil.i(tag, "finish:" + activity.getClass().getSimpleName());
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
				AbLogUtil.i(tag, "finish:"
						+ activity.getClass().getSimpleName());
				break;
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				finishActivity(activityStack.get(i));

				break;
			}
		}
		AbLogUtil.i(tag, "finishAll:");
		activityStack.clear();
	}

	/**
	 * 获取指定的Activity
	 * 
	 * @author kymjs
	 */
	public static Activity getActivity(Class<?> cls) {
		if (activityStack != null)
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		return null;
	}
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			// 退出应用前保存友盟统计数据
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}