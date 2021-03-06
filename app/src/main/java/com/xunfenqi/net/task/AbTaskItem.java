package com.xunfenqi.net.task;

/**
 * 
* @ClassName: AbTaskItem 
* @Description: 数据执行单位.
* @author Xuebo Li
* @date 2015-8-14 下午2:45:28 
*
 */
public class AbTaskItem { 
	
	/** 记录的当前索引. */
	private int position;
	 
 	/** 执行完成的回调接口. */
    private AbTaskListener listener; 
    
	/**
	 * Instantiates a new ab task item.
	 */
	public AbTaskItem() {
		super();
	}

	/**
	 * Instantiates a new ab task item.
	 *
	 * @param listener the listener
	 */
	public AbTaskItem(AbTaskListener listener) {
		super();
		this.listener = listener;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Gets the listener.
	 *
	 * @return the listener
	 */
	public AbTaskListener getListener() {
		return listener;
	}

	/**
	 * Sets the listener.
	 *
	 * @param listener the new listener
	 */
	public void setListener(AbTaskListener listener) {
		this.listener = listener;
	}

} 

