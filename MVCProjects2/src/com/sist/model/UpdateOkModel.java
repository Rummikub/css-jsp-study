package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateOkModel  implements Model {
	@Override
	public String execute(HttpServletRequest request) {
		//★★★ boardUpdate 
		
		// 상세보기==>  번호 넘어가야 함
		String no="";
		 try
		  {
			request.setCharacterEncoding("UTF-8");
			//String no -> no 
			no=request.getParameter("no");
			
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			vo.setNo(Integer.parseInt(no))	;
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardUpdate(vo);

		  } catch (Exception ex) {}

		//★★★ 값이 넘어와야...
			return "redirect:detail.do?no="+no;
	}

}
