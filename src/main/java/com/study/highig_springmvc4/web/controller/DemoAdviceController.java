package com.study.highig_springmvc4.web.controller;

import com.study.highig_springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoAdviceController {
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) throws IllegalAccessException {
        throw new IllegalAccessException("非常抱歉，参数有误/"+"来自@ModelAttribute:"+msg);
    }
}
