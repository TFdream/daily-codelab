package com.bytebeats.codelab.gson;

import com.bytebeats.codelab.model.User;
import com.google.gson.Gson;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-27 15:29
 */
public class GsonDemo {

    public static void main(String[] args) {

        Gson gson = new Gson();
        System.out.println(gson.toJson(1));

        User user = new User();
        user.setUsername("ricky");

        System.out.println(gson.toJson(user));
    }
}
