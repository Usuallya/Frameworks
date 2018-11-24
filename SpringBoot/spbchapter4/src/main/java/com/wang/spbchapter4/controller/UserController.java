package com.wang.spbchapter4.controller;

import com.wang.spbchapter4.domain.Users;
import com.wang.spbchapter4.entity.User;
import com.wang.spbchapter4.repository.UserRepository;
import com.wang.spbchapter4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ ClassName  ：UserController
 * @ Author     ：王海奇
 * @ Date       ：Created in 17:23 2018/11/20
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
    @RequestMapping("/addUsers")
    public String addUsers(){
        usersService.addUser(new Users(1,"wang",24));
        return "ok";
    }

    //使用JPA来操作数据库
    @GetMapping("/addUserByJPA")
    @ResponseBody
    public User addUserByJPA(){
        User user = userRepository.findById(1).get();
        return user;
    }
}
