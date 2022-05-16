package com.study.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.study.handler.CustomAccessDeniedHandler;
import com.study.handler.CustomLoginSuccessHandler;
import com.study.service.CustomUserDetailService;


// security-context.xml 환경 파일 변경


@Configuration//환경 설정 파일
@EnableWebSecurity //스프링 mvc + 시큐리티 결합


//WebSecurityConfigurerAdapter : 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//remember-me data-source-ref="ds"
	@Autowired
	private DataSource dataSource;
	
	
	//비밀번호 암호화 : <bean id ="encoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"/>
	@Bean //BCryptPasswordEncoder
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//<!-- component 삭제 후 동일하게 사용하기 위해 생성 	-->
	//<!-- 로그인 성공 후 다음 작업을 담당 -->
	//<bean id="customLoginSuccessHandler" class="com.study.handler.CustomLoginSuccessHandler"/>
	@Bean
	public AuthenticationSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	
	//<!-- 접근 권한이 없는 경우 다음 작업을 담당 -->
	//<bean id="customAccessDeniedHandler" class="com.study.handler.CustomAccessDeniedHandler"/>
	@Bean
	public AccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	//<!-- 로그인 이후 정보를 가지고 있는 객체 -->
	//<bean id="customUserDetailService" class="com.study.service.CustomUserDetailService"></bean>
	@Bean
	public UserDetailsService customUserDetailService() {
		return new CustomUserDetailService();
	}
	
	
	//데이터 소스 구현
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	
	
	//<security:http>, <security:authentication-manager>  오버라이딩
	
	
	//<security:authentication-manager> 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService())
			.passwordEncoder(passwordEncoder());
	}
	
	
	
	
	//<security:http>
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//한글필터
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		//<security:form-login 
		http.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/login")
			.successHandler(customLoginSuccessHandler())
			.failureUrl("/member/login-error");
		
		//<security:logout
		http.logout()
			.logoutUrl("/member/logout")
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/");
		
		//<security:remember-me
		http.rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
			
	}
	
	
	
	
	
	
	
	
	
	
}
