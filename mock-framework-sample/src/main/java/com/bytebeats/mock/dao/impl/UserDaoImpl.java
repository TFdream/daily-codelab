package com.bytebeats.mock.dao.impl;

import com.bytebeats.mock.dao.UserDao;
import com.bytebeats.mock.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:40
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getUsers(String group) {
        return null;
    }

    @Override
    public long insert(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
