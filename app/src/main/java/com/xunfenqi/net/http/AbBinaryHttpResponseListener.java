package com.xunfenqi.net.http;

/**
 * 
* @ClassName: AbBinaryHttpResponseListener 
* @Description: Http二进制响应监听器
* @author Xuebo Li
* @date 2015-8-14 下午2:38:49 
*
 */
public abstract class AbBinaryHttpResponseListener extends AbHttpResponseListener{
	
    /**
     * 构造.
     */
	public AbBinaryHttpResponseListener() {
		super();
	}
	
	/**
	 * 描述：获取数据成功会调用这里.
	 *
	 * @param statusCode the status code
	 * @param content the content
	 */
    public abstract void onSuccess(int statusCode,byte[] content);
    

	/**
     * 成功消息.
     *
     * @param statusCode the status code
     * @param content the content
     */
    public void sendSuccessMessage(int statusCode,byte[] content){
    	sendMessage(obtainMessage(AbHttpClient.SUCCESS_MESSAGE, new Object[]{statusCode, content}));
    }
    

}
