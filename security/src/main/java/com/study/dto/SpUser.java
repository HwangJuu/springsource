package com.study.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SpUser {
	private String userid;
	private String email;
	private boolean enabled;
	private String password;
	
	//권한 정보 리스트 형태
	private List<SpUserAuthority> authorities;
	

}
