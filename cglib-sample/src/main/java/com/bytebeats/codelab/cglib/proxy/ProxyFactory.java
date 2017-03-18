package com.bytebeats.codelab.cglib.proxy;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-18 16:41
 */
public interface ProxyFactory {

    <T> T getProxy(Object target);
}
