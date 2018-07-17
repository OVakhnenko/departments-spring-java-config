package com.vakhnenko.departments.filters;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Order(Integer.MIN_VALUE)
@Component(value = "formEncodingSetterFilter")
// http://www.skipy.ru/technics/encodings_webapp.html
public class FormEncodingSetterFilter extends BaseFilter {

    private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
            req.setCharacterEncoding(ENCODING_DEFAULT);
        }
        chain.doFilter(req, resp);
    }
}