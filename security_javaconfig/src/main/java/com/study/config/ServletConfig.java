package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
	
	
	// commons fileupload 설정  
	//<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
	
	

}
