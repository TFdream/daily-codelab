package com.bytebeats.codelab.javassist.proxy.javassist;

import javassist.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 14:55
 */
public class Proxy {

    private static final String PROXY_PREFIX = "$Proxy";

    private static final AtomicLong SUFFIX_GENERATOR = new AtomicLong(1);

    public static Object newProxyInstance(ClassLoader classLoader, Class<?> targetClass, InvocationHandler invocationHandler)
            throws Exception {

        ClassPool pool = ClassPool.getDefault();
        //生成代理类的全限定名
        String qualifiedName = targetClass.getName()+PROXY_PREFIX;
        // 创建代理类
        CtClass proxy = pool.makeClass(qualifiedName);

        //接口方法列表
        CtField mf = CtField.make("public static java.lang.reflect.Method[] methods;", proxy);
        proxy.addField(mf);

        CtField hf = CtField.make("private " + InvocationHandler.class.getName() + " handler;", proxy);
        proxy.addField(hf);

        CtConstructor constructor = new CtConstructor(new CtClass[]{pool.get(InvocationHandler.class.getName())}, proxy);
        constructor.setBody("this.handler=$1;");
        constructor.setModifiers(Modifier.PUBLIC);
        proxy.addConstructor(constructor);

        proxy.addConstructor(CtNewConstructor.defaultConstructor(proxy));

        // 获取被代理类的所有接口
        CtClass[] interfaces = pool.get(targetClass.getName()).getInterfaces();

        int cnt = 0;
        for (CtClass parent : interfaces) {
            proxy.addInterface(parent);
            CtMethod[] methods = parent.getDeclaredMethods();
            for (int i= 0; i< methods.length; i++) {
                CtMethod method = methods[i];

                CtClass returnType = method.getReturnType();
                CtMethod ctMethod = new CtMethod(returnType, method.getName(), method.getParameterTypes(), proxy);

                if(returnType==CtClass.voidType){
                    ctMethod.setBody(String.format(" handler.invoke(this, methods[%d], %s);", cnt, "$args"));
                } else {
                    ctMethod.setBody(String.format(" return (%s) handler.invoke(this, methods[%d], %s);", asArgument(returnType, ""), cnt, "$args"));
                }

                proxy.addMethod(ctMethod);
                cnt++;
            }
        }

        proxy.setModifiers(Modifier.PUBLIC);

        Class<?> proxyClass = proxy.toClass(classLoader, null);
        return proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
    }

    private static String asArgument(CtClass cl, String name) {

        if( cl.booleanType == cl )
            return name + "==null?false:((Boolean)" + name + ").booleanValue()";
        if( cl.byteType == cl )
            return name + "==null?(byte)0:((Byte)" + name + ").byteValue()";
        if( cl.charType == cl )
            return name + "==null?(char)0:((Character)" + name + ").charValue()";
        if( cl.doubleType == cl )
            return name + "==null?(double)0:((Double)" + name + ").doubleValue()";
        if( cl.floatType == cl )
            return name + "==null?(float)0:((Float)" + name + ").floatValue()";
        if( cl.intType == cl )
            return name + "==null?(int)0:((Integer)" + name + ").intValue()";
        if( cl.longType == cl )
            return name + "==null?(long)0:((Long)" + name + ").longValue()";
        if( cl.shortType == cl )
            return name + "==null?(short)0:((Short)" + name + ").shortValue()";

        return cl.getName();
    }

    public static String getName(Class<?> c)
    {
        if( c.isArray() )
        {
            StringBuilder sb = new StringBuilder();
            do
            {
                sb.append("[]");
                c = c.getComponentType();
            }
            while( c.isArray() );

            return c.getName() + sb.toString();
        }
        return c.getName();
    }
}
