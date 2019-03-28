package com.qezhhnjy.login.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author fuzy
 * create time 19-3-26-上午10:24
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @Pointcut("execution(public * com.qezhhnjy.login.advice.*Controller.*(..))")
    public void exception() {
    }

    @Before("exception()")
    public void doBefore(JoinPoint joinPoint) {

        //类方法
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        log.info("args={}", joinPoint.getArgs());
    }

    @After("exception()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "exception()")
    public void doAfterReturning(Object object) {
        log.info("response={}", object.toString());
    }


}
