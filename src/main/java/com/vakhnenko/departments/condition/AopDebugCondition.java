package com.vakhnenko.departments.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class AopDebugCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String aopLoggeMode = context.getEnvironment().getProperty("aop.logger.mode");
        return aopLoggeMode.equalsIgnoreCase("on");
    }
}
