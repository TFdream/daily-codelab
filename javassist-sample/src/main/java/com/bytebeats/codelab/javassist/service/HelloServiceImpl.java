package com.bytebeats.codelab.javassist.service;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:34
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void say(String msg) {
        System.out.println("hello, "+msg);
    }

    @Override
    public String echo(String msg) {
        System.out.println("echo, "+msg);
        return "echo, "+msg;
    }
}
