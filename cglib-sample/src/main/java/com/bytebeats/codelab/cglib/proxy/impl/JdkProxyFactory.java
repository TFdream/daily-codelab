package com.bytebeats.codelab.cglib.proxy.impl;

import com.bytebeats.codelab.cglib.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK动态代理实现
 * @author Ricky
 *
 */
public class JdkProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(final Object target) {

        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                String methodName = method.getName();
                //打印日志
                System.out.println("[before] The method " + methodName + " begins with " + (args!=null ? Arrays.asList(args) : "[]"));

                //调用目标方法
                Object result = null;
                try {
                    //前置通知
                    result = method.invoke(target, args);
                    //返回通知, 可以访问到方法的返回值
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                }
                //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
                //打印日志
                System.out.println("[after] The method ends with " + result);
                return result;
            }
        });
    }
}