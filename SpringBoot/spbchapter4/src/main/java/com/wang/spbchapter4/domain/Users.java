package com.wang.spbchapter4.domain;

/**
 * @ ClassName  ：Users
 * @ Author     ：王海奇
 * @ Date       ：Created in 17:14 2018/11/20
 */
public class Users {
    private Integer id;
    private String username;
    private Integer age;

    public Users(Integer id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
