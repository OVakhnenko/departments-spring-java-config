package com.vakhnenko.departments.config;

import com.vakhnenko.departments.filters.FormEncodingSetterFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInit implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "departments";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // <!-- Filters -->
        // <!-- Encoding! MUST BE FIRST!! -->
        // <!-- http://www.skipy.ru/technics/encodings_webapp.html -->//
        FormEncodingSetterFilter formEncodingSetterFilter = new FormEncodingSetterFilter();
        FilterRegistration.Dynamic filter = servletContext.
                addFilter("FormEncodingSetterFilter", formEncodingSetterFilter);
        filter.setInitParameter("encoding", "UTF-8");
        filter.addMappingForUrlPatterns(null, true, "/*");

        // Spring Security filter
        servletContext.addFilter("springSecurityFilterChain",
                new DelegatingFilterProxy("springSecurityFilterChain")).
                addMappingForUrlPatterns(null, false, "/*");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);
        //servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}