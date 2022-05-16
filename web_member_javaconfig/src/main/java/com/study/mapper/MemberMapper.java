package com.study.mapper;

import org.apache.ibatis.annotations.Param;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberMapper {
	//CRUD 메소드 정의
	//C-회원가입
	public int insert(MemberDTO register);
	//R-로그인
	public AuthDTO login(@Param("userid")String userid, @Param("password")String password);
	//R-중복아이디 검사
	public String dupId(String userid);
	//U- 비밀번호 변경
	public int update(ChangeDTO changeDto);
	//D-회원탈퇴
	public int delete(@Param("userid")String userid, @Param("password")String password);

}
