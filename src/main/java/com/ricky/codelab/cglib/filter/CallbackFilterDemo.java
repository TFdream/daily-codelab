package com.ricky.codelab.cglib.filter;

import com.ricky.codelab.cglib.interceptor.AopInterceptor;
import com.ricky.codelab.cglib.log.UserActivityLogginImpl;

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
