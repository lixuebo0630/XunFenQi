package com.xunfenqi.net.http;

import java.util.List;

/**
 * 
* @ClassName: AbHttpListener 
* @Description: Http响应监听器
* @author Xuebo Li
* @date 2015-8-14 下午2:39:21 
*
 */
public abstract class AbHttpListener{
	
    /**
     * 构造.
     */
	public AbHttpListener() {
		super();
	}

	/**
	 * 请求成功.
	 *
	 * @param content the content
	 */
    public void onSuccess(String content){};
    
    /**
	 * 请求成功.
	 *
	 * @param list the list
	 */
    public void onSuccess(List<?> list){};
    
    /**
     * 请求失败.
     * @param content the content
     */
    public abstract void onFailure(String content);
    
    
    /**
	 * 描述：获取数据开始.
	 */
    public void onStart(){};
    
    
    /**
	 * 完成后调用，失败，成功，调用.
	 */
    public void onFinish(){};

}
