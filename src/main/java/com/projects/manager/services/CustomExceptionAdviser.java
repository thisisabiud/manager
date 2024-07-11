package com.projects.manager.services;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionAdviser {
    /**
     * Create response body from exception message so that it can be rendered on web page
     * @param ex    object containing error message
     * @return      error string from provided exception class
     */
    @ExceptionHandler(CustomException.class)
    String objectNotFoundHandler(CustomException ex){
        return ex.getMessage();
    }
}
