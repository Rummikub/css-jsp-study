package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardReplyVO;
import com.sist.vo.BoardVO;

@Controller
/*

사용자가 페이지를 보여달라고 요청을 했다 , Model로 이동 @RequestMapping된 메소드를 통해서 사용자 요청한 URI주소 필요,--DAO랑 왔다갔다하면서 처리하고--, JSP에서 출력
 
Model = DAO + Service + VO   --> 중복을 제거하려고 따로 클래스로 빼준 것!

		****************************기술면접************
		시맨틱 태그 : 프로그래머가 바로 알아볼 수 있게 만든 태그 (HTML5)
		GET/POST 방식
		ASC/BYTE=> 인코딩 / 디코딩 (송신/수신) 
		오버로딩/오버라이딩
		인터페이스/상속
		*********************************************
*/
public class FreeBoardModel {

	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response)
	{
		//page
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		FreeBoardDAO dao=new FreeBoardDAO();
		List<BoardVO> list=dao.freeboardListData(curpage);
		//--------------------------------------------------
		int totalpage=dao.freeboardTotalPage();
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		//--------------------------------------------------
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		//*****************************************************************오늘날짜 바로 변환 ******
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return"../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return"../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{	
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		//★ 매개변수가 3개 이상이면 클래스로 묶어서 전송 (~VO) 구조체[여러개의 변수를 묶어줌] => 클래스 이용
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//★ DAO
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.freeboardInsert(vo);
		
		return"redirect:../freeboard/list.do";
	}
//***********************대댓글 *******************************************************상세보기 아래에 붙는다	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request,HttpServletResponse response)
	{
		//${vo.no}<==> VO
		String no=request.getParameter("no"); //bno
		String page=request.getParameter("page"); // 댓글에 관련된 페이지가 넘어가지
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		Map map=new HashMap();
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		map.put("pStart", start);
		map.put("pEnd",end);
		map.put("pBno", Integer.parseInt(no));
		List<BoardReplyVO> list=FreeBoardReplyDAO.replyListData(map);
		
		map=new HashMap();
		map.put("pBno", Integer.parseInt(no));
		int totalpage=FreeBoardReplyDAO.replyTotalPage(map);
		
		FreeBoardDAO dao=new FreeBoardDAO();
// VO를 받아온다 (no, type=1)
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 1);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", page);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
	{
		//${vo.no}<==> VO
		String no=request.getParameter("no");
		
		FreeBoardDAO dao=new FreeBoardDAO();
//type=2
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 2);
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request,HttpServletResponse response)
	{	
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		//★ 매개변수가 3개 이상이면 클래스로 묶어서 전송 (~VO) 구조체[여러개의 변수를 묶어줌] => 클래스 이용
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		String no=request.getParameter("no");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		//★ DAO
		FreeBoardDAO dao=new FreeBoardDAO();
		boolean bCheck=dao.freeboardUpdate(vo);
		request.setAttribute("no", no);
		request.setAttribute("bCheck", bCheck);
		return"../freeboard/update_ok.jsp";
	}
	
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		request.setAttribute("no", no);
		request.setAttribute("main_jsp", "../freeboard/delete.jsp");
		return "../main/main.jsp";
	}

	
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no"); 
		String pwd=request.getParameter("pwd");
		//DAO
		FreeBoardDAO dao=new FreeBoardDAO();
		boolean bCheck=dao.freeboardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
		return "../freeboard/delete_ok.jsp";
	}
	
	@RequestMapping("freeboard/reply_insert.do")
	public String freeboard_reply_insert(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String msg=request.getParameter("msg");
		String bno=request.getParameter("bno");
		
		HttpSession session=request.getSession(); 
		/*****************************************
		 request가 있으면 Session, cookie를 얻어올 수 있음 
		 ******************************************/
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		//DAO
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pBno", Integer.parseInt(bno));
		map.put("pId", id);
		map.put("pName", name);
		map.put("pMsg", msg);
		
		//insert
		FreeBoardReplyDAO.replyInsert(map);
		return "redirect: ../freeboard/detail.do?no="+bno;
	}
	
	@RequestMapping("freeboard/reply_update.do")
	public String freeboard_reply_update(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		String msg=request.getParameter("msg");
		String bno=request.getParameter("bno");
		String no=request.getParameter("no");
		
		Map map=new HashMap();
		map.put("pNo", Integer.parseInt(no));
		map.put("pMsg", msg);
		
		FreeBoardReplyDAO.replyUpdate(map);
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	@RequestMapping("freeboard/reply_reply_insert.do")
	public String freeboard_reply_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e){}
		
		String pno=request.getParameter("pno");
		String bno=request.getParameter("bno");
		String msg=request.getParameter("msg");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		Map map=new HashMap();
		map.put("pBno", Integer.parseInt(bno));
		map.put("pPno", Integer.parseInt(pno));
		map.put("pId", id);
		map.put("pName", name);
		map.put("pMsg", msg);
		//DAO
		FreeBoardReplyDAO.replyReplyInsert(map);
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	@RequestMapping("freeboard/reply_delete.do")
	public String freeboard_reply_delete(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String bno=request.getParameter("bno");
		Map map=new HashMap();
		map.put("pNo", Integer.parseInt(no));
		
		FreeBoardReplyDAO.replyDelete(map);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
}
