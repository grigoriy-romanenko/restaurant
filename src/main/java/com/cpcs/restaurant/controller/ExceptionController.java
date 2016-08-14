package com.cpcs.restaurant.controller;

import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);

    private static final String AJAX_EXCEPTION_MSG = "Exception on AJAX request";
    private static final String ERROR_PAGE_MSG = "Exception occurred. Error page will be rendered";
    private static final String UNEXPECTED_EXCEPTION_MSG = "Unexpected exception was suppressed";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    public String ajaxRequestExceptionHandler(Exception exception) {
        LOGGER.warn(AJAX_EXCEPTION_MSG, exception);
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    public String showErrorPage(Exception exception, Model model) {
        LOGGER.warn(ERROR_PAGE_MSG, exception);
        model.addAttribute("exception", exception);
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception exception) {
        LOGGER.warn(UNEXPECTED_EXCEPTION_MSG, exception);
    }

}