package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.Criteria;
import com.study.dto.ReplyDTO;
import com.study.dto.ReplyPageDTO;
import com.study.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@RestController //jsp가 아니고 데이터만 보내기 위해 사용. 데이터만 받을 꺼야
@Slf4j
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// 댓글 삽입
	// / replies/new + POST + body(댓글 내용- json)
	// json == @RequestBody
	// return : 성공시 success + 200번 == HttpStatus.OK
	// 실패시 fail + 500번 == HttpStatus.INTERNAL_SERVER_ERROR
	// create
	// consumes : 받아서 처리할 컨텐츠 타입
	// produces : 내보낼 타입
	@PostMapping(path ="/new", consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyDTO insertDto) {
		log.info("댓글 삽입 요청" + insertDto);
		
		return service.replyInsert(insertDto)? new ResponseEntity<String>("success", HttpStatus.OK):
			new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 하나 가져오기
	// /replies/rno + GET
	// 성공시 ReplyDTO + 200
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno){
		log.info("댓글 가져오기" + rno);
		return new ResponseEntity<ReplyDTO>(service.replyRow(rno),HttpStatus.OK);
	}
	
	//댓글 수정
	// /replies/rno + PUT + body(수정내용 - json)
	// 성공시 success + 200, 실패시 fail + 500
	// @RequestMapping(path= "/{rno}",method = {RequestMethod.PUT,RequestMethod.PATCH})
	// public ResponseEntity<String> ==> success or fail이 들어온다는 것
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> put(@PathVariable("rno") int rno, @RequestBody ReplyDTO updateDto){
		log.info("댓글 수정" + updateDto);
		
		updateDto.setRno(rno);
		
		return service.replyUpdate(updateDto)? new ResponseEntity<String>("success", HttpStatus.OK):
			new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 삭제
	// /relies/rno + DELETE
	// 성공시 success + 200, 실패시 fail + 500
	@DeleteMapping(path="/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("댓글 삭제" + rno);
		
		return service.replyDelete(rno)? new ResponseEntity<String>("success", HttpStatus.OK):
			new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// 댓글 리스트 가져오기
	// / relies/pages/bno/page + GET
	// 성공시 댓글 리스트, 실패 나는 경우는 없음
	// http://localhost:9090/replies/pages/500/1 : 댓글을 가지고 올껀데 첫번째 페이지를 가지고 와
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") int bno, @PathVariable("page") int page){
		log.info("댓글 리스트 요청 bno="+bno+", page="+page);
		
		Criteria cri = new Criteria(page, 10); //1page에 10개씩 볼꺼야
		
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	

}
