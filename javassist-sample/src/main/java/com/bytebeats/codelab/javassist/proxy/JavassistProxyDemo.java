package com.bytebeats.codelab.javassist.proxy;

import com.bytebeats.codelab.javassist.proxy.javassist.JavassistProxyFactory;
import com.bytebeats.codelab.javassist.proxy.jdk.JdkProxyFactory;
import com.bytebeats.codelab.javassist.service.HelloService;
import com.bytebeats.codelab.javassist.service.HelloServiceImpl;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-16 17:23
 */
public class JavassistProxyDemo {

    public static void main(String[] args) {

        try {
//            new JavassistProxyDemo().testProxy("Jdk");
            new JavassistProxyDemo().testProxy("javassist");
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void testProxy(String type) throws Throwable {

        HelloService helloService = new HelloServiceImpl();
        ProxyFactory factory = null;
        if("javassist".equals(type)){
            factory = new JavassistProxyFactory(helloService);
        } else {
            factory = new JdkProxyFactory(helloService);
        }

        HelloService proxy = (HelloService) factory.getProxy();

        proxy.say("ricky");

        proxy.echo("world");
    }
}
