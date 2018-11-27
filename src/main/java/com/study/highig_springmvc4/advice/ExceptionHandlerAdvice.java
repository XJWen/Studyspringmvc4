package com.study.highig_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice//控制器建言，控制器全局布置
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception ex, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage",ex.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息。。");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("id");
    }
}
