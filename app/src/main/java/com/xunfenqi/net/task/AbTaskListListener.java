package com.xunfenqi.net.task;

import java.util.List;

/**
 * 
* @ClassName: AbTaskListListener 
* @Description: 数据执行的接口.
* @author Xuebo Li
* @date 2015-8-14 下午2:46:01 
*
 */
public abstract class AbTaskListListener extends AbTaskListener{

	/**
	 * Gets the list.
	 *
	 * @return 返回的结果列表
	 */
	public abstract List<?> getList();

	/**
	 * 描述：执行完成后回调.
	 * 不管成功与否都会执行
	 * @param paramList 返回的List
	 */
    public abstract void update(List<?> paramList);
	
}
