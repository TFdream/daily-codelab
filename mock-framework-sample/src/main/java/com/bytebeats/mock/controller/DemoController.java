package com.bytebeats.mock.controller;

import com.bytebeats.mock.model.User;
import com.bytebeats.mock.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-03-28 22:35
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(path = "/demo/register", method = RequestMethod.POST)
    public User register(String name, String password){

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        long id = demoService.register(user);
        if(id>0){
            user.setId(id);
            return user;
        }
        return user;
    }

}
