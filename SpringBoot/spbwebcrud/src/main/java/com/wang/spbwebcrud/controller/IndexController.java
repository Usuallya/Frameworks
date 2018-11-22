package com.wang.spbwebcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ ClassName  ：Controller
 * @ Author     ：王海奇
 * @ Date       ：Created in 10:35 2018/11/22
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "login";
    }


}
