package com.bytebeats.codelab.cglib.service;

/**
 * 需要被代理的类
 * @author Ricky Fung
 *
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public void hello(String msg) {
		System.out.println("hello "+msg);
	}

	@Override
	public String echo(String msg) {
		return "echo ["+msg+"]";
	}
}
