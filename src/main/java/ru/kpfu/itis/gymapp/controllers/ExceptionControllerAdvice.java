package ru.kpfu.itis.gymapp.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.warn(e.getMessage(), e);
        return "error";
    }
}
