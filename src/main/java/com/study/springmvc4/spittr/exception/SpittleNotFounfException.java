package com.study.springmvc4.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义的NotFound异常
 * 将异常映射成404HTTP状态码
 * **/
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Spittle Not Found")
public class SpittleNotFounfException extends RuntimeException {
}
