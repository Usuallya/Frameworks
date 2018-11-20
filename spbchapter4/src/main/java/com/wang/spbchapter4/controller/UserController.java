package com.wang.spbchapter4.controller;

import com.wang.spbchapter4.domain.Users;
import com.wang.spbchapter4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
    @RequestMapping("/addUsers")
    public String addUsers(){
        usersService.addUser(new Users(1,"wang",24));
        return "ok";
    }
}
