package com.ricky.codelab.cglib.filter;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.CallbackFilter;

public class MyCallbackFilter implements CallbackFilter{
	
    public int accept(Method arg0) {
        if(!"query".equalsIgnoreCase(arg0.getName()))  
            return 0;
        return 1;
    }
}
