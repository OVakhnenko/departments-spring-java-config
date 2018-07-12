package com.vakhnenko.departments.resolver;

import com.vakhnenko.departments.controller.DepartmentsControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingHandlerExceptionResolver
        implements HandlerExceptionResolver, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentsControllerAdvice.class);

    public int getOrder() {
        return Integer.MIN_VALUE; // we're first in line, yay!
    }

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e
    ) {
        logger.error("8684580554: Error!!", e);
        return null; // trigger other HandlerExceptionResolver's
    }
}