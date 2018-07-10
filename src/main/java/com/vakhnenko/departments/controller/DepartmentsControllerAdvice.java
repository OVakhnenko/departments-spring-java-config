package com.vakhnenko.departments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DepartmentsControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException500(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "500";
    }
/*/
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException404(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "404";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException403(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "403";
    }*/
}