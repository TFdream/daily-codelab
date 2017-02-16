package com.bytebeats.codelab.javassist.proxy;

import javassist.CtMethod;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-16 17:34
 */
public interface JavassistInvocationHandler {

    Object invoke(Object proxy, CtMethod method, Object[] args)
            throws Throwable;
}
