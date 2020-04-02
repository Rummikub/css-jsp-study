package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardModel {

	@RequestMapping("main/board.do")  // RequestMapping.java에서 value의 type형이 String이니까 꼭 ""로 값을 채워줘야 함 
	public String main_board(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판");
		return "board";
	}
	
}
