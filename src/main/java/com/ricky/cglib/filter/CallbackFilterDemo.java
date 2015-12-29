package com.ricky.cglib.filter;

import com.ricky.cglib.interceptor.AopInterceptor;
import com.ricky.cglib.log.UserActivityLogginImpl;

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
