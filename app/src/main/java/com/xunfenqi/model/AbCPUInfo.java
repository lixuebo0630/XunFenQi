package com.xunfenqi.model;

/**
 * 
* @ClassName: AbCPUInfo 
* @Description: CPU信息
* @author Xuebo Li
* @date 2015-8-14 下午2:41:40 
*
 */
public class AbCPUInfo {

	public String User;

	public String System;

	public String IOW;

	public String IRQ;

	public AbCPUInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbCPUInfo(String user, String system, String iOW, String iRQ) {
		super();
		User = user;
		System = system;
		IOW = iOW;
		IRQ = iRQ;
	}

}
