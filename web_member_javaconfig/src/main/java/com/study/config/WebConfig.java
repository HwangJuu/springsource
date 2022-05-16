package com.study.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration //환경설정 파일이야 알려줌
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Class<?>[] 배열
	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {		
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		//기존 인코딩이 있어도 설정한 값 사용
		encodingFilter.setForceEncoding(true);
		
		return new Filter[] {encodingFilter};
	}

}
