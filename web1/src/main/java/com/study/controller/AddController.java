package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.NumDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/calc/*") //고정으로 만들지 않을 시 controllor에서 //@GetMapping("/calc/add"), //@GetMapping("/board/insert")  다양하게 불러쓸수 있음
public class AddController {
	
	//@GetMapping("/calc/add")
	@GetMapping("/add")
	public void addGet() {
		log.info("add.jsp 페이지 요청...");
	//	System.out.println("로그 사용 하지 않을시 사용");
	}
	
//	@PostMapping("/add")
//	public void addPost(int num1, int num2) { //(int op1, int op2)
//		log.info("덧셈 요청");
//		log.info("num1 + num2 = " + (num1+num2));
//		//log.info("num1 + num2 = " + (op1+op2));  500 에러 발생. 변수명 똑같이 맞춰야함.
//	}
	
//	@PostMapping("/add")
//	public void addPost(int num1, int num2) {//값을 넣지 않고 전송을 누르면 400에러가 뜸
//		log.info("덧셈 요청");
//		log.info("num1 + num2 = " + (num1+num2));
//	}
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam("num1") int num1, @RequestParam("num2")int num2) {
//		log.info("덧셈 요청");
//		log.info("num1 + num2 = " + (num1+num2)); //400 에러
////		log.info("num1 + num2 = " + (op1+op2)); //출력 됨.
//	}
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam(value="num1" , required = false, defaultValue = "0") int num1,
//			@RequestParam(value="num2", required = false, defaultValue = "0")int num2) {
//		//  required 필수는 아니야, defaultValue 값이 없으면 0이야. 
//		log.info("덧셈 요청");
//		log.info("num1 + num2 = " + (num1+num2)); //에러 없이 출력
//		
//		//어느view로 갈껀지 
//		//어느 jsp 갈것인가? //http://localhost:9090/calc/add 
//	}
	
	
//	//NumDTO 사용
//	@PostMapping("/add")
//	public void addPost(NumDTO dto) {
//		log.info("덧셈 요청");
//		log.info("num1 + num2 = " + (dto.getNum1()+dto.getNum2()));
//	}
	
	//@ModelAttribute("이름") : 바인딩 객체의 이름을 변경
	//						  : Model 객체에 값을 담는 것과 같은 기능 제공
	
	@PostMapping("/add")
	public void addPost(@ModelAttribute("dto") NumDTO dto, Model model) {
		log.info("덧셈 요청");
		log.info("num1 + num2 = " + (dto.getNum1()+dto.getNum2()));
		
		int result = dto.getNum1()+dto.getNum2();
		//result  값을 add.jsp 에서 보여주기 => Model 객체(request.setAttribute()와 같은 개념)
		model.addAttribute("result", result);
	}
	
	
	

}
