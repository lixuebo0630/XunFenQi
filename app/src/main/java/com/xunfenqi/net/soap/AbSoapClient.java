package com.xunfenqi.net.soap;

import android.content.Context;

import com.xunfenqi.global.AbAppConfig;
import com.xunfenqi.global.AbAppException;
import com.xunfenqi.net.http.AbHttpStatus;
import com.xunfenqi.net.http.FakeX509TrustManager;
import com.xunfenqi.net.task.AbThreadFactory;
import com.xunfenqi.utils.AbAppUtil;
import com.xunfenqi.utils.AbLogUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.repackage.ksoap2.SoapEnvelope;
import org.repackage.ksoap2.serialization.SoapObject;
import org.repackage.ksoap2.serialization.SoapSerializationEnvelope;
import org.repackage.ksoap2.transport.HttpTransportSE;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 
 * @ClassName: AbSoapClient
 * @Description: Soap请求客户端
 * @author Xuebo Li
 * @date 2015-8-14 下午2:36:24
 * 
 */
public class AbSoapClient {

	/** 上下文. */
	private static Context mContext;
	/** 线程执行器. */
	public static Executor mExecutorService = null;

	/** WebService dotNet属性. */
	private boolean mDotNet = true;

	/** soap参数. */
	private AbSoapParams mParams = null;

	/** 超时时间. */
	public static final int DEFAULT_SOCKET_TIMEOUT = 30000;
	/** 超时时间. */
	private int mTimeout = DEFAULT_SOCKET_TIMEOUT;

	/**
	 * 初始化.
	 * 
	 * @param context
	 *            the context
	 */
	public AbSoapClient(Context context) {
		mContext = context;
		mExecutorService = AbThreadFactory.getExecutorService();
	}

	/**
	 * Call.
	 * 
	 * @param url
	 *            the url
	 * @param nameSpace
	 *            the name space
	 * @param methodName
	 *            the method name
	 * @param Params
	 *            the params
	 * @param listener
	 *            the listener
	 */
	public void call(final String url, final String nameSpace,
			final String methodName, AbSoapParams Params,
			final AbSoapListener listener) {
		this.mParams = Params;
		if (!AbAppUtil.isNetworkAvailable(mContext)) {
			listener.sendFailureMessage(AbHttpStatus.CONNECT_FAILURE_CODE,
					AbAppConfig.CONNECT_EXCEPTION, new AbAppException(
							AbAppConfig.CONNECT_EXCEPTION));
			return;
		}

		listener.sendStartMessage();

		mExecutorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					doCall(url, nameSpace, methodName, mParams, listener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		listener.sendFinishMessage();

	}

	/**
	 * Do call.
	 * 
	 * @param url
	 *            the url
	 * @param nameSpace
	 *            the name space
	 * @param methodName
	 *            the method name
	 * @param params
	 *            the params
	 * @param listener
	 *            the listener
	 */
	public void doCall(String url, String nameSpace, String methodName,
			AbSoapParams params, AbSoapListener listener) {

		String result = null;
		try {
			SoapObject request = new SoapObject(nameSpace, methodName);
			// 传递参数
			List<BasicNameValuePair> paramsList = params.getParamsList();
			for (NameValuePair nameValuePair : paramsList) {
				request.addProperty(nameValuePair.getName(),
						nameValuePair.getValue());
			}
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			// 添加验证头

			Element[] header = new Element[1];
			header[0] = new Element();
			header[0].setName("authHeader");

			Element userName = new Element();
			userName.setName("userName");
			userName.addChild(Node.TEXT, "username");
			header[0].addChild(Node.ELEMENT, userName);

			Element pass = new Element();
			pass.setName("password");
			pass.addChild(Node.TEXT, "password");
			header[0].addChild(Node.ELEMENT, pass);

			envelope.headerOut = header;
			envelope.bodyOut = request;
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);

//			HttpsTransportSE httpsTransportSE = new KeepAliveHttpsTransportSE(
//					"223.202.60.29", 80, "/app_webservice/publicRequest",
//					mTimeout);
			HttpTransportSE httpTransportSE = new HttpTransportSE(url, mTimeout);
			AbLogUtil.d(AbSoapClient.class, "--call--");
			// ////////////////////////////////
			FakeX509TrustManager.allowAllSSL();
			// ///////////////////////////////////

			// SSLContext context;
			// KeyStore ts = KeyStore.getInstance("BKS");
			// ts.load(mContext.getResources().openRawResource(R.raw.test),
			// "123456".toCharArray());
			// // TrustManagerFactory tmf = TrustManagerFactory
			// // .getInstance("X509");
			// // tmf.init(ts);
			// // TrustManager[] tm = tmf.getTrustManagers();
			// // context = SSLContext.getInstance("SSL");
			// // context.init(null, tm, null);
			//
			// // Create a TrustManager that trusts the CAs in our KeyStore
			// String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			// TrustManagerFactory tmf = TrustManagerFactory
			// .getInstance(tmfAlgorithm);
			// tmf.init(ts);
			//
			// // Create an SSLContext that uses our TrustManager
			// context = SSLContext.getInstance("TLS");
			// context.init(null, tmf.getTrustManagers(), null);
			//
			// ((HttpsServiceConnectionSE)
			// httpsTransportSE.getServiceConnection())
			// .setSSLSocketFactory(context.getSocketFactory());

			// //////////////////////////////////////
			//httpsTransportSE.call(null, envelope);
			 httpTransportSE.call(null, envelope);

			SoapObject bodyIn = (SoapObject) envelope.bodyIn;
			result = bodyIn.getProperty(0).toString();
			if (result != null) {
				listener.sendSuccessMessage(AbHttpStatus.SUCCESS_CODE, result);
			}
		} catch (Exception e) {

			e.printStackTrace();
			AbLogUtil
					.i(mContext, "request：" + url + ",error：" + e.getMessage());
			// 发送失败消息
			listener.sendFailureMessage(AbHttpStatus.UNTREATED_CODE,
					e.getMessage(), new AbAppException(e));
		}

	}

	/**
	 * 描述：设置连接超时时间.
	 * 
	 * @param timeout
	 *            毫秒
	 */
	public void setTimeout(int timeout) {
		this.mTimeout = timeout;
	}

	/**
	 * Checks if is dot net.
	 * 
	 * @return true, if is dot net
	 */
	public boolean isDotNet() {
		return mDotNet;
	}

	/**
	 * Sets the dot net.
	 * 
	 * @param dotNet
	 *            the new dot net
	 */
	public void setDotNet(boolean dotNet) {
		this.mDotNet = dotNet;
	}

}
