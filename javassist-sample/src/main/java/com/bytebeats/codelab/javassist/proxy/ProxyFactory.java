package com.bytebeats.codelab.javassist.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:28
 */
public interface ProxyFactory {

    <T> T getProxy(Object target, InvocationHandler handler) throws Throwable;
}
