package com.ricky.cglib.filter;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import com.ricky.cglib.UserDao;
import com.ricky.cglib.interceptor.AopInterceptor;

/**
 * 方法过滤器
 * @author Ricky Fung
 *
 */
public class CallbackFilterDemo {

	public static void main(String[] args) {
		
		new CallbackFilterDemo().run();
	}

	private void run() {
		
		UserDao userDao = getProxyInstance(new AopInterceptor());
		userDao.insert();
		userDao.query();
	}

	public UserDao getProxyInstance(MethodInterceptor methodInterceptor){  
	    Enhancer en = new Enhancer();
	    //进行代理 
	    en.setSuperclass(UserDao.class);  
	    en.setCallbacks(new Callback[]{methodInterceptor, NoOp.INSTANCE});  
	    en.setCallbackFilter(new MyCallbackFilter());  
	    //生成代理实例  
	    return (UserDao)en.create();  
	}  

}
