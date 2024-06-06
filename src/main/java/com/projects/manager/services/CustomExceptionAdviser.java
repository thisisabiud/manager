package com.projects.manager.services;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionAdviser {
    @ExceptionHandler(CustomException.class)
    String objectNotFoundHandler(CustomException ex){
        return ex.getMessage();
    }
}
