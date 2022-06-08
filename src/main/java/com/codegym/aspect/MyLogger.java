package com.codegym.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger {

    @AfterThrowing(pointcut = "within(com.codegym.controller.*)", throwing= "result")
    public void logging(JoinPoint joinPoint, Exception result){
        System.out.println("Start log");
        String name = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        System.out.println(name +"."+ method);
        if (result == null)
            System.out.println("null");
        else
            System.out.println(result.hashCode());
    }

}
