package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.mapper.SampleMapper1;
import com.study.mapper.SampleMapper2;

@Service("service")
public class SampleServiceImpl implements SampleService {
	
	@Autowired
	private SampleMapper1 mapper1;
	
	@Autowired
	private SampleMapper2 mapper2;

	@Transactional
	@Override
	public void addData(String data) {
		// transaction처리
		mapper1.inserCol1(data);
		mapper2.inserCol2(data); //일부러 에러를 냄. 
		//둘다 반영을 하던지, 둘다 반영을 안하던지. 서로 다른 테이블이 와도 상관없고 여러개가 와도 상관없이 하나처럼 반영시키기
	}

}
