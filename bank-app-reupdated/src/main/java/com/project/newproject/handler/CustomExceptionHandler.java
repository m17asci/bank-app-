package com.project.newproject.handler;


import com.project.newproject.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = UserNotFound.class)
    public UserNotFound exception(UserNotFound exception) {
        return new UserNotFound(HttpStatus.NOT_FOUND,exception.getMessage());
    }
}
