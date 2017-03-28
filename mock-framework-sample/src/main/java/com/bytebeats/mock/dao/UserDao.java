package com.bytebeats.mock.dao;

import com.bytebeats.mock.model.User;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:36
 */
public interface UserDao {

    User getUserById(Long id);

    List<User> getUsers(String group);

    long insert(User user);

    int update(User user);

    int delete(long id);
}
