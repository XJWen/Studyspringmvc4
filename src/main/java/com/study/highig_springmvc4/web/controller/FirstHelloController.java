package com.study.highig_springmvc4.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstHelloController {

    @RequestMapping("/index")
    public String Hello(){
        return "index";
    }
}
