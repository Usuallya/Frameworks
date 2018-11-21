package com.wang.spbchapter3.domain;

/**
 * @ ClassName  ：Users
 * @ Author     ：王海奇
 * @ Date       ：Created in 16:35 2018/11/20
 */
public class Users {
    private Integer userAge;
    private String userName;
    private Integer userId;

    public Users(Integer userAge, String userName, Integer userId) {
        this.userAge = userAge;
        this.userName = userName;
        this.userId = userId;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
