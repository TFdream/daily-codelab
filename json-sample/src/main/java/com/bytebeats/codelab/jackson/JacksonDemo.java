package com.bytebeats.codelab.jackson;

import com.bytebeats.codelab.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-27 15:31
 */
public class JacksonDemo {

    public static void main(String[] args) throws IOException {

        new JacksonDemo().readJson();
    }

    public void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        mapper.setDateFormat(outputFormat);

        User user = new User();
        user.setUsername("ricky");
        user.setBirthday(new Date());

        mapper.writeValue(System.out, user);
    }

    public void readJson() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String json =
                "{ \"username\" : \"ricky\", \"password\" : \"1234\" }";

        User user = mapper.readValue(json, User.class);
        System.out.println("user.username = " + user.getUsername());
        System.out.println("user.password = " + user.getPassword());

    }
}
