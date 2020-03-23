package cn.xaiu.springbootgraduation.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop {



    @Before("@annotation(cn.xaiu.springbootgraduation.annotation.AddAop)")
    public void logBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"aop");
    }
    @After("execution(* cn.xaiu.springbootgraduation.controller.AlipayController.*(..))")
    public void logAfter(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"aop");
        
    }
}
