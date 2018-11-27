package com.study.highig_springmvc4.web.controller;

import com.study.highig_springmvc4.service.DemoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NormalController {
    @Autowired
    DemoTestService service;

    @RequestMapping("/normal")
    public String testPage(Model model){
        model.addAttribute("msg", service.saySomething());
        return "page";
    }
}
