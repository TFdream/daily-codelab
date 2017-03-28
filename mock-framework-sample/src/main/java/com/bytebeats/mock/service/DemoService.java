package com.bytebeats.mock.service;

import com.bytebeats.mock.model.User;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:32
 */
public interface DemoService {

    String hello(String msg);

    long register(User user);

    User getUserById(Long id);

    List<User> getUsers(String group);
}
