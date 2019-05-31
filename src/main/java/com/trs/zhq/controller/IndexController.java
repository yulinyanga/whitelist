package com.trs.zhq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/")
public class IndexController {


    @RequestMapping("index")
    public String ImportFile(){
        System.out.println(111);
        return "index";
    }

}
