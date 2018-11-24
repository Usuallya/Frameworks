package com.wang.sprintboot_cache.Controller;

import com.wang.sprintboot_cache.domain.Users;
import com.wang.sprintboot_cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ ClassName  ：UserController
 * @ Author     ：王海奇
 * @ Date       ：Created in 15:47 2018/11/24
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/getUser")
    public Users getUsers(){
        return userService.getUsers(new Integer(1));
    }
    @RequestMapping("/updateUsers")
    public Users updateUsers(){
        Users user = userService.updateUsers(new Users("lili","30",1));
        return user;
    }
    @RequestMapping("/deleteUsers")
    public String deleteUsers(){
        userService.deleteUsers(1);
        return "清除缓存";
    }
}
