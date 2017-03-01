package com.xunfenqi.net.task;



/**
 * 
* @ClassName: AbTaskObjectListener 
* @Description: 数据执行的接口.
* @author Xuebo Li
* @date 2015-8-14 下午2:46:17 
*
 */
public abstract class AbTaskObjectListener extends AbTaskListener{
	
	/**
	 * Gets the object.
	 *
	 * @param <T> the generic type
	 * @return 返回的结果对象
	 */
    public abstract <T> T getObject();
    
    /**
     * 描述：执行开始后调用.
     *
     * @param <T> the generic type
     * @param obj the obj
     */
    public abstract <T> void update(T obj); 
    
	
}
