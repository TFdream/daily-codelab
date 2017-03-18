package com.bytebeats.codelab.javassist.service;

import com.bytebeats.codelab.javassist.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public String[] getHobbies() {
        return new String[]{"NBA", "Shopping"};
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public User getUser() {
        return new User();
    }

    @Override
    public List<User> getUser(String group, int age) {
        List<User> users = new ArrayList<>();

        return users;
    }
}
