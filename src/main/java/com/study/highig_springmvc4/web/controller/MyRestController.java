package com.study.highig_springmvc4.web.controller;

import com.study.highig_springmvc4.service.DemoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    @Autowired
    DemoTestService service;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testRest(){
        return service.saySomething();
    }
}
