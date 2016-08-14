package com.cpcs.restaurant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestClientException.class)
    public @ResponseBody String ajaxRequestExceptionHandler(Exception exception) {
        //TODO log
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    public String showErrorPage(Exception exception, Model model) {
        //TODO log
        exception.printStackTrace();
        model.addAttribute("exception", exception);
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception exception) {
        //TODO log
        exception.printStackTrace();
    }

}