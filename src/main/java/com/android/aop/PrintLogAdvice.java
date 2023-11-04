package com.android.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class PrintLogAdvice {

    @Pointcut("execution(* com.android.controller.*Controller.*(..))")
    private void pointcut() {
    }


    @AfterReturning(value = "pointcut()", returning = "ret")
    public void printLogMethod(JoinPoint jp, Object ret) {
        System.out.println(new Date() + " >>> " + ret);
    }
}
