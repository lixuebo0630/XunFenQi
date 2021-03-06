package com.xunfenqi.net.http;
/**
 * 
* @ClassName: AbStringHttpResponseListener 
* @Description: Http字符串响应监听器
* @author Xuebo Li
* @date 2015-8-14 下午2:40:58 
*
 */

public abstract class AbStringHttpResponseListener extends AbHttpResponseListener{
	
	
    /**
     * 构造.
     */
	public AbStringHttpResponseListener() {
		super();
	}

	/**
	 * 描述：获取数据成功会调用这里.
	 *
	 * @param statusCode the status code
	 * @param content the content
	 */
    public abstract void onSuccess(int statusCode,String content);
    
    
    /**
     * 成功消息.
     *
     * @param statusCode the status code
     * @param content the content
     */
    public void sendSuccessMessage(int statusCode,String content){
    	sendMessage(obtainMessage(AbHttpClient.SUCCESS_MESSAGE, new Object[]{statusCode, content}));
    }
		

}
