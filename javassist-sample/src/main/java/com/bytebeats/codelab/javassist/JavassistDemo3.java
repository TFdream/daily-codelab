package com.bytebeats.codelab.javassist;

import com.bytebeats.codelab.javassist.service.HelloService;
import com.bytebeats.codelab.javassist.service.HelloServiceImpl;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 17:30
 */
public class JavassistDemo3 {

    public static void main(String[] args) {

        try {
            new JavassistDemo3().testProxy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testProxy() throws Exception {
        HelloService helloService = (HelloService) getProxy(HelloServiceImpl.class);

        helloService.say("ricky");
        helloService.echo("ricky");
    }

    public Object getProxy(Class<?> type) throws IllegalAccessException, InstantiationException {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(type);
        f.setFilter(new MethodFilter() {
            public boolean isHandled(Method m) {
                // ignore finalize()
                return !m.getName().equals("finalize");
            }
        });

        Class c = f.createClass();
        MethodHandler mi = new MethodHandler() {
            public Object invoke(Object self, Method m, Method proceed,
                                 Object[] args) throws Throwable {
                System.out.println("method name: " + m.getName()+" exec");
                return proceed.invoke(self, args);  // execute the original method.
            }
        };
        Object proxy = c.newInstance();
        ((Proxy)proxy).setHandler(mi);
        return proxy;
    }
}
