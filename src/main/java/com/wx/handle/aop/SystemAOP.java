package com.wx.handle.aop;

import com.wx.base.util.SysTimeUitl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Aspect
@Component
public class SystemAOP {

    @Around(value = "execution(* com.wx.handle.*.controller..*Controller.*(..))")
    public Object handleAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        Object[] objs = joinPoint.getArgs();
        for(Object obj : objs){

        }
        String methodName = joinPoint.getSignature().getName();
        long startTime = new Date().getTime();
        Object result = joinPoint.proceed();
        long endTime = new Date().getTime();
        System.out.println("[log]:<ip>127.0.0.1<ip><method>"+methodName+"<method><time>"+(endTime-startTime)+"ms<time>");
        return result;
    }
}
