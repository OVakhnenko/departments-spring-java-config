package com.vakhnenko.departments.aop;

import com.vakhnenko.departments.utils.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class AopLogger {
    final static Logger logger = LoggerFactory.getLogger(AopLogger.class);

    @Around("execution(* com.vakhnenko.departments.service.*.*(..))")
    public Object joinpointDepartmentService(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        try {
            return pjp.proceed();
        } finally {
            long done = System.nanoTime();
            long nano = done - start;
            logger.info("987987987: method " + method + " " + (nano) + " ns (" + Strings.nanoToSeconds(nano) + ") s");
        }
    }
}
