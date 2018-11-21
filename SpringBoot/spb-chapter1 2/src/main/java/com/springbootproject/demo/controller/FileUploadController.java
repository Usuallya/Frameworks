package com.springbootproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileUploadController {
    @RequestMapping("/fileUploadController")
    public Map<String,Object> fileUpload(MultipartFile fileName){
        System.out.println(fileName.getName());
        Map<String,Object> map = new HashMap<>();
        map.put("1",fileName.getName());
        return map;
    }
}
