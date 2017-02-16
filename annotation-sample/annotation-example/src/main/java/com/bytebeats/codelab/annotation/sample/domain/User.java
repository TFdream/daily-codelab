package com.bytebeats.codelab.annotation.sample.domain;

import com.bytebeats.codelab.orm.annotation.Column;
import com.bytebeats.codelab.orm.annotation.Mapper;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 17:50
 */
@Mapper(pkg = "com.bytebeats.codelab.annotation.sample.mapper")
public class User {
    @Column
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private int age;

    @Column("create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
