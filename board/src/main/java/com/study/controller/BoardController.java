package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dto.BoardDTO;
import com.study.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// /board/list 컨트롤러 작성
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전체 리스트 호출");
		List<BoardDTO> list = service.getList();
		model.addAttribute("list", list);
	}
	

}
