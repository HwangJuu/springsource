package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
		//@RequestMapping(path = "/login", method = RequestMethod.GET) // 405 에러 Request method 'POST' not supported	
	// http://localhost:9090/login
		@GetMapping("/login")
		public String login() {
			log.info("login...."); // WEB-INF/views/sample/login.jsp
		
			return "sample/login"; //forward 방식
									
		}
		
//		 @RequestMapping(path = "/login", method = RequestMethod.POST) // 405 에러 Request method 'POST' not supported	
//		//①변수명 사용
//		@PostMapping("/login")
//		public void loginPost(String userid, String password, String addr, int age) {
//			log.info("login POST...."+userid+ " "+ password+" "+addr +" "+age); // WEB-INF/views/sample/login.jsp										}
//		}
		
		
//		//② ~~DTO 객체 사용
//		@PostMapping("/login")
//		public String loginPost(UserDTO userDto) {
//			log.info("login POST...."+userDto.getUserid()+ " "+ userDto.getPassword()+" "+userDto.getAddr()+" "+userDto.getAge()); // WEB-INF/views/sample/login.jsp								
//			
//			// return "home"; - forward 방식
//			// return "redirect:/"; - sendRedirect방식
//			//return "redirect:/calc/add";// 페이지가 아닌 가야할 경로를 지정 (get방식으로 들어가는 주소)
//			//return "redirect:/" / => HomeController에 있는 value 값으로 인식 return이 home으로 되어 있어서 home으로 감.
//			return "redirect:/"; 
//		}
		
		//② ~~DTO 객체 사용
		@PostMapping("/login")
		public String loginPost(@ModelAttribute("user") UserDTO userDto) {
			log.info("login POST...."+userDto.getUserid()+ " "+ userDto.getPassword()+" "+userDto.getAddr()+" "+userDto.getAge()); // WEB-INF/views/sample/login.jsp								
			
			return "sample/logout";
//			return "redirect:/"; //값을 유지하려면 session에 담아야함.
		}
			
//		@PostMapping("/login")
//		③ HttpServletRequest 객체 사용
//		public void loginPost(HttpServletRequest request) {
//			String userid = request.getParameter("userid");
//			String password = request.getParameter("password");
//			String addr = request.getParameter("addr");
//			int age = Integer.parseInt(request.getParameter("age"));
//			log.info("login POST...."+userid+ " "+ password+" "+addr +" "+age); // WEB-INF/views/sample/login.jsp								
//		}
			

}
