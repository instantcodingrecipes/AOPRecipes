package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// Add import for cross cutting logic like logging.
// Do not create them as instance variables. It must live and die inside methods.

@Aspect
public class AspectClass {

	@Pointcut("execution(* *.*(..))")
	public void excutionForAllClassAndMethods() {

	}

	@Pointcut("execution(* AspectClass.printMethodInfo(..))")
	public void ignorePrivateMethodInThisClass() {

	}

	@Pointcut("excutionForAllClassAndMethods() && !ignorePrivateMethodInThisClass()")
	public void excutionForAllClassAndMethodsExceptThisClass() {

	}

	@Around("excutionForAllClassAndMethodsExceptThisClass()") // Add the point cut expression
	public Object AroundAdvice(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		// write before logic
		System.out.println("Inside Around Advice");
		printMethodInfo(point);
		try {
			result = point.proceed();
		} catch (Throwable t) {
			System.out.println("Exception Thrown:" + t.getMessage());
			// Write what you want to do if exception is thrown.
			throw t; // maintain passivity with application.
		}

		// write after logic
		return result;
	}

	@Before("excutionForAllClassAndMethodsExceptThisClass()")
	public void BeforeAdvice(JoinPoint join) {
		// write before logic
		System.out.println("Inside Before Advice");
		printMethodInfo(join);
	}

	@After("excutionForAllClassAndMethodsExceptThisClass()")
	public void AfterAdvice(JoinPoint join) {
		// write after logic
		System.out.println("Inside After Advice");
		printMethodInfo(join);
	}

	@AfterReturning(pointcut = "excutionForAllClassAndMethodsExceptThisClass()", returning = "ret")
	public void AfterReturningAdvice(JoinPoint join, Object ret) {
		// write after returuning logic. ret will have return value.
		System.out.println("Inside AfterReturning Advice");
		printMethodInfo(join);

		System.out.println("Returning Value: " + ret);
	}

	@AfterThrowing(pointcut = "excutionForAllClassAndMethodsExceptThisClass()", throwing = "ex")
	public void AfterThrowingAdvice(JoinPoint join, Throwable ex) {
		// write after AfterThrowingAdvice logic. ret will have reference to throwable.
		System.out.println("Inside AfterThrowing Advice");
		printMethodInfo(join);
		System.out.println("Exception: " + ex.getMessage());
	}

	private static void printMethodInfo(JoinPoint join) {
		if (join == null) {
			return;
		}
		System.out.println("Number of Args: " + join.getArgs() != null ? join.getArgs().length : 0);
		System.out.println("Signature: " + join.toLongString());
		if (join.getArgs() == null) {
			return;
		}
		for (Object obj : join.getArgs()) {
			System.out.println("Arg Value: " + obj);
		}

	}
}