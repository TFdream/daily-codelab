package com.ricky.cglib.interceptor;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AopInterceptor implements MethodInterceptor {  
	
    public Object intercept(Object arg0, Method arg1, Object[] arg2,  
            MethodProxy arg3) throws Throwable {  
        
    	System.out.println("before method execute");
    	
    	Object obj = arg3.invokeSuper(arg0, arg2);
    	
    	System.out.println("after method execute");
    	
        return obj;  
    }  
}