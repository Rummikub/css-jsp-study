package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class InsertOkModel  implements Model {
	@Override
	public String execute(HttpServletRequest request) {
		// ★★★ boardInsert에서 긁긁
		  try
		  {
			request.setCharacterEncoding("UTF-8");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			//메모리 할당
			
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			//값채움
			
			BoardDAO.boardInsert(vo);
			//호출
			
			
			
		  } catch (Exception ex) {}
			return "redirect:list.do";
	}

}
