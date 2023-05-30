package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {

	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		// 또는
		//Signature sig = jp.getSignature();
		//String method = sig.getName();
		
		if (args.length == 0) {
			System.out.println("[사전처리] 비즈니스 로직 수행전 작업...");
			System.out.println("[사전 처리]" + method+ "() ARGS: 없음");
		} else {
			System.out.println("[사전처리] 비즈니스 로직 수행전 작업...");
			System.out.println("[사전 처리]" + method+ "() ARGS: " + args[0].toString());
		}
	}
}
