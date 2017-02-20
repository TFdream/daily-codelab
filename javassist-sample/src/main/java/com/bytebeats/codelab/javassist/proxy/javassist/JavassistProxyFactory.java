package com.bytebeats.codelab.javassist.proxy.javassist;

import com.bytebeats.codelab.javassist.proxy.ProxyFactory;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 14:55
 */
public class JavassistProxyFactory extends ProxyFactory {

    public JavassistProxyFactory(Object target) {
        super(target);
    }

    @Override
    public Object getProxy() throws Throwable {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass(), this);
    }
}
