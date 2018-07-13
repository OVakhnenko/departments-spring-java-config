package com.vakhnenko.departments.filters;

import com.vakhnenko.departments.entity.Variable;
import com.vakhnenko.departments.security.UserAuthetication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "GuestAuthenticationFilter")
public class GuestAuthenticationFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (Variable.guestLogged) {
            UserAuthetication guest = new UserAuthetication("guest");
            SecurityContextHolder.getContext().setAuthentication(guest);
        }
        chain.doFilter(req, resp);
    }
}
