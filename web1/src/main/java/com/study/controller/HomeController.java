package com.study.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.RegisterDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "home";  //String 값이 남게 되면, return 값 만을 가지고 
						///WEB-INF/views/home.jsp가 응답
	}
	// redirect 방식으로 움직일 때 값을 전송하는 방법
	// ① rttr.addAttribute("age",10); => 주소줄에 age라는 이름으로 10을 보내는 방식
	//		http:// ~~~~~~~?age=10 (~~~~:어떤게 오던 상관없이)
	//		path += "?page=+page+"&amount="+amount..... 와 같은 개념
	
	// ② rttr.addFlashAttribute("num","15"); => 주소줄에 따라가지 않음. 보이지 않음
	//			세션 객체(일회성)를 이용해서 담아줌

	@GetMapping("/doB")
	public String doB(RedirectAttributes rttr) {
		rttr.addAttribute("age",10); // http://localhost:9090/?age=10
		rttr.addAttribute("addr","서울"); 
		rttr.addAttribute("name","홍길동");  //http://localhost:9090/?age=10&addr=서울&name=홍길동
		
		rttr.addFlashAttribute("num","15");
		return "redirect:/";  // /home으로 가는지 확인 => http://localhost:9090
	}
	
	//예전 방식
	@GetMapping("/doC")
	public ModelAndView doC() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("num",35); //request.setAttribute()
		return mav;		
	}
	
	@GetMapping("/doD")
	public RegisterDTO regist() {
		return new RegisterDTO();
	}
	
}
