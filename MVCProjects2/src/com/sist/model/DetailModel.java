package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class DetailModel implements Model {
	@Override
	public String execute(HttpServletRequest request) {
		// boardDetailData에서 긁어와
		String no=request.getParameter("no");
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		
		//detail.jsp로 전송
		request.setAttribute("vo", vo);
		return "board/detail.jsp";
	}

}
