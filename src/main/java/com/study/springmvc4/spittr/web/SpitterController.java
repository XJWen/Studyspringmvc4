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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
     *  RedirectAttributes 继承Model 可以设置flash属性
     *  flash属性会一直携带数据到下一次请求，然后再销毁
     *  flash保存在会话中，可跨请求传输对象
     * **/
    @RequestMapping(value = "/register",method = POST)
    public String processRegistration(
          Spitter spitter, RedirectAttributes model){

        repository.save(spitter);
        model.addAttribute("username",spitter.getUsername());
        model.addFlashAttribute("spitter",spitter);
        return "redirect:/spitter/{username}";
    }

    /**
     * 将username作为占位符填充到URL模板中
     * model中的属性没有在URL中的，会自动以查询参数的形式附加到重定向URL中
     * **/
    @RequestMapping(value = "/register",method = POST)
    public String processRegistration(
            Spitter spitter, Model model){

        repository.save(spitter);
        model.addAttribute("username",spitter.getUsername());
        model.addAttribute("spitterid",spitter.getId());
        return "redirect:/spitter/{username}";
    }

    /**
     * 表单提交到Spitter对象中
     * 注册后重定向
     * 校检功能 @Vali 对参数添加校检限制
     * Errors 要紧跟在带@Vaild注解的参数后面
     * **/
    @RequestMapping(value = "/registered",method = POST)
    public String processRegistration(
            @Valid Spitter spitter, Errors error){
        if (error.hasErrors()){
            return "registerForm";
        }
        repository.save(spitter);
        return "redircet:/spitter/"+spitter.getUsername();
    }

    /**
     * 用户注册提交表单，如果没有上传照片，profilePicture为空数组而非null
     * 非Servlet3.0容器
     * **/
    @RequestMapping(value = "/registered",method = POST)
    public String processRegistration(
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid Spitter spitter, Errors error)throws IOException {
        if (error.hasErrors()){
            return "registerForm";
        }
        profilePicture.transferTo(
                new File("/data/spittr/"+profilePicture.getOriginalFilename())
        );
        repository.save(spitter);
        return "redircet:/spitter/"+spitter.getUsername();
    }

    /**
     * 用户注册提交表单，如果没有上传照片，profilePicture为空数组而非null
     * Servlet3.0容器
     * **/
    @RequestMapping(value = "/registered",method = POST)
    public String processRegistration(
            @RequestPart("profilePicture") Part profilePicture,
            @Valid Spitter spitter, Errors error)throws IOException {
        if (error.hasErrors()){
            return "registerForm";
        }
        profilePicture.write(
                "/data/spittr/"+profilePicture.getSubmittedFileName()
        );
        repository.save(spitter);
        return "redircet:/spitter/"+spitter.getUsername();
    }

    /**
     * 重定向后根据路径中的用户名去查找用户基本信息
     * model.containsAttribute 从model中去查找相关属性
     * **/
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(
            @PathVariable String username, Model model){

        if (!model.containsAttribute("spitter")){
            Spitter spitter = repository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }
}
