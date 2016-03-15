package com.ricky.codelab.cglib.interceptor;

import com.ricky.codelab.cglib.log.UserActivityLogginImpl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class UserActivityCglibMethodProxy {

	private MethodInterceptor methodInterceptor;
	public UserActivityCglibMethodProxy(MethodInterceptor methodInterceptor){
		this.methodInterceptor = methodInterceptor;
	}
	
	public UserActivityLogginImpl getProxyInstance(){  
		Enhancer en = new Enhancer();
	    //进行代理 
	    en.setSuperclass(UserActivityLogginImpl.class);  
	    en.setCallback(methodInterceptor);  
	    //生成代理实例  
	    return (UserActivityLogginImpl)en.create();    
	}  

}
