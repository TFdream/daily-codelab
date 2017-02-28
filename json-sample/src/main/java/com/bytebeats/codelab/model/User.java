package com.bytebeats.codelab.model;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-02-27 15:23
 */
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Date birthday;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
