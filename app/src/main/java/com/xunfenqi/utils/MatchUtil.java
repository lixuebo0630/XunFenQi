package com.xunfenqi.utils;

/**
 * 字符串匹配工具类
 * 
 * @author Administrator
 * 
 */
public class MatchUtil {
	// 电话号码的正则表达式
	static String NumberRE1 = "(^(0\\d{2,3})?(\\d{7,8})(,\\d{3,})?)$";
	// 手机号码正则表达式
	// private static String PHONEREG =
	// "^(13\\d|147|15[0-35-9]|18[025-9])\\d{8}$";
	private static String PHONEREG = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$";

	// 以字母开头的英文或数字组合3到50位字符串
	private static String CHACCOUNT = "^\\w{3,20}$";

	// private static String CHACCOUNT = "^[A-Z0-9a-z_]{3,50}$";

	private static String PASSWORDREG = "^[\\x20-\\x7E]{6,50}$";
	private static String ALLNUMBER = "^\\d{6,50}";


	/**
	 * 验证字符串是否是手机号码
	 * 
	 * @param matchStr
	 *            代匹配的字符串
	 * @return 如果是返回true否则返回false
	 */
	public static boolean isPhoneNum(String matchStr) {

		return matchStr == null ? false : matchStr.matches(PHONEREG);

	}

	/**
	 * 判断是否为全数字
	 * 
	 * @param matchStr
	 * @return
	 */
	public static boolean isAllNumber(String matchStr) {
		return matchStr == null ? false : matchStr.matches(ALLNUMBER);

	}

	/**
	 * 
	 * @Title: isIDNumber
	 * @Description: 判断是否为身份证号
	 * @param: @param matchStr
	 * @param: @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isIDNumber(String matchStr) {

		String regx = "[0-9]{17}x";
		String reg1 = "[0-9]{15}";
		String regex = "[0-9]{18}";
		return matchStr.matches(regx) || matchStr.matches(reg1)
				|| matchStr.matches(regex);

	}

	/**
	 * 验证字符串是否是电话（包括手机）号码
	 * 
	 * @param matchStr
	 *            代匹配的字符串
	 * @return 如果是返回true否则返回false
	 */
	public static boolean isContactNum(String matchStr) {
		return matchStr == null ? false
				: (matchStr.matches(PHONEREG) || matchStr.matches(NumberRE1));
	}

	/**
	 * 判断是否是合法的帐号
	 * 
	 * @param matcherStr
	 *            待检验的字符串
	 * @return 如果合法返回true 否则返回false
	 */
	public static boolean isLicitAccount(String matcherStr) {

		return matcherStr == null ? false
				: (matcherStr.matches(PHONEREG) || matcherStr
						.matches(CHACCOUNT));

	}

	public static boolean isLicitPassword(String matcherStr) {

		return matcherStr == null ? false : matcherStr.matches(PASSWORDREG);

	}
}
