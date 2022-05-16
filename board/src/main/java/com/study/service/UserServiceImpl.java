package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.AuthDTO;
import com.study.dto.MemberDTO;

import com.study.mapper.MemberMapper;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private MemberMapper mapper;
	
	
	//암호화를 위해서 사용하는 클래스 	
	@Autowired
	private BCryptPasswordEncoder encoder; 

	@Transactional // 두개 테이블 동시 접근
	@Override
	public boolean register(MemberDTO member) {
		
		// 사용자가 입력한 비밀번호를 암호화하기
		// encoder. : 단방향 암호화
//		member.setUserpw(encoder.encode(member.getUserpw()));
//		
//		//회원가입
//		boolean result = mapper.register(member) == 1;
//		
//		//권한부여
//		AuthDTO auth = new AuthDTO(member.getUserid(), "ROLE_USER");
//		mapper.registerAuth(auth);
		
		
		
//		return result;
		return false;
	}



}
