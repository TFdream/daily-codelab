package com.bytebeats.codelab.fastjson;

import com.alibaba.fastjson.JSON;
import com.bytebeats.codelab.model.User;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-27 15:23
 */
public class FastJsonDemo {

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("ricky");

        String jsonString = JSON.toJSONString(user);

        System.out.println(jsonString);
    }
}
