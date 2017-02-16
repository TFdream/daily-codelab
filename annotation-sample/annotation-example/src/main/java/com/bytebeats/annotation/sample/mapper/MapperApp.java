package com.bytebeats.annotation.sample.mapper;

import java.sql.SQLException;
import com.bytebeats.annotation.sample.mapper.UserMapper;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 16:15
 */
public class MapperApp {

    public static void main(String[] args) throws SQLException {

        UserMapper userMapper = new UserMapper();
        userMapper.toBean(null);
    }
}
