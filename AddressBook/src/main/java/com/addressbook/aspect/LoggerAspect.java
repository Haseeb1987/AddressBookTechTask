package com.addressbook.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Before("allMethods()")
	public void logAdvice(JoinPoint joinPoint){
		System.out.println("AddressBookController method : " + joinPoint.getSignature() + " is called ...");
	}
	
	@Pointcut ("execution(* com.addressbook.controller.AddressBookController.*(..))")
	public void allMethods(){}
}
