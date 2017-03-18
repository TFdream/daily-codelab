package com.bytebeats.codelab.cglib.fast;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-18 15:05
 */
public class FastMethodDemo {

    private int count = 100000;

    public static void main(String[] args) throws Exception {

        new FastMethodDemo().testCGLibPerformance();
        //new FastMethodDemo().testJdkReflectPerformance();
    }

    public void testJdkReflectPerformance() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Class<?> clazz = HelloService.class;
        Method method = clazz.getMethod("say", new Class[]{String.class});
        HelloService helloService = new HelloService();

        long start = System.currentTimeMillis();
        for(int i=0; i<count; i++) {
            method.invoke(helloService, new Object[]{"ricky"});
        }
        System.out.println("jdk cost:"+(System.currentTimeMillis() - start)+ "ms");
    }

    public void testCGLibPerformance() throws InvocationTargetException {

        FastClass fastClass = FastClass.create(HelloService.class);
        FastMethod method = fastClass.getMethod("say", new Class[]{String.class});
        HelloService helloService = new HelloService();

        long start = System.currentTimeMillis();
        for(int i=0; i<count; i++) {
            method.invoke(helloService, new Object[]{"ricky"});
        }
        System.out.println("jdk cost:"+(System.currentTimeMillis() - start)+ "ms");
    }

    public void test1() throws InvocationTargetException {
        HelloService helloService = new HelloService();

        FastClass fastClass = FastClass.create(HelloService.class);
        FastMethod method = fastClass.getMethod("say", new Class[]{String.class});
        Object result = method.invoke(helloService, new Object[]{"ricky"});
        System.out.println(result);
    }
}
