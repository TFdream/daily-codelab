package com.bytebeats.codelab.javassist.proxy.javassist;

import com.bytebeats.codelab.javassist.proxy.ProxyFactory;
import java.lang.reflect.InvocationHandler;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 14:55
 */
public class JavassistProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Object target, InvocationHandler handler) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass(), handler);
    }
}
