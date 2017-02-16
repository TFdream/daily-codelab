package com.bytebeats.codelab.javassist.proxy;

import javassist.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-16 17:25
 */
public class JavassistProxy {

    public static <T> T newProxyInstance(ClassLoader loader, Class<T> type, JavassistInvocationHandler handler) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {

        String qualifiedName = type.getCanonicalName();

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass(String.format("%s$Impl", qualifiedName));
        CtClass parent = pool.get(qualifiedName);
        cc.setSuperclass(parent);

        CtMethod[] methods = parent.getDeclaredMethods();
        for(CtMethod method : methods){

            CtMethod proxyMethod = CtNewMethod.make(
                    "public int xmove(int dx) { x += dx; }",
                    cc);
            cc.addMethod(proxyMethod);
        }

        Class c = cc.toClass();
        return (T) c.newInstance();
    }
}
