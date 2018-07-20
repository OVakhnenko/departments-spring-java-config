package com.vakhnenko.departments.filters;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Order(9)
@Component(value = "guestAuthenticationFilter")
public class GuestAuthenticationFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // @link SecurityServiceImpl.guestLogin
        chain.doFilter(req, resp);
    }
}
