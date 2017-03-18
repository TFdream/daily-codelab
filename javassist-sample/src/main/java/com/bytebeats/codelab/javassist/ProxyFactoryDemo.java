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
 * @create 2017-03-18 19:07
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) throws Exception {

        HelloService proxy = new ProxyFactoryDemo().testProxy(HelloServiceImpl.class);
        proxy.say("ricky");
    }

    public <T> T testProxy(Class<T> type) throws IllegalAccessException, InstantiationException {
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
                System.out.println("Name: " + m.getName());
                return proceed.invoke(self, args);  // execute the original method.
            }
        };
        T proxy = (T) c.newInstance();
        ((Proxy)proxy).setHandler(mi);
        return proxy;
    }
}
