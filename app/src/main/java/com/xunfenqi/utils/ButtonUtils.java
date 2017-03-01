/**
 * Project Name:HaiHeFinance
 * File Name:ButtonUtils.java
 * Package Name:com.haihefinance.utils
 * Date:2015-8-26下午2:49:48
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.utils;

/**
 * @date: 2015-8-26 下午2:49:48
 * @author: XueBo Li
 * @version:
 * @description:
 * @see
 */
public class ButtonUtils {
	private static long lastClickTime;

	/**
	 * 
	 * @Title: isFastDoubleClick
	 * @Description:防止用户连续点击
	 * @param:
	 * @return boolean true:2000毫秒 连续点击 false
	 * @throws
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 3000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
	public static boolean isFastDoubleClickFor1s() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

}
