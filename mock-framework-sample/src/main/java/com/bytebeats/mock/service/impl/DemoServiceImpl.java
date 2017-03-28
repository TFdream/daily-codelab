package com.bytebeats.mock.service.impl;

import com.bytebeats.mock.dao.UserDao;
import com.bytebeats.mock.model.User;
import com.bytebeats.mock.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:34
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserDao userDao;

    @Override
    public String hello(String msg) {
        return "hello "+msg;
    }

    @Override
    public long register(User user) {
        return userDao.insert(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUsers(String group) {
        return userDao.getUsers(group);
    }
}
