package com.bytebeats.codelab.cglib.filter;

import com.bytebeats.codelab.cglib.log.UserActivityLogginImpl;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;

public class UserActivityCglibCallbackProxy {

	private MethodInterceptor methodInterceptor;
	public UserActivityCglibCallbackProxy(MethodInterceptor methodInterceptor){
		this.methodInterceptor = methodInterceptor;
	}
	
	public UserActivityLogginImpl getProxyInstance(){
	    Enhancer en = new Enhancer();
	    //进行代理 
	    en.setSuperclass(UserActivityLogginImpl.class);  
	    en.setCallbacks(new Callback[]{methodInterceptor, NoOp.INSTANCE});  
	    en.setCallbackFilter(new MyCallbackFilter());  
	    //生成代理实例  
	    return (UserActivityLogginImpl)en.create();  
	}  

}
