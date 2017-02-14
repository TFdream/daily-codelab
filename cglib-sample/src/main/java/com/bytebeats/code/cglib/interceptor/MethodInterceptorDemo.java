package com.bytebeats.code.cglib.interceptor;

import com.bytebeats.code.cglib.log.UserActivityLogginImpl;

/**
 * 实现AOP
 * @author Ricky Fung
 *
 */
public class MethodInterceptorDemo {

	public static void main(String[] args) {
		
		UserActivityLogginImpl activityLogginImpl = new UserActivityCglibMethodProxy(new AopInterceptor()).getProxyInstance();
		activityLogginImpl.signin("abc@gmail.com", "ricky", "abc");
	}

}
