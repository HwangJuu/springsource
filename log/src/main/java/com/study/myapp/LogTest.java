package com.study.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTest {
	
	//private static final Logger log=LoggerFactory.getLogger(LogTest.class); => lombok 으로 실행 가능
	
	public static void main(String[] args) {
		log.error("error");
		log.warn("warn");
		log.info("info");
		log.debug("debug");
		log.trace("trace");
		

	}
}
