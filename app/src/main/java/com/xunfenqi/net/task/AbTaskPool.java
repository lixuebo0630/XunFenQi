package com.xunfenqi.net.task;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

import android.os.Handler;
import android.os.Message;
/**
 * 
* @ClassName: AbTaskPool 
* @Description: 线程池
* @author Xuebo Li
* @date 2015-8-14 下午2:46:30 
*
 */
public class AbTaskPool{
	
	/** 单例对象 The http pool. */
	private static AbTaskPool abTaskPool = null; 
	
	/** 线程执行器. */
	public static Executor mExecutorService = null;
	
	/**  存放返回的任务结果. */
    private static HashMap<String,Object> result;
	
	/** 下载完成后的消息句柄. */
    private static Handler handler = new Handler() { 
        @Override 
        public void handleMessage(Message msg) { 
        	AbTaskItem item = (AbTaskItem)msg.obj; 
        	if(item.getListener() instanceof AbTaskListListener){
        		((AbTaskListListener)item.getListener()).update((List<?>)result.get(item.toString())); 
        	}else if(item.getListener() instanceof AbTaskObjectListener){
        		((AbTaskObjectListener)item.getListener()).update(result.get(item.toString())); 
        	}else{
        		item.getListener().update(); 
        	}
        	result.remove(item.toString());
        } 
    }; 
    
	
	/**
	 * 构造线程池.
	 */
    protected AbTaskPool() {
        result = new HashMap<String,Object>();
        mExecutorService = AbThreadFactory.getExecutorService();
    } 
	
	/**
	 * 单例构造图片下载器.
	 *
	 * @return single instance of AbHttpPool
	 */
    public static AbTaskPool getInstance() { 
    	if (abTaskPool == null) { 
    		abTaskPool = new AbTaskPool(); 
        } 
        return abTaskPool;
    } 
    
    /**
     * 执行任务.
     * @param item the item
     */
    public void execute(final AbTaskItem item) {   
    	mExecutorService.execute(new Runnable() { 
    		public void run() {
    			try {
    				//定义了回调
                    if (item.getListener() != null) { 
                        if(item.getListener() instanceof AbTaskListListener){
                            result.put(item.toString(), ((AbTaskListListener)item.getListener()).getList());
                        }else if(item.getListener() instanceof AbTaskObjectListener){
                            result.put(item.toString(), ((AbTaskObjectListener)item.getListener()).getObject());
                        }else{
                        	item.getListener().get();
                            result.put(item.toString(), null);
                        }
                        
                    	//交由UI线程处理 
                        Message msg = handler.obtainMessage(); 
                        msg.obj = item; 
                        handler.sendMessage(msg); 
                    }                              
    			} catch (Exception e) { 
    				e.printStackTrace();
    			}                         
    		}                 
    	});                 
    	
    }
	
}
