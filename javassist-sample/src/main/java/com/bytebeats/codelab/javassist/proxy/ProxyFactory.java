package com.bytebeats.codelab.javassist.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:28
 */
public abstract class ProxyFactory implements InvocationHandler {
    protected Object target;  //被代理类的对象

    public ProxyFactory(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(String.format("before method:%s execute", method.getName()));
        Object result = null;
        try{
            result = method.invoke(target, args);
            System.out.println(String.format("after method:%s execute", method.getName()));
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(String.format("return method:%s execute", method.getName()));
        return result;
    }

    public abstract Object getProxy() throws Throwable;
}
