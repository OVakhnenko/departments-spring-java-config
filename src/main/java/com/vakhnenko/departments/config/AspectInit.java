package com.vakhnenko.departments.config;

import com.vakhnenko.departments.aop.AopLogger;
import com.vakhnenko.departments.condition.AopDebugCondition;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.vakhnenko.departments")
public class AspectInit {
    @Bean
    @Conditional(AopDebugCondition.class)
    public AopLogger aopLogger() {
        return new AopLogger();
    }
}
