package com.ricky.cglib.jdk;

import com.ricky.cglib.log.UserActivityLogger;
import com.ricky.cglib.log.UserActivityLogginImpl;

/**
 * JDK动态代理实现
 * @author Ricky
 *
 */
public class JDKProxyDemo {

	public static void main(String[] args) {
		
		UserActivityLogger activityLogger = new UserActivityLoggerProxy(new UserActivityLogginImpl()).getProxyInstance();
		activityLogger.logout("ricky");
		
	}

}
