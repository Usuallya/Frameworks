package com.wang.spbchapter4.entity;


import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;

/**
 * @ ClassName  ：User
 * @ Author     ：王海奇
 * @ Date       ：Created in 11:44 2018/11/24
 */
@Entity//告诉JPA这是一个实体类，要和数据表映射
@Table(name="tbl_user")//如果省略，默认是对应user表
public class User {
    @Id//设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
private Integer id;
    @Column(name="last_name",length = 50)//表明这是和表列明对应
private String lastName;
    @Column
private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
