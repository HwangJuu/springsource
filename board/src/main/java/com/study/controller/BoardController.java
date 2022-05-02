package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.dto.PageDTO;
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
	public void list(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("전체 리스트 호출" + cri);
		
		List<BoardDTO> list = service.getList(cri);
		int total = service.getTotalCnt(cri);
		
		model.addAttribute("pageDto", new PageDTO(cri, total));
		model.addAttribute("list", list);
	}
	
	// /board/register 컨트롤러 작성
	@GetMapping("/register")
	public void register() {
		log.info("register 폼 요청");
	}
	
	//post 
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, Criteria cri, RedirectAttributes rttr) {
		log.info("글 등록 요청" + insertDto);
		
		if(!service.insert(insertDto)) {
			return "redirect:/board/register";
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
	}
	
	// /board/read + bno
	// bno에 해당하는 게시물 읽어온 후 read.jsp 보여주기
	// 똑같이 받아서 오는건 같이 배열로 묶을 수 있다
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("게시물 요청" + bno);
		log.info("게시물 요청-cri" + cri);
		
		BoardDTO dto = service.getRow(bno);				
		model.addAttribute("dto", dto);
	}
	
	//수정
	// /board/read + post = >수정 성공시 수정된 게시물 보여주기
	@PostMapping("/modify")
	public String modifyPost(BoardDTO updateDto,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 수정 요청"+updateDto);
		log.info("게시물 수정 요청-cri"+cri);
		
		service.update(updateDto);
		
		//수정 성공
		rttr.addAttribute("bno", updateDto.getBno());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/read";
	}
	
	// /board/remove + bno
	// 성공 시 list 보여주기
	@GetMapping("/remove")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 삭제 요청"+bno);
		log.info("게시물 삭제 요청-cri"+cri);
		
		service.delete(bno);
		
		//주소줄에 딸려보내는 방식
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		//세션을 이용하는 방식
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}

}











