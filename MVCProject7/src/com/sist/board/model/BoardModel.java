package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
//**************************
import com.sist.dao.*;

@Controller
public class BoardModel {
 
	  @RequestMapping("site/board/list.do")
	  public String board_list(HttpServletRequest request, HttpServletResponse response)
	  {
		 
		  List<EmpVO> list=EmpDAO.empAllData();
		  request.setAttribute("list", list);
		  request.setAttribute("main_jsp", "board/list.jsp");
		  
		  
		  //★ sidebar --> CommonData.class 로 빼서 commonData 메서드로 호출
		  CommonData.commonData(request, response);
		  return "../main.jsp";
	  }
}
