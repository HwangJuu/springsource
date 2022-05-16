package com.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.study.interceptor.LoginInterceptor;

@Configuration //환경설정 파일이야 알려줌

@EnableWebMvc //<annotation-driven /> 대체

@ComponentScan(basePackages = {"com.study.controller"}) //@Controller 활성화 <context:component-scan base-package="com.study.controller" />

//servlet-context : 대체 환경설정
public class ServletConfig implements WebMvcConfigurer {
	
	//<resources mapping="/resources/**" location="/resources/" />대체
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	//<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	//<beans:property name="prefix" value="/WEB-INF/views/" />
	//<beans:property name="suffix" value=".jsp" /></beans:bean>
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	
	//<interceptors>
	//<interceptor><!--<mapping path="/member/step1"/>  -->
	//<mapping path="/member/changePwd"/>
	//<beans:ref bean="loginInterceptor"/>
	//</interceptor>
	//</interceptors>
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/member/changPwd")
				.addPathPatterns("/member/leave");
	}
	
	

}
