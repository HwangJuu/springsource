package com.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration //환경설정 파일이야 알려줌
@ComponentScan({"com.study.service","com.study.task","com.study.handler"}) //@Service 활성화 <context:component-scan base-package="com.study.service"/>
@MapperScan("com.study.mapper") //mybatis 인터페이스, 매퍼 활성화 <mybatis-spring:scan base-package="com.study.mapper"/>
@EnableScheduling //<task:annotation-driven/>
@EnableTransactionManagement //<tx:annotation-driven/>




//root-context 대체
public class RootConfig {
	
	@Bean // 객체 생성해줘. <bean> </bean>
	public DataSource dataSource() {
		
		//HikariCP 설정 <bean id= "hikariConfig" class="com.zaxxer.hikari.HikariConfig">
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("javadb");
		hikariConfig.setPassword("12345");
		
		HikariDataSource datasource = new HikariDataSource(hikariConfig);
		return datasource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		// mybatis sql 작업을 해주는 객체 생성  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
		
	}
	
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
		
	}

}
