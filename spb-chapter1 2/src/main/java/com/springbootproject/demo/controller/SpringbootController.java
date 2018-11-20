package com.springbootproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SpringbootController {

    @RequestMapping("/hello")
    @ResponseBody
    public Map<String,String> hello(){
        Map<String,String> s = new HashMap<>();
        s.put("1","hello world");
        return s;
    }

}
