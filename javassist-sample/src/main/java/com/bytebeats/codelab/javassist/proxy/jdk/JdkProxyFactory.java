package com.bytebeats.codelab.javassist.proxy.jdk;

import com.bytebeats.codelab.javassist.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:24
 */
public class JdkProxyFactory extends ProxyFactory {

    public JdkProxyFactory(Object target) {
        super(target);
    }

    public Object getProxy() throws Exception {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
