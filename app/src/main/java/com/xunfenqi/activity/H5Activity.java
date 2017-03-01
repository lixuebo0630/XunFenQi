/**
 * Project Name:HaiHeFinance
 * File Name:AboutUsActivity.java
 * Package Name:com.haihefinance.activity
 * Date:2015-9-17下午1:40:51
 * Copyright (c) 2015, haihejinrong.com All Rights Reserved.
 *
 */

package com.xunfenqi.activity;

import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xunfenqi.R;
import com.xunfenqi.application.MyApplication;
import com.xunfenqi.base.BaseActivity;
import com.xunfenqi.net.network.Base64Utils;
import com.xunfenqi.net.network.RSACoder;
import com.xunfenqi.net.network.RSAUtil;
import com.xunfenqi.utils.AbViewUtil;
import com.xunfenqi.utils.ActivityUtil;
import com.xunfenqi.utils.UIUtils;
import com.xunfenqi.view.titlebar.AbTitleBar;

/**
 * @date: 2015-9-17 下午1:40:51
 * @author: XueBo Li
 * @version:
 * @description: 关于我们界面
 * @see
 */
public class H5Activity extends BaseActivity {

	private WebView wv;
	private String title = "";
	private String url = "";

	@Override
	public void initView() {
		setAbContentView(R.layout.activity_h5);
		title = getIntent().getExtras().getString("title");
		url = getIntent().getExtras().getString("url");

		wv = (WebView) findViewById(R.id.wv_act_aboutus);
	}

	@Override
	public void initData() {
		// 加载html界面
		// 支持javascript
		wv.getSettings().setJavaScriptEnabled(true);
		// wv.loadUrl(AbConstant.BASE_URL +
		// "/usercenter/toViewDzhtApp.do?userId=");
		wv.loadUrl(url);

		wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wv.getSettings().setDomStorageEnabled(true);
		// wv.loadUrl("http://v.youku.com/v_show/id_XMTQwMzQ3NzQxMg==.html");
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		// wv.loadUrl("http://192.168.12.253:8081/usercenter/toViewDzhtApp.do?userId=e38154c9e22e4bedbb52c8ff62c8d2fb&dzhtId=20150306230043937285");

		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setDefaultTextEncodingName("utf-8");
		wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		// 开启 DOM storage API 功能
		wv.getSettings().setDomStorageEnabled(true);
		// 支持通过JS打开新窗口
		wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onReceivedSslError(WebView view,
					android.webkit.SslErrorHandler handler,
					android.net.http.SslError error) {
				handler.proceed();
			};
		});

		wv.addJavascriptInterface(new Object() {
			@JavascriptInterface
			public String HtmlcallJava() throws Exception {
				String loginUid = MyApplication.getInstance().getLoginUid();

				byte[] encryptByPublicKey = RSACoder.encryptByPublicKey(
						loginUid.getBytes(), RSAUtil.publicKey);
				String encode = Base64Utils.encode(encryptByPublicKey);

				if (loginUid.isEmpty()) {
					MyApplication.getInstance().setNineFlag(3);
					ActivityUtil.startActivity(H5Activity.this,
							LoginActivity.class);
					return null;
				} else {
					return encode;
				}

			}

			@JavascriptInterface
			public void toLogin() {
				MyApplication.getInstance().setNineFlag(3);
				ActivityUtil
						.startActivity(H5Activity.this, LoginActivity.class);

			}


			@JavascriptInterface
			public void toIndex() {
				wv.post(new Runnable() {
					@Override
					public void run() {
						wv.clearHistory();
						wv.clearCache(true);
					}
				});
			}
		}, "callAndroid");

	}

	@Override
	public void initActionBar() {
		// 初始化ActionBar
		AbTitleBar tTitleBar = AbViewUtil.getTTitleBar(this, title);
		tTitleBar.setLogo(R.drawable.titlebar_back);
		tTitleBar.setTitleTextMargin(0, UIUtils.dip2px(14), UIUtils.dip2px(58),
				UIUtils.dip2px(14));
	}
}
