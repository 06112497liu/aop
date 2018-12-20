package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author liuweibo
 * @date 2018/12/20
 */
@Aspect
@Component
@Slf4j
public class TimeUsedLog {

    @Pointcut("@annotation(com.example.aop.annotation.TimeUsed)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("执行方法【{}】，耗时【{}ms】", signature.getDeclaringTypeName() + "#" + signature.getName(), end - start);
        return obj;
    }
}
