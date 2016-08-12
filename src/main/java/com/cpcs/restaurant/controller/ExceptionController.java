package com.cpcs.restaurant.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model, Exception exception) {
        model.addAttribute("exception", exception);
        return "error";
    }

}