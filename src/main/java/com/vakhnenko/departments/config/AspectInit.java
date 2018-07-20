package com.vakhnenko.departments.config;

import com.vakhnenko.departments.aop.AopLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.vakhnenko.departments")
public class AspectInit {
    @Bean
    public AopLogger aopLogger() {
        return new AopLogger();
    }
}
