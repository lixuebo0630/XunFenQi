package com.xunfenqi.net.network;

import android.content.Context;

import com.xunfenqi.global.AbConstant;
import com.xunfenqi.net.soap.AbSoapListener;
import com.xunfenqi.net.soap.AbSoapParams;
import com.xunfenqi.net.soap.AbSoapUtil;


/**
 * 
 * @ClassName: NetWorkUtils
 * @Description: 网络请求工具类
 * @author Xuebo Li
 * @date 2015-8-14 下午3:17:16
 * 
 */
public class NetWorkUtils {

	public static void call(Context context, String sendData,
			AbSoapListener listener) {
		AbSoapUtil abSoapUtil = AbSoapUtil.getInstance(context);
		// 设置超时时间
		abSoapUtil.setTimeout(30000);

		AbSoapParams params = new AbSoapParams();
		// 发送的参数
		params.put("arg0", sendData);

		abSoapUtil.call(AbConstant.URL, AbConstant.NAMESPACE,
				AbConstant.METHODNAME, params, listener);

	}

	/**
	 * 
	 * @Title: string2Json
	 * @Description: 处理json数据
	 * @param: @param s
	 * @param: @return
	 * @return String
	 * @throws
	 */
	public static String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
