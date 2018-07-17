package com.vakhnenko.departments.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(Integer.MIN_VALUE + 1)
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
    // == XML org.springframework.web.filter.DelegatingFilterProxy
    // https://habr.com/post/255773/
}
