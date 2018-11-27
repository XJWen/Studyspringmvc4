package com.study.highig_springmvc4.web.controller;

import com.study.highig_springmvc4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {
    @RequestMapping(value = "/convert",produces = {"application/x-wisely"})
    public DemoObj convert(DemoObj demoObj){
        return demoObj;
    }
}
