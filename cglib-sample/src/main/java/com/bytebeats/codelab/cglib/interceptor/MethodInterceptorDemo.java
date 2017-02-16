package com.bytebeats.codelab.cglib.interceptor;

import com.bytebeats.codelab.cglib.log.UserActivityLogginImpl;

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
