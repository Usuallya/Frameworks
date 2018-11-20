package com.wang.spbchapter3.controller;

import com.wang.spbchapter3.domain.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ ClassName  ：FreeMarkerController
 * @ Author     ：王海奇
 * @ Date       ：Created in 16:32 2018/11/20
 * @ Description ：视图整合——JSP
 */
@Controller
public class JspController {
/**
 * 处理请求、产生数据
 */
    @RequestMapping("/showJsp")
    public String showJsp(Model model){
        List<Users> list = new ArrayList<>();
        list.add(new Users(10,"wanghaiqi",1));
        list.add(new Users(11,"li",2));
        list.add(new Users(12,"zhang",3));
        model.addAttribute("list",list);
        return "userList.jsp";
    }
}
