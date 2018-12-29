package com.study.springmvc4.spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED,reason = "Spittle is exist")
public class DuplicateSpittleException extends RuntimeException{
}
