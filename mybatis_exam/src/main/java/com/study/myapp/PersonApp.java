package com.study.myapp;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.service.PersonSerivce;


//org.apache.ibatis.binding.BindingException:
// Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
//오류 수정 => @Param("id") String id,@Param("name") String name

public class PersonApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonSerivce service = (PersonSerivce) ctx.getBean("person");
//		System.out.println(service.insertPerson("kang476", "강민섭"));
		
//		System.out.println(service.selectPerson("kang456"));
//		
//		//수정
//		System.out.println(service.updatePerson("hong123","홍동길")?"수정성공":"수정실패");
//		System.out.println(service.selectPerson("hong123"));
//		
//		//삭제
//		System.out.println(service.deletePerson("hong123")?"삭제성공":"삭제실패");
//		System.out.println(service.selectPerson("hong123"));

	}
}
