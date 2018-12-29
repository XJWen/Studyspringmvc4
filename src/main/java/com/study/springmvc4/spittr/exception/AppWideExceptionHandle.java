package com.study.springmvc4.spittr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 * 所有控制器的异常都在@ControllerAdvice进行一致的处理
 * **/
@ControllerAdvice
public class AppWideExceptionHandle {

    @ExceptionHandler(DuplicateSpittleException.class)
    public String duplicatrSpittleHandle(){
        return "error/duplicate";
    }
}
