package com.bytebeats.codelab.cglib.proxy.impl;

import com.bytebeats.codelab.cglib.proxy.ProxyFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * CgLib动态代理实现
 * @author Ricky
 *
 */
public class CgLibProxyFactory implements ProxyFactory {
    private final Enhancer en = new Enhancer();

    @Override
    public <T> T getProxy(Object target) {

        //进行代理
        en.setSuperclass(target.getClass());
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                String methodName = method.getName();
                //打印日志
                System.out.println("[before] The method " + methodName + " begins with " + (args!=null ? Arrays.asList(args) : "[]"));
                Object result = null;
                try{
                    //前置通知
                    result = methodProxy.invokeSuper(o, args);
                    //返回通知, 可以访问到方法的返回值
                    System.out.println(String.format("after method:%s execute", method.getName()));
                } catch (Exception e){
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                }
                //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
                //打印日志
                System.out.println("[after] The method ends with " + result);
                return result;
            }
        });
        //生成代理实例
        return (T)en.create();
    }
}