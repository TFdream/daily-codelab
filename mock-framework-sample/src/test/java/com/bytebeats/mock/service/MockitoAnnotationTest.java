package com.bytebeats.mock.service;

import com.bytebeats.mock.dao.UserDao;
import com.bytebeats.mock.model.User;
import com.bytebeats.mock.service.impl.DemoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:27
 */
public class MockitoAnnotationTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private DemoServiceImpl demoService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDemo2(){

        String group = "A";
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userDao.getUsers(group)).thenReturn(users);
        //when(userDao.getUsers(any(String.class))).thenReturn(users);
        //when(userDao.getUsers(anyString())).thenReturn(users);

        List<User> result = demoService.getUsers(group);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testDemo(){

        User user = new User();
        when(userDao.insert(user)).thenReturn(1L);

        long result = demoService.register(user);

        //验证方法调用
        verify(userDao).insert(user);

        Assert.assertEquals(1, result);
    }
}
