package com.wang.spbwebcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ ClassName  ：LoginController
 * @ Author     ：王海奇
 * @ Date       ：Created in 15:20 2018/11/22
 */
@Controller
public class LoginController {
    @PostMapping("/user/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        Map<String,Object> map,
                        HttpSession session){
        if(!StringUtils.isEmpty(username) && password.equals("123456")){
            session.setAttribute("loginUser",true);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }
}
