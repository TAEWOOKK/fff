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
		// �Ǵ�
		//Signature sig = jp.getSignature();
		//String method = sig.getName();
		
		if (args.length == 0) {
			System.out.println("[����ó��] ����Ͻ� ���� ������ �۾�...");
			System.out.println("[���� ó��]" + method+ "() ARGS: ����");
		} else {
			System.out.println("[����ó��] ����Ͻ� ���� ������ �۾�...");
			System.out.println("[���� ó��]" + method+ "() ARGS: " + args[0].toString());
		}
	}
}
