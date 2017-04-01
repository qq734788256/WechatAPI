package com.wx.handle.aop;

import com.wx.base.util.SysTimeUitl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class SystemAOP {

    @Around(value = "execution(* com.wx.handle.*.controller..*Controller.*(..))")
    public Object handleAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
//        HttpServletRequest request = sra.getRequest();
//        HttpServletResponse response = sra.getResponse();
//        Object[] obj = joinPoint.getArgs();
//        Object[] newObj =  Arrays.copyOf(obj,obj.length + 1);
//        newObj[newObj.length-1] = "id";
        String methodName = joinPoint.getSignature().getName();
        long startTime = new Date().getTime();
        Object result = joinPoint.proceed();
        long endTime = new Date().getTime();
        System.out.println("[log]:----" + methodName + "----               耗时：" + (endTime - startTime) + "ms");
        return result;
    }
}
