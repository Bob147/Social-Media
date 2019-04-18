package com.example.aspect;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component("myAspect")
@Aspect
public class AspectExample {
	final static Logger logger = Logger.getLogger(AspectExample.class);
	
	static {logger.setLevel(Level.OFF);}
//	@Around("execution(UserProfile com.example.controller.UCController.login(..))")
//	public void testAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
//		logger.info("user loging in:");
//		pjp.proceed();
//		
//		logger.info("user login successful, user info: " );
//	}
	
}
