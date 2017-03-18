package com.bytebeats.codelab.javassist.service;

import com.bytebeats.codelab.javassist.model.User;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-20 15:16
 */
public interface HelloService {

    void say(String msg);

    String echo(String msg);

    String[] getHobbies();

    int insert(User user);

    User getUser();

    List<User> getUser(String group, int age);
}
