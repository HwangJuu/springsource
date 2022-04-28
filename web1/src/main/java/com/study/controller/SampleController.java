package com.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/sample/*") //http://localhost:9090/sample/** 은 고정
public class SampleController {
	
	// 앞쪽 고정 : WEB-INF/views/
	// 뒤쪽 고정 : .jsp
	
	// 컨트롤러의 리턴 타입이 void 인 경우
	// http://localhost:9090/ 다음 부분이 jsp 페이지를 찾는데 사용됨
	
	// 컨트롤러의 리턴 타입이 String 인 경우
	// return 값 만을 가지고 jsp 페이지를 찾음
	
//	//@RequestMapping(path = "/basic", method = RequestMethod.GET) //http://localhost:9090/sample/basic
//	@GetMapping("/basic")//간단 버전
//	public void basic() {
//		log.info("basic...."); // 파일 [/WEB-INF/views/sample/basic.jsp]을(를) 찾을 수 없습니다.
//	}
	
	
	//Model에 담기
//	@GetMapping("/basic")
//	public void basic(int page, Model model) { //
//		log.info("basic...."); // /WEB-INF/views/sample/basic.jsp
//		
//		//page 변수값을 jsp 보여주기 : Model에 담기
//		model.addAttribute("page", page);
//	}
	
	//@ModelAttribute("page") 사용
	@GetMapping("/basic")
	public void basic(@ModelAttribute("page") int page, Model model) { //
		log.info("basic...."); // /WEB-INF/views/sample/basic.jsp
		
		
	}
	
	
	//@RequestMapping("/login") 메소드 방식 get + post 둘 다 허용. 잘 사용하지 않음 //http://localhost:9090/sample/login
	//@RequestMapping(path = "/login", method = RequestMethod.GET) : get만 허용
	//@RequestMapping(path = "/login", method = RequestMethod.POST) : post만 허용
	//get과 post를 나눠서 사용. method만 다르게 사용
	//@RequestMapping(path = "/login", method = RequestMethod.GET) // 405 에러 Request method 'POST' not supported	
	@GetMapping("/login")
	public void login() {
		log.info("login...."); //jsp 찾을 때 sample/login
								// WEB-INF/views/sample/login.jsp
	}
	
	//Controller 파라미터 수집
	//①변수명 사용
	//② ~~DTO 객체 사용 (jsp 에서 값이 유지됨)
	//③ HttpServletRequest 객체 사용(필요할 경우만 사용, 대부분 사용하지 않음)
	
	//@RequestParam("이름") : 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우 사용
	
// @RequestMapping(path = "/login", method = RequestMethod.POST) // 405 에러 Request method 'POST' not supported	
	//①변수명 사용
	@PostMapping("/login")
	public void loginPost(String userid, String password, String addr, int age) {
		log.info("login POST...."+userid+ " "+ password+" "+addr +" "+age); // WEB-INF/views/sample/login.jsp								
	}
	
	//② ~~DTO 객체 사용
//	@PostMapping("/login")
//	public void loginPost(UserDTO userDto) {
//		log.info("login POST...."+userDto.getUserid()+ " "+ userDto.getPassword()+" "+userDto.getAddr()+" "+userDto.getAge()); // WEB-INF/views/sample/login.jsp								
//	}
	
//	@PostMapping("/login")
	//③ HttpServletRequest 객체 사용
//	public void loginPost(HttpServletRequest request) {
//		String userid = request.getParameter("userid");
//		String password = request.getParameter("password");
//		String addr = request.getParameter("addr");
//		int age = Integer.parseInt(request.getParameter("age"));
//		log.info("login POST...."+userid+ " "+ password+" "+addr +" "+age); // WEB-INF/views/sample/login.jsp								
//	}
	
	
	//@RequestMapping(path = "/doA", method= RequestMethod.GET) //http://localhost:9090/sample/doA
	@GetMapping("/doA")
	public void doA(String userid) {
		log.info("doA..."+userid); //파일 [/WEB-INF/views/sample/doA.jsp]을(를) 찾을 수 없습니다.
	}
	
	//@RequestMapping(value="/insert", method= RequestMethod.GET) //http://localhost:9090/sample/insert
	@GetMapping("/insert")
	public String insert(@RequestParam("ids") ArrayList<String> ids) {
		log.info("insert...."+ids); //파일 [/WEB-INF/views/insert.jsp]을(를) 찾을 수 없습니다.
		return "insert"; //리턴하는 이름만 가지고 view를 찾음
	}

}
