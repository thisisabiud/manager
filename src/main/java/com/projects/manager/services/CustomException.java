package com.projects.manager.services;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class CustomException extends Exception{
    /**
     * Custom exception thrown once given key has no associated entity in database
     * @param message   displayed message when exception is thrown
     */
    public CustomException(String message) {
        super(message);
    }
}
