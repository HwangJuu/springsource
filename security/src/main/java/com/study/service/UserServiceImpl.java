package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.SpUser;
import com.study.dto.SpUserAuthority;
import com.study.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	
	//암호화를 위해서 사용하는 클래스 	
	@Autowired
	private BCryptPasswordEncoder encoder; 

	@Transactional // 두개 테이블 동시 접근
	@Override
	public boolean register(SpUser user) {
		
		// 사용자가 입력한 비밀번호를 암호화하기
		// encoder. : 단방향 암호화
		user.setPassword(encoder.encode(user.getPassword()));
		
		//회원가입
		boolean result = mapper.register(user) == 1;
		
		//권한부여
		SpUserAuthority auth = new SpUserAuthority(user.getUserid(), "ROLE_USER");
		mapper.registerAuth(auth);
		
		auth = new SpUserAuthority(user.getUserid(), "ROLE_ADMIN");
		mapper.registerAuth(auth);
		
		
		return result;
	}



}
