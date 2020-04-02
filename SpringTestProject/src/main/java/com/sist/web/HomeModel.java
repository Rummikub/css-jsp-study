package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeModel {
			//★★★★★★★★★★★★★★★★★★★★★★
			@RequestMapping("main/home.do")
			public String home_main(HttpServletRequest request)
			{
				request.setAttribute("msg", "Hello Spring :^)");
				return "home";
			}
}
