package com.study.calc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
	
	@Around(value="execution(* com.study.calc..*(..))") //.* : 여러 클래스를 지정할 때
	public Object measure(ProceedingJoinPoint pjp) throws Throwable {
		
		//수행 전
		long start = System.nanoTime();
		
		
		try {//메소드 호출
			Object obj = pjp.proceed(); //리턴 값 받기
			return obj;
			
		} finally {
			//수행 후
			long end = System.nanoTime();
			System.out.println("걸린 시간 : " + (end - start));			
		}		
	}
}
