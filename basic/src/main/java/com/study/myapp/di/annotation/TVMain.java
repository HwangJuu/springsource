package com.study.myapp.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class TVMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		
		//No bean named 'lgTV' available
//		TV tv = (TV)ctx.getBean("lg"); // 기본 클래스명의 앞자리만 소문자로 바꿔서 넣기
										// @Component("lg") 아이디 생성 후 아이디로 불러오기 가능
		TV tv = (TV)ctx.getBean("samsung"); // 클래스명의 앞자리만 소문자로 바꿔서 넣기
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		
//		NoUniqueBeanDefinitionException:
//		No qualifying bean of type 'com.study.myapp.di.annotation.Speaker' available:
//		expected single matching bean but found 2: appleSpeaker,sonySpeaker

	
	}
}
