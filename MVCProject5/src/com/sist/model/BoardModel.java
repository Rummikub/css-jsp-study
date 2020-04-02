package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

@Controller
// POJO 서로 영향이 없게 결합성 낮은 방식의 프로그래밍


public class BoardModel {
//게시판과 관련된 모든 메소드를 모아서 처리한다 (한 클래스 안에 여러 메소드)
												
	
								@RequestMapping ("board/list.do")
											// 목록보기
											public String  boardListData(HttpServletRequest request,HttpServletResponse response)
											{
												String page=request.getParameter("page");
												if(page==null)
														page="1";
												int curpage=Integer.parseInt(page);
												int rowSize=10;
												int start=(rowSize*curpage)-(rowSize-1);
												int end=rowSize*curpage;
												
												Map map=new HashMap();
												map.put("start", start);
												map.put("end", end);
												
												List<BoardVO> list=BoardDAO.boardListData(map);
												int totalpage=BoardDAO.boardTotalPage();
												String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
												
										
												//JSP로 결과값 전송
												request.setAttribute("list", list);
												request.setAttribute("curpage", curpage);
												request.setAttribute("totalpage", totalpage);
												request.setAttribute("today", today);
										
											 // ★ 페이지 넘기려고 String jsp 받음 + 위에서 @ReqeustMapping("board/list.do") 라고 줬기 때문에 board/제거
																// 이미 board라는 폴더에 들어와 있는데 경로를 한번 더 주면 board > board 폴더가 하나 더 있어야 된다는 뜻
												return "list.jsp";
											}
										
								
								@RequestMapping ("board/detail.do")			
											// detail -> 값출력 req실어서 값을 보내주는 용도
											public String boardDetailData(HttpServletRequest request,HttpServletResponse response)
											{
												String no=request.getParameter("no");
												BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
												
												//detail.jsp로 전송
												request.setAttribute("vo", vo);
												
												return "detail.jsp";
												
											}
											
								@RequestMapping ("board/insert_ok.do")
											// insert -> _ok 화면이동  (model = 비즈니스로직 , 모든 기능 코딩은 자바에 있음)
											public void boardInsert(HttpServletRequest request, HttpServletResponse response)
											{    
												  try
												  {
													request.setCharacterEncoding("UTF-8");
													String name=request.getParameter("name");
													String subject=request.getParameter("subject");
													String content=request.getParameter("content");
													String pwd=request.getParameter("pwd");
													
													BoardVO vo=new BoardVO();
													vo.setName(name);
													vo.setSubject(subject);
													vo.setContent(content);
													vo.setPwd(pwd);
													
													BoardDAO.boardInsert(vo);
												//	response.sendRedirect("list.jsp");
												  } catch (Exception ex) {}
										
											}
											
									@RequestMapping ("board/update.do")
											//update list 
											  public void boardUpdateData(HttpServletRequest request,HttpServletResponse response)
											   {
												   String no=request.getParameter("no");
												   BoardVO vo=BoardDAO.boardUpdateData(Integer.parseInt(no));
												   request.setAttribute("vo", vo);
											   }
											  
									@RequestMapping ("board/update_ok.do")
											// update 
											public void boardUpdate(HttpServletRequest request, HttpServletResponse response)
											{    
												  try
												  {
													request.setCharacterEncoding("UTF-8");
													
													String no=request.getParameter("no");
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
													//response.sendRedirect("list.jsp?no="+no);
												  } catch (Exception ex) {}

	}
}
