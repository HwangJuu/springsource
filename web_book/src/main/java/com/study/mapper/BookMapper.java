package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.BookDTO;

public interface BookMapper {
	//메소드 프로터 타입만 들어오면 됨
	//CRUD 작업에 대한 메소드 선언만 해주면 됨
	
	//C - 삽입 ,R - 전체 조회, 하나 조회, 검색,U - 수정,D - 삭제	
	
	public int insert(BookDTO insertDto);//C - 삽입
	public List<BookDTO> list();//R - 전체 조회,
	public BookDTO select(int code); //R - 하나 조회
	public List<BookDTO> search(@Param("criteria") String criteria,@Param("keyword") String keyword);//R- 검색
	public int update(@Param("code")int code,@Param("price") int price); //U - 수정
	public int delete(int code); //D - 삭제
}
