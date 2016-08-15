package com.cpcs.restaurant.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public String beanValidationExceptionHandler(ConstraintViolationException exception) {
        LOGGER.warn(AJAX_EXCEPTION_MSG, exception);
        return exception.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .collect(Collectors.joining(". "));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Exception exception, Model model) {
        return showErrorPage(exception, model);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String badRequest(Exception exception, Model model) {
        return showErrorPage(exception, model);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String forbidden(Exception exception, Model model) {
        return showErrorPage(exception, model);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception exception) {
        LOGGER.warn(UNEXPECTED_EXCEPTION_MSG, exception);
    }

    private String showErrorPage(Exception exception, Model model) {
        LOGGER.warn(ERROR_PAGE_MSG, exception);
        model.addAttribute("exception", exception);
        return "error";
    }

}