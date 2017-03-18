package com.bytebeats.codelab.javassist.proxy.jdk;

import com.bytebeats.codelab.javassist.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:24
 */
public class JdkProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Object target, InvocationHandler handler) throws Throwable {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), handler);
    }
}
