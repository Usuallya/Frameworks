package com.wang.spbchapter4.service;

import com.wang.spbchapter4.domain.Users;
import com.wang.spbchapter4.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ ClassName  ：UsersService
 * @ Author     ：王海奇
 * @ Date       ：Created in 17:20 2018/11/20
 */
@Service
@Transactional  //该注解表示这个类下所有方法都有事务控制
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;
    public void addUser(Users users){
        usersMapper.insertUser(users);
    }
}
