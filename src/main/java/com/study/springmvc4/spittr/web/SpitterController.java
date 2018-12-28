package com.study.springmvc4.spittr.web;

import com.study.springmvc4.spittr.dao.Spitter;
import com.study.springmvc4.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository repository;

    public SpitterController(){}

    @Autowired
    public SpitterController(SpitterRepository repository){
        this.repository = repository;
    }
    /**
     * 注册页面
     * **/
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

    /**
     * 表单提交到Spitter对象中
     * 注册后重定向
     * 校检功能 @Vali 对参数添加校检限制
     * Errors 要紧跟在带@Vaild注解的参数后面
     * **/
    @RequestMapping(value = "/registered",method = RequestMethod.POST)
    public String processRegistration(
            @Valid Spitter spitter, Errors error){
        if (error.hasErrors()){
            return "registerForm";
        }
        repository.save(spitter);
        return "redircet:/spitter/"+spitter.getUsername();
    }

    /**
     * 重定向后根据路径中的用户名去查找用户基本信息
     * **/
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(
            @PathVariable String username, Model model){

        Spitter spitter = repository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
