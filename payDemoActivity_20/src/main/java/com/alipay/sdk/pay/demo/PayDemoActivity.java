package com.alipay.sdk.pay.demo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.pay.demo.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 *  重要说明:
 *  
 *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
 */
public class PayDemoActivity extends FragmentActivity {
	
	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016080300155618";
	
	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2eMvkkkTSnB6CxKgB9+aZFvIUmJy/jt2tMssWMeOj4NujlnV7qY7Pz5FHk+oBd7A+6JEYe1zj6Yg6pEnnWYuOYuSZGu89AG7tO7XvmJGiT8WsB9MYNRKXl27xfubcpa7x7HY5ay4iH7cUCwIe96tmWqH3oT0wQYW5sYX6yyYTF13TAYX8xJNWT0slddkRCkgv5/8BAuqyIKBmSNHUP7aLzTIoaAcWggWUu9LH4185N1o24n5U/qtejlk2iJ9gMfjVVoZfDLZ3wzL54UyaQ7uJG2p/Xw6VMmF6QYl/n1Th1hVRBh5lAm4+5fpTsit5waV+ulFu93KFcHvxEXQ33Jf9AgMBAAECggEBAJ0kBDswt/JEHoZxQ1qGmeEnAI06LGtPZpp8jqbrieYchCoqLby0ddeDItLoj/J5l59le2cA/isMd5RA3aBN3kBd7A3qQzNehyjobjMLSgfk+Q/AoUtMkVlZQWLZ7YkBalrPT7T9F3qto2WwYUA3/DSmQqvWALLpCnezaPVlM8HluY1Yf5bkoJIa2JiHjfpS6n0oi6Givd01lsHhGxY0IEA+Zte9HjYLsvO1RDMi6n0MspIv6sTqo8ilLZU69de5of7LOPpvRJFpkKzzF/13VBKLK407HoTdVH50g1CwXsZZ4WRXUltV//vK44Y+HgailCfEglnVSkLubD5BqGePd8UCgYEA/akSxboQ4bXssDEMdleaK9Bl+Gv61oWCPbNMeCs2ALxnW4/ScmM1W0kPVMDHPZJUuXg6iHI25U/Y8I2wW80yrkH7eR6e8TFiam6IZt68opSbLLXyZrCP3WlIqhS6g24gB4hCh2fPwcB+Qzo/UyUAcq2I4YO/pgsrYdkruoJ/9HcCgYEAuCejIkGab58pw1vK5BVacZ6xQ2J6QzYkV/OZjyMGULevk9h0PhCEPRxeQza+vVULMHuDkJrr1vhtuQ9/hhuXwua4LcMGQxQoAxPQg1skwLlSAb04S30JRj910/MwaAapS6zY13tLJLRQhZp38lmWUTzYLgZRH0BwYw8OseffuCsCgYBcB1hRNlVjnl5V+iWcVeIQXVZxj7N82wlKm192Ba9dCv2wwXhHmejEyrfuNtbSZuGoPI9364AxKAv8ElvWopbtk/DJm6tstE8RcDK6p7rmfWXjDcv5J7u6R7VeR5D3mB+0u+HvQGGm2NC2UU6iOr1z3bPYpbUFZ+4AXQs5aplDtwKBgB1BFrY7a7gttam2LE39YmfISOqmezuwHyflp9BIRRmPhJHdAeJWJ1u55POSf8x99RAXnvfa1HpK7stGPgDk4z/v/PQwFg+T5SNQA4DKetUu22sxTv8W9uE09Zfc+ySbYkuGQtBAYtiK2HvQ5RsXxjxTiRgOotZvGytxWelDJ5QPAoGBAKe5llUAByZ9Cv7IneAQD51ijdHTPgXASKnI7V/9QmFM7XL8uncGICBY2/7YCD4/TF+qnD/6NMuMwvI1JzT16898rYeRXzuCcCXGPBv9TMCjB2wbay4kcOoQsfz/fzSB5txQvC/ySwse5DLPhsi63uNSbqRwp6mKMY797TvR8pU9";
	public static final String RSA_PRIVATE = "";
	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case SDK_AUTH_FLAG: {
				@SuppressWarnings("unchecked")
				AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
				String resultStatus = authResult.getResultStatus();

				// 判断resultStatus 为“9000”且result_code
				// 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
				if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
					// 获取alipay_open_id，调支付时作为参数extern_token 的value
					// 传入，则支付账户为该授权账户
					Toast.makeText(PayDemoActivity.this,
							"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
							.show();
				} else {
					// 其他状态值则为授权失败
					Toast.makeText(PayDemoActivity.this,
							"授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_main);
	}
	
	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void payV2(View v) {
//		if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
//			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
//					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialoginterface, int i) {
//							//
//							finish();
//						}
//					}).show();
//			return;
//		}
//
		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * orderInfo的获取必须来自服务端；
		 */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		final String orderInfo = orderParam + "&" + sign;

		EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
		Log.d("ali",orderInfo);
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayDemoActivity.this);

			//	String ordInfo = "timestamp=2017-04-05++14%3A30%3A39&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%22393.33%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%9D%8E%E5%AD%A6%E5%8D%9A%E7%AC%AC1%E6%9C%9F%EF%BC%8C%E8%BF%98%E6%AC%BE%E6%80%BB%E9%A2%9D%EF%BC%9A393.33%22%2C%22out_trade_no%22%3A%2220170405143039042361985%22%7D&sign_type=RSA2&notify_url=http%3A%2F%2F60.205.107.161%3A8080%2Fapp_webservice_shopping%2Fnews%2FalipayBack.do&charset=utf-8&method=alipay.trade.app.pay&app_id=2016080300155618&version=1.0&sign=Qg7RwAGyXyYpshwkhj4ghRBUIbauWhX%2BP8IIm9ixr81uuxB42bbAKyXvru5A0GkKaY8V7mW6OaRiuuXhwB0sF5%2B0Idytw4eapeDnl8WShpPDnjwAMT3nGJsMbk5gW4Qt7v5pzLDBJE1XmtD3dfU%2Fis4L71nfkz4GTJsoVXsyuvk1occloVhKiw7TNtQqPXZamp7%2B%2FWZhENFVsw1ll%2FyCIdliUwzQr0f%2FJdMdvCokkG%2FSvcxKcmUkF5YDToZn9zelqkgrdfRC7iITd5uafX7sUfnPGB2gLT%2F%2B6AEoIvlWAmfXdLWbQCkfijERVaAz8XG%2FUUZ6AIt%2FmuPHTK0qhrwR9Q%3D%3D";
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				Log.d("ALiPay",result.toString());
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * 支付宝账户授权业务
	 * 
	 * @param v
	 */
	public void authV2(View v) {
		if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
				|| (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
				|| TextUtils.isEmpty(TARGET_ID)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
						}
					}).show();
			return;
		}

		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * authInfo的获取必须来自服务端；
		 */
		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		
		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
		final String authInfo = info + "&" + sign;
		Runnable authRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造AuthTask 对象
				AuthTask authTask = new AuthTask(PayDemoActivity.this);
				// 调用授权接口，获取授权结果
				Map<String, String> result = authTask.authV2(authInfo, true);

				Message msg = new Message();
				msg.what = SDK_AUTH_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread authThread = new Thread(authRunnable);
		authThread.start();
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		/**
		 * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
		 * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
		 * 商户可以根据自己的需求来实现
		 */
		String url = "http://m.taobao.com";
		// url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

}
