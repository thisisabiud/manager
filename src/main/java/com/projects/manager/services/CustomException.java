package com.projects.manager.services;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class CustomException extends Exception{
    public CustomException(String message) {
        super(message);
    }
}
