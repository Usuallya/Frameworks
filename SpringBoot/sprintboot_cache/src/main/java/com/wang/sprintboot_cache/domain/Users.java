package com.wang.sprintboot_cache.domain;

/**
 * @ ClassName  ：Users
 * @ Author     ：王海奇
 * @ Date       ：Created in 15:18 2018/11/24
 */
public class Users {
    private String name;
    private String age;
    private Integer id;

    public Users() {
    }

    public Users(String name, String age, Integer id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
