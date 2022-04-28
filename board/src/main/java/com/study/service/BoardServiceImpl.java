package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dto.BoardDTO;
import com.study.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	

	@Override
	public List<BoardDTO> getList() {		
		return mapper.select();
	}

}
