package com.wang.spbwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * @ ClassName  ：WebController
 * @ Author     ：王海奇
 * @ Date       ：Created in 19:30 2018/11/21
 */
@Controller
public class WebController {
    @RequestMapping("/ok")
    public String map2Ok(Map<String,Object> map){
        //将会转发给templates/ok.html
        map.put("hello","你好");
        map.put("ones", Arrays.asList(1,2,3,4,5));
        return "ok";
    }
}
