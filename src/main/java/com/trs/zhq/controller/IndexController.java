package com.trs.zhq.controller;

import com.trs.zhq.service.ConfigService;
import com.trs.zhq.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping({ "/" })
public class IndexController {
    @Autowired
    private ConfigService configService;

    @RequestMapping("index")
    public String ImportFile(){
        System.out.println(111);
        return "index";
    }

    @RequestMapping(value = { "importWhiteList" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
    public ResultDataUtil ImportWhiteList(MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        return configService.inportData(file);
    }

}
