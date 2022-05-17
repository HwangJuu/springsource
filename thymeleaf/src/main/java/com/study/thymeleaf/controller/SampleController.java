package com.study.thymeleaf.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.thymeleaf.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample")
public class SampleController {

	@GetMapping("/ex1")
	public void ex1(Model model) {
		log.info("ex1 요청");
		
		model.addAttribute("data", "thymeleaf");
		
	}
	
	@GetMapping("/ex2")
	public void ex2(Model model) {
		log.info("ex2 요청");
		
		model.addAttribute("greeting", "안녕하세요");
		
	}
	
	@GetMapping("/ex3")
	public void ex3(Model model) {
		log.info("ex3 요청");
		
		MemberDTO dto = new MemberDTO(100, "mem01", "mem01", "홍길동", new Timestamp(System.currentTimeMillis()));
		
		model.addAttribute("dto", dto);
		
	}
	
	@GetMapping("/ex4")
	public void ex4(Model model) {
		log.info("ex4 요청");
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		for(int i=1; i<11; i++) {
			MemberDTO dto = new MemberDTO(i*10, "member"+i, "member"+i, "홍길동"+i, new Timestamp(System.currentTimeMillis()));
			list.add(dto);
		}		
		
		model.addAttribute("list", list);
		
	}
	

	@GetMapping("/ex5")
	public void ex5(Model model) {
		log.info("ex5 요청");
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		//if else 구조
		for(int i=0; i<10; i++) {
			MemberDTO dto = new MemberDTO(i, "u000"+i%3, "u000"+i%3, "홍길동"+i, new Timestamp(System.currentTimeMillis()));
			list.add(dto);
		}		
		
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/ex6")
	public void ex6(Model model) {
		String result = "SUCCESS";
		
		model.addAttribute("result", result);
		
	}
	
	@GetMapping("/ex-inline")
	public String exInline(RedirectAttributes rttr) {
		log.info("ex-inline 요청");
		
		MemberDTO dto = new MemberDTO(100, "mem01", "mem01", "홍길동", new Timestamp(System.currentTimeMillis()));
		
		rttr.addFlashAttribute("dto",dto);
		rttr.addFlashAttribute("result", "SUCCESS");
		
		return "redirect:/sample/ex7";
		
	}
	
	@GetMapping({"/ex7", "exlink", "exLayout","exLayout2","exLayout3","exTemplate","exSidebar"})
	public void ex7() {
		//링크처리
		
	}
	
	@GetMapping("/ex8")
	public void ex8(String param1, String param2, Model model) {
		
		
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
	}
	
	
	
}
