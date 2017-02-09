package com.bytebeats.code.cglib.filter;

import com.bytebeats.code.cglib.interceptor.AopInterceptor;
import com.bytebeats.code.cglib.log.UserActivityLogginImpl;

/**
 * 方法过滤器
 * @author Ricky Fung
 *
 */
public class CallbackFilterDemo {

	public static void main(String[] args) {
		
		UserActivityLogginImpl activityLogginImpl = new UserActivityCglibCallbackProxy(new AopInterceptor()).getProxyInstance();
		activityLogginImpl.login("ricky", "abc");
	}
}
