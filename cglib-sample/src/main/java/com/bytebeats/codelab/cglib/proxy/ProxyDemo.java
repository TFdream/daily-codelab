package com.bytebeats.codelab.cglib.proxy;

import com.bytebeats.codelab.cglib.proxy.impl.CgLibProxyFactory;
import com.bytebeats.codelab.cglib.proxy.impl.JdkProxyFactory;
import com.bytebeats.codelab.cglib.service.HelloService;
import com.bytebeats.codelab.cglib.service.HelloServiceImpl;

public class ProxyDemo {

	public static void main(String[] args) {

		//需要被代理的类
		HelloService helloService = new HelloServiceImpl();

		//jdk代理
		ProxyFactory jdkProxyFactory = new JdkProxyFactory();
		HelloService jdkProxy = jdkProxyFactory.getProxy(helloService);
		jdkProxy.echo("ricky");
		jdkProxy.hashCode();

		//CgLib代理
		ProxyFactory cgLibProxyFactory = new CgLibProxyFactory();
		HelloService cgLibProxy = cgLibProxyFactory.getProxy(helloService);
		cgLibProxy.echo("ricky");
		jdkProxy.hashCode();
	}

}
