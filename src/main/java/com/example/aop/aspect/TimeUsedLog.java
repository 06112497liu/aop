package com.example.aop.aspect;

import com.example.aop.annotation.TimeUsed;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Time;

/**
 * @author liuweibo
 * @date 2018/12/20
 */
@Aspect
@Component
@Slf4j
public class TimeUsedLog {

    /**
     * "@annotation(注解)"：表示会拦截所有标注有指定注解的方法。在这里表示会拦截标注有TimeUsed注解的方法。
     */
    @Pointcut("@annotation(com.example.aop.annotation.TimeUsed)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取拦截方法上的注解
        TimeUsed annotation = AnnotationUtils.findAnnotation(method, TimeUsed.class);
        long value = annotation.value();
        if (value < (end - start)) {
            log.info("执行方法【{}】，耗时【{}ms】", signature.getDeclaringTypeName() + "#" + signature.getName(), end - start);
        }
        return obj;
    }

    /**
     * 表示会拦截所有标注有TimeUsed注解的方法，并且标注的注解也会绑定到timeUsed参数中，并传入通知方法：arround2()对应的参数。
     * agrs(param)：表示传入被通知方法的参数也会绑定到切点中的param。然后传入通知方法：arround2()对应的参数。
     * @param timeUsed
     */
    @Pointcut("@annotation(timeUsed) && args(param)")
    public void pointcut2(TimeUsed timeUsed, Object param) {
    }

    @Around("pointcut2(timeUsed, param)")
    public Object arround2(ProceedingJoinPoint joinPoint, TimeUsed timeUsed, Object param) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (timeUsed.value() < (end - start)) {
            log.info("执行方法【{}】，耗时【{}ms】", signature.getDeclaringTypeName() + "#" + signature.getName(), end - start);
        }
        return obj;
    }
}
